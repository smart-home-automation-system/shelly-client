package cloud.cholewa.shelly.autoconfigure;

import cloud.cholewa.shelly.codec.ShellyMqttDecoder;
import cloud.cholewa.shelly.http.ShellyGen1Client;
import cloud.cholewa.shelly.http.ShellyHttpClientCustomizer;
import cloud.cholewa.shelly.http.ShellyRpcClient;
import cloud.cholewa.shelly.http.ShellyTimeouts;
import java.util.function.UnaryOperator;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import tools.jackson.databind.json.JsonMapper;

/**
 * Registers what a service needs to talk to its Shelly devices - each part only where the
 * service has what that part is built on, and every bean only when the service has none of
 * that type itself:
 * <ul>
 * <li>{@link ShellyRpcClient} and {@link ShellyGen1Client}, on the {@code WebClient.Builder}
 * of the application - so with WebFlux, Reactor Netty and one builder to take (the only one,
 * or the primary), with every {@link ShellyHttpClientCustomizer} of the application;</li>
 * <li>{@link ShellyMqttDecoder}, on the {@code JsonMapper} of the application - so with
 * Jackson 3 and one mapper to take.</li>
 * </ul>
 * A WebFlux service of this organization has all of that, so it gets all three beans by
 * taking the library - used or not. They call nobody by themselves;
 * {@code shelly.client.enabled: false} keeps them out.
 */
@AutoConfiguration(afterName = {
    "org.springframework.boot.webclient.autoconfigure.WebClientAutoConfiguration",
    "org.springframework.boot.jackson.autoconfigure.JacksonAutoConfiguration" })
@ConditionalOnProperty(prefix = "shelly.client", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(ShellyClientProperties.class)
public class ShellyClientAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({ WebClient.class, HttpClient.class })
    @ConditionalOnSingleCandidate(WebClient.Builder.class)
    static class Clients {

        // a bean of its own, so that a timeout outside its bounds stops the start and says
        // which one, and a service can give the clients other timeouts than the properties
        @Bean
        @ConditionalOnMissingBean
        ShellyTimeouts shellyTimeouts(ShellyClientProperties properties) {
            return new ShellyTimeouts(properties.connectTimeout(), properties.responseTimeout());
        }

        @Bean
        @ConditionalOnMissingBean
        ShellyRpcClient shellyRpcClient(WebClient.Builder builder, ShellyTimeouts timeouts,
                ObjectProvider<ShellyHttpClientCustomizer> customizers) {
            return new ShellyRpcClient(builder, timeouts, all(customizers));
        }

        @Bean
        @ConditionalOnMissingBean
        ShellyGen1Client shellyGen1Client(WebClient.Builder builder, ShellyTimeouts timeouts,
                ObjectProvider<ShellyHttpClientCustomizer> customizers) {
            return new ShellyGen1Client(builder, timeouts, all(customizers));
        }

        // every customizer of the application, in its order - none is the usual case
        private static UnaryOperator<HttpClient> all(ObjectProvider<ShellyHttpClientCustomizer> customizers) {
            return http -> {
                HttpClient customized = http;
                for (ShellyHttpClientCustomizer customizer : customizers.orderedStream().toList()) {
                    customized = customizer.apply(customized);
                }
                return customized;
            };
        }
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(JsonMapper.class)
    @ConditionalOnSingleCandidate(JsonMapper.class)
    static class Codec {

        @Bean
        @ConditionalOnMissingBean
        ShellyMqttDecoder shellyMqttDecoder(JsonMapper mapper) {
            return new ShellyMqttDecoder(mapper);
        }
    }
}
