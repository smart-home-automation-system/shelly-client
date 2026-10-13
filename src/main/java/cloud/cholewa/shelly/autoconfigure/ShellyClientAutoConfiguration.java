package cloud.cholewa.shelly.autoconfigure;

import cloud.cholewa.shelly.codec.ShellyMqttDecoder;
import cloud.cholewa.shelly.http.ShellyGen1Client;
import cloud.cholewa.shelly.http.ShellyRpcClient;
import cloud.cholewa.shelly.http.ShellyTimeouts;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
 * of the application - so with WebFlux, Reactor Netty and a builder bean;</li>
 * <li>{@link ShellyMqttDecoder}, on the {@code JsonMapper} of the application - so with
 * Jackson 3 and a mapper bean.</li>
 * </ul>
 * {@code shelly.client.enabled: false} switches all of it off.
 */
@AutoConfiguration(afterName = {
    "org.springframework.boot.webclient.autoconfigure.WebClientAutoConfiguration",
    "org.springframework.boot.jackson.autoconfigure.JacksonAutoConfiguration" })
@ConditionalOnProperty(prefix = "shelly.client", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(ShellyClientProperties.class)
public class ShellyClientAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({ WebClient.class, HttpClient.class })
    @ConditionalOnBean(WebClient.Builder.class)
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
        ShellyRpcClient shellyRpcClient(WebClient.Builder builder, ShellyTimeouts timeouts) {
            return new ShellyRpcClient(builder, timeouts);
        }

        @Bean
        @ConditionalOnMissingBean
        ShellyGen1Client shellyGen1Client(WebClient.Builder builder, ShellyTimeouts timeouts) {
            return new ShellyGen1Client(builder, timeouts);
        }
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(JsonMapper.class)
    @ConditionalOnBean(JsonMapper.class)
    static class Codec {

        @Bean
        @ConditionalOnMissingBean
        ShellyMqttDecoder shellyMqttDecoder(JsonMapper mapper) {
            return new ShellyMqttDecoder(mapper);
        }
    }
}
