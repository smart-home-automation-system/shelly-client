package cloud.cholewa.shelly.http;

import cloud.cholewa.shelly.model.RpcError;
import io.netty.channel.ChannelOption;
import java.net.URI;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    // a path, a query or another scheme into the call
    private static final Pattern ADDRESS = Pattern
        .compile("([A-Za-z0-9](?:[A-Za-z0-9.-]{0,251}[A-Za-z0-9])?)(?::(\\d{1,5}))?");

    private final WebClient client;
    private final ShellyTimeouts timeouts;

    // The builder is cloned: the consumer keeps its own, and what it configured on it - the
    // codecs, the observation that carries the trace - comes along. Only the connector is
    // replaced, because the timeouts live there.
    //
    // A connection per call, no pool: a device is called now and then, closes an idle
    // connection after seconds and restarts for an update, so a pooled connection is mostly a
    // dead one - and the first call after a pause would fail on it.
    ShellyHttp(WebClient.Builder builder, ShellyTimeouts timeouts) {
        HttpClient http = HttpClient.newConnection()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Math.toIntExact(timeouts.connect().toMillis()))
            .responseTimeout(timeouts.response());

        this.client = builder.clone().clientConnector(new ReactorClientHttpConnector(http)).build();
        this.timeouts = timeouts;
    }

    WebClient client() {
        return client;
    }

    static URI uri(String address, String path, Map<String, ?> query) {
        Matcher matcher = address == null ? null : ADDRESS.matcher(address);
        if (matcher == null || !matcher.matches()) {
            throw new IllegalArgumentException("Not the address of a device (a host, or host:port): " + address);
        }
        UriComponentsBuilder uri = UriComponentsBuilder.newInstance().scheme("http").host(matcher.group(1)).path(path);
        if (matcher.group(2) != null) {
            uri.port(Integer.parseInt(matcher.group(2)));
        }
        query.forEach((name, value) -> {
            if (value != null) {
                uri.queryParam(name, value);
            }
        });
        return uri.build().encode().toUri();
    }

    // A refusal is an error, with the status and - for a Gen2+ device - with what it said.
    <T> Mono<T> body(ClientResponse response, Mono<T> body, String call) {
        return response.statusCode().is2xxSuccessful() ? body
            : response.createException().flatMap(refusal -> Mono.error(refused(call, refusal)));
    }

    // The answer of a command is not read, but it has to be JSON: a 200 with a page of HTML is
    // a portal or another device on the address, not a Shelly that did what it was told.
    Mono<Void> done(ClientResponse response, String call) {
        if (!response.statusCode().is2xxSuccessful()) {
            return response.createException().flatMap(refusal -> Mono.error(refused(call, refusal)));
        }
        boolean json = response.headers().contentType().filter(MediaType.APPLICATION_JSON::isCompatibleWith)
            .isPresent();
        return json ? response.releaseBody()
            : response.releaseBody().then(Mono.error(() -> new ShellyCallException(call + ": " + NOT_A_SHELLY)));
    }

    // What every read has in common. An answer without a body completes without a value, and
    // JSON of another shape decodes into an object of nulls - "{}" - which would pass for a
    // device that is off: both are a failed call. So is everything else that can go wrong, as
    // one exception, with the cause kept for the log.
    <T> Mono<T> answer(Mono<T> exchange, Predicate<T> isAnswer, String call) {
        return exchange.filter(isAnswer)
            .switchIfEmpty(Mono.error(() -> new ShellyCallException(call + ": " + NOT_A_SHELLY)))
            .transform(result -> bounded(result, call));
    }

    <T> Mono<T> bounded(Mono<T> exchange, String call) {
        return exchange.timeout(timeouts.call()).onErrorMap(failure -> !(failure instanceof ShellyCallException),
            failure -> new ShellyCallException(call + " failed: " + failure.getClass().getSimpleName(), failure));
    }

    private static final String NOT_A_SHELLY = "the answer is not the answer of a Shelly";

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
