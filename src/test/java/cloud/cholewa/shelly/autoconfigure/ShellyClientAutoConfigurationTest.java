package cloud.cholewa.shelly.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import cloud.cholewa.shelly.codec.ShellyMqttDecoder;
import cloud.cholewa.shelly.http.ShellyGen1Client;
import cloud.cholewa.shelly.http.ShellyRpcClient;
import cloud.cholewa.shelly.http.ShellyTimeouts;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.webclient.autoconfigure.WebClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import tools.jackson.databind.json.JsonMapper;

class ShellyClientAutoConfigurationTest {

    // an application with WebFlux and Jackson: the builder comes from Spring Boot's own
    // auto-configuration, which this one has to come after
    private final ApplicationContextRunner application = new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(ShellyClientAutoConfiguration.class, WebClientAutoConfiguration.class))
        .withUserConfiguration(MapperOfTheApplication.class);

    @Test
    void shouldRegisterTheClientsAndTheDecoderWithTheDefaultTimeouts() {
        application.run(context -> {
            assertThat(context).hasNotFailed().hasSingleBean(ShellyRpcClient.class)
                .hasSingleBean(ShellyGen1Client.class).hasSingleBean(ShellyMqttDecoder.class);
            assertThat(context.getBean(ShellyTimeouts.class))
                .isEqualTo(new ShellyTimeouts(Duration.ofSeconds(3), Duration.ofSeconds(5)));
        });
    }

    // a bare number is seconds: "8" bound as milliseconds would be below the bound, and
    // "8000" meant as milliseconds is refused instead of waiting two hours
    @Test
    void shouldReadABareNumberOfATimeoutAsSeconds() {
        application.withPropertyValues("shelly.client.connect-timeout=8", "shelly.client.response-timeout=20s")
            .run(context -> assertThat(context.getBean(ShellyTimeouts.class))
                .isEqualTo(new ShellyTimeouts(Duration.ofSeconds(8), Duration.ofSeconds(20))));
    }

    @ParameterizedTest
    @ValueSource(strings = { "shelly.client.connect-timeout=0", "shelly.client.response-timeout=8000",
        "shelly.client.response-timeout=500ms", "shelly.client.connect-timeout=2m" })
    void shouldNotStartWithATimeoutOutsideTheBounds(String property) {
        application.withPropertyValues(property).run(context -> assertThat(context).hasFailed().getFailure()
            .rootCause().isInstanceOf(IllegalArgumentException.class).hasMessageContaining("timeout of a Shelly call"));
    }

    @Test
    void shouldRegisterNothingWhenSwitchedOff() {
        application.withPropertyValues("shelly.client.enabled=false")
            .run(context -> assertThat(context).hasNotFailed().doesNotHaveBean(ShellyRpcClient.class)
                .doesNotHaveBean(ShellyGen1Client.class).doesNotHaveBean(ShellyTimeouts.class)
                .doesNotHaveBean(ShellyMqttDecoder.class));
    }

    // a consumer that wants the models alone - the four services that had the library before
    // the clients - has no WebFlux here or no builder, and gets no client and no failure
    @Test
    void shouldRegisterNoClientWithoutWebFlux() {
        application.withClassLoader(new FilteredClassLoader(WebClient.class))
            .run(context -> assertThat(context).hasNotFailed().doesNotHaveBean(ShellyRpcClient.class)
                .doesNotHaveBean(ShellyGen1Client.class).hasSingleBean(ShellyMqttDecoder.class));
    }

    @Test
    void shouldRegisterNoClientWithoutReactorNetty() {
        application.withClassLoader(new FilteredClassLoader(HttpClient.class))
            .run(context -> assertThat(context).hasNotFailed().doesNotHaveBean(ShellyRpcClient.class)
                .doesNotHaveBean(ShellyGen1Client.class));
    }

    @Test
    void shouldRegisterNoClientWithoutABuilder() {
        new ApplicationContextRunner().withConfiguration(AutoConfigurations.of(ShellyClientAutoConfiguration.class))
            .withUserConfiguration(MapperOfTheApplication.class)
            .run(context -> assertThat(context).hasNotFailed().doesNotHaveBean(ShellyRpcClient.class)
                .doesNotHaveBean(ShellyTimeouts.class).hasSingleBean(ShellyMqttDecoder.class));
    }

    @Test
    void shouldRegisterNoDecoderWithoutJackson() {
        application.withClassLoader(new FilteredClassLoader(JsonMapper.class))
            .run(context -> assertThat(context).hasNotFailed().doesNotHaveBean(ShellyMqttDecoder.class)
                .hasSingleBean(ShellyRpcClient.class));
    }

    @Test
    void shouldRegisterNoDecoderWithoutAMapper() {
        new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(ShellyClientAutoConfiguration.class, WebClientAutoConfiguration.class))
            .run(context -> assertThat(context).hasNotFailed().doesNotHaveBean(ShellyMqttDecoder.class)
                .hasSingleBean(ShellyRpcClient.class));
    }

    // what the service declares itself wins - other timeouts for the clients, a client of its own
    @Test
    void shouldLeaveTheBeansOfTheApplicationAlone() {
        application.withUserConfiguration(BeansOfTheApplication.class).run(context -> {
            assertThat(context).hasNotFailed().hasSingleBean(ShellyTimeouts.class)
                .hasSingleBean(ShellyRpcClient.class).hasSingleBean(ShellyGen1Client.class);
            assertThat(context.getBean(ShellyTimeouts.class)).isSameAs(BeansOfTheApplication.TIMEOUTS);
            assertThat(context.getBean(ShellyRpcClient.class)).isSameAs(BeansOfTheApplication.RPC);
        });
    }

    // the file Spring Boot finds the auto-configuration through
    @Test
    void shouldBeListedForSpringBoot() throws Exception {
        try (var imports = getClass()
            .getResourceAsStream("/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports")) {
            assertThat(imports).isNotNull();
            assertThat(new String(imports.readAllBytes()).strip())
                .isEqualTo(ShellyClientAutoConfiguration.class.getName());
        }
    }

    @Configuration(proxyBeanMethods = false)
    static class MapperOfTheApplication {

        @Bean
        JsonMapper jsonMapper() {
            return JsonMapper.builder().build();
        }
    }

    @Configuration(proxyBeanMethods = false)
    static class BeansOfTheApplication {

        static final ShellyTimeouts TIMEOUTS = new ShellyTimeouts(Duration.ofSeconds(1), Duration.ofSeconds(30));
        static final ShellyRpcClient RPC = new ShellyRpcClient(WebClient.builder(), TIMEOUTS);

        @Bean
        ShellyTimeouts timeoutsOfTheApplication() {
            return TIMEOUTS;
        }

        @Bean
        ShellyRpcClient rpcClientOfTheApplication() {
            return RPC;
        }
    }
}
