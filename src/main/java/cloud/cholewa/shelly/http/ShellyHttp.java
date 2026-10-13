package cloud.cholewa.shelly.http;

import cloud.cholewa.shelly.model.RpcError;
import io.netty.channel.ChannelOption;
import java.net.URI;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/** What the two clients share: the HTTP client, the address of a device, the end of a call. */
final class ShellyHttp {

    // a host name or an IPv4 address, with a port when the device is not on 80. Nothing else:
    // the address comes from a registry somebody types into, and it must not be able to carry
    // a path, a query, a user or another scheme into the call
    private static final Pattern ADDRESS = Pattern
        .compile("([A-Za-z0-9](?:[A-Za-z0-9.-]{0,251}[A-Za-z0-9])?)(?::(\\d{1,5}))?");

    private static final String NOT_A_SHELLY = "the answer is not the answer of a Shelly";

    /** A device, by the address the registry holds: checked, and safe to put into a message. */
    record Target(String host, Integer port) {

        URI uri(String path, Map<String, ?> query) {
            UriComponentsBuilder uri = UriComponentsBuilder.newInstance().scheme("http").host(host).path(path);
            if (port != null) {
                uri.port(port);
            }
            query.forEach((name, value) -> {
                if (value != null) {
                    uri.queryParam(name, value);
                }
            });
            return uri.build().encode().toUri();
        }

        @Override
        public String toString() {
            return port == null ? host : host + ":" + port;
        }
    }

    private final WebClient client;
    private final ShellyTimeouts timeouts;

    // The builder is cloned: the consumer keeps its own, and what it configured on it - the
    // codecs, the observation that carries the trace - comes along. Only the connector is
    // replaced, because the timeouts live there; what the consumer wants on the HTTP client
    // of the calls - its request log - it adds through the customizer, and the timeouts are
    // set after it, so that nothing it does can take them away.
    //
    // A connection per call, no pool: a device is called now and then, closes an idle
    // connection after seconds and restarts for an update, so a pooled connection is mostly a
    // dead one - and the first call after a pause would fail on it.
    ShellyHttp(WebClient.Builder builder, ShellyTimeouts timeouts, UnaryOperator<HttpClient> customizer) {
        HttpClient http = customizer.apply(HttpClient.newConnection())
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Math.toIntExact(timeouts.connect().toMillis()))
            .responseTimeout(timeouts.response());

        this.client = builder.clone().clientConnector(new ReactorClientHttpConnector(http)).build();
        this.timeouts = timeouts;
    }

    WebClient client() {
        return client;
    }

    // What is not an address is not repeated in the message: it may be anything somebody
    // typed, a password in front of an @ included, and the message ends in a log.
    static Target target(String address, String what) {
        Matcher matcher = address == null ? null : ADDRESS.matcher(address);
        if (matcher == null || !matcher.matches()) {
            throw new ShellyAddressException(what + ": not the address of a device (a host, or host:port)");
        }
        Integer port = matcher.group(2) == null ? null : Integer.valueOf(matcher.group(2));
        if (port != null && (port < 1 || port > 65535)) {
            throw new ShellyAddressException(what + ": not a port of a device: " + port);
        }
        return new Target(matcher.group(1), port);
    }

    /**
     * A call that is read: the answer has to be the model, and the model has to have the one
     * field it cannot be without. An answer without a body completes without a value, and
     * JSON of another shape decodes into an object of nulls - "{}" - which would pass for a
     * device that is off: both are a failed call.
     */
    <T> Mono<T> read(String address, String what, Function<Target, WebClient.RequestHeadersSpec<?>> request,
            ParameterizedTypeReference<T> model, Predicate<T> isAnswer) {
        return Mono.defer(() -> {
            Target target = target(address, what);
            String call = what + " on " + target;
            return request.apply(target)
                .exchangeToMono(response -> response.statusCode().is2xxSuccessful() ? response.bodyToMono(model)
                    : refusal(response, call))
                .filter(isAnswer)
                .switchIfEmpty(Mono.error(() -> new ShellyCallException(call + ": " + NOT_A_SHELLY)))
                .transform(exchange -> bounded(exchange, call));
        });
    }

    /**
     * A call whose answer is not read. It still has to be JSON: a 200 with a page of HTML is
     * a portal or another device on the address, not a Shelly that did what it was told.
     */
    Mono<Void> command(String address, String what, Function<Target, WebClient.RequestHeadersSpec<?>> request) {
        return Mono.defer(() -> {
            Target target = target(address, what);
            String call = what + " on " + target;
            return request.apply(target).exchangeToMono(response -> done(response, call))
                .transform(exchange -> bounded(exchange, call));
        });
    }

    private static Mono<Void> done(ClientResponse response, String call) {
        if (!response.statusCode().is2xxSuccessful()) {
            return refusal(response, call);
        }
        boolean json = response.headers().contentType().filter(MediaType.APPLICATION_JSON::isCompatibleWith)
            .isPresent();
        return json ? response.releaseBody()
            : response.releaseBody().then(Mono.error(() -> new ShellyCallException(call + ": " + NOT_A_SHELLY)));
    }

    // Everything that can go wrong leaves as one exception, with the cause kept for the log.
    // And the whole call is bounded: the response timeout only bounds the silence, which a
    // device that sends a byte now and then never runs into.
    <T> Mono<T> bounded(Mono<T> exchange, String call) {
        return exchange.timeout(timeouts.call()).onErrorMap(failure -> !(failure instanceof ShellyCallException),
            failure -> new ShellyCallException(call + " failed: " + failure.getClass().getSimpleName(), failure));
    }

    // a refusal is an error, with the status and - for a Gen2+ device - with what it said
    private static <T> Mono<T> refusal(ClientResponse response, String call) {
        return response.createException().flatMap(refusal -> Mono.error(refused(call, refusal)));
    }

    private static ShellyCallException refused(String call, WebClientResponseException refusal) {
        int status = refusal.getStatusCode().value();
        RpcError error = rpcError(refusal);
        if (error != null && error.getCode() != null) {
            return new ShellyRpcException(
                call + " refused: " + error.getCode() + " " + error.getMessage(), status, error.getCode(),
                error.getMessage());
        }
        return new ShellyCallException(call + " refused with the status " + status, status, refusal);
    }

    // a Gen1 device and a proxy answer text, not an RPC error: whatever cannot be read as one
    // leaves the status alone
    private static RpcError rpcError(WebClientResponseException refusal) {
        try {
            return refusal.getResponseBodyAs(RpcError.class);
        } catch (RuntimeException notAnRpcError) {
            return null;
        }
    }
}
