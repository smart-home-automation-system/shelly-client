package cloud.cholewa.shelly.http;

import java.util.function.UnaryOperator;
import reactor.netty.http.client.HttpClient;

/**
 * What a service wants on the HTTP client of its calls to the devices - its request log first
 * of all:
 *
 * <pre>
 * &#64;Bean
 * ShellyHttpClientCustomizer shellyCallsInTheLog(Logbook logbook) {
 *     return http -&gt; http.doOnConnected(connection -&gt; connection.addHandlerLast(new LogbookClientHandler(logbook)));
 * }
 * </pre>
 *
 * The clients replace the connector of the {@code WebClient.Builder} they are given, so what a
 * service set on its own connector does not reach them; a bean of this type does. The
 * timeouts are set after the customizers and cannot be taken away by one.
 */
@FunctionalInterface
public interface ShellyHttpClientCustomizer extends UnaryOperator<HttpClient> {
}
