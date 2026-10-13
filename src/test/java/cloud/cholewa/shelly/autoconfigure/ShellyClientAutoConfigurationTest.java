package cloud.cholewa.shelly.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import cloud.cholewa.shelly.codec.ShellyMqttDecoder;
import cloud.cholewa.shelly.http.ShellyGen1Client;
import cloud.cholewa.shelly.http.ShellyHttpClientCustomizer;
import cloud.cholewa.shelly.http.ShellyRpcClient;
import cloud.cholewa.shelly.http.ShellyTimeouts;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.jackson.autoconfigure.JacksonAutoConfiguration;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.webclient.autoconfigure.WebClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import tools.jackson.databind.json.JsonMapper;

class ShellyClientAutoConfigurationTest {

    // An application with WebFlux and Jackson - what every WebFlux service of the
    // organization is. The builder and the mapper come from Spring Boot's own
    // auto-configurations, which this one names by their class names to come after: with the
    // real ones here, a name that no longer exists shows as a missing bean.
    private final ApplicationContextRunner application = new ApplicationContextRunner().withConfiguration(
        AutoConfigurations.of(ShellyClientAutoConfiguration.class, WebClientAutoConfiguration.class,
            JacksonAutoConfiguration.class));

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

    // An application without WebFlux - none of the organization's is one today: no client
    // and no failure. A WebFlux service that wants the models alone switches the library off.
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
        new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(ShellyClientAutoConfiguration.class, JacksonAutoConfiguration.class))
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

    // A second mapper or builder of the application, none of them primary: there is no one
    // to take, so that part is left out - the application still starts. With a primary there is.
    @Test
    void shouldLeaveAPartOutWhenThereIsNoOneBeanToBuildItOn() {
        application.withUserConfiguration(TwoMappers.class, TwoBuilders.class)
            .run(context -> assertThat(context).hasNotFailed().doesNotHaveBean(ShellyMqttDecoder.class)
                .doesNotHaveBean(ShellyRpcClient.class).doesNotHaveBean(ShellyGen1Client.class));
    }

    @Test
    void shouldTakeThePrimaryOfSeveralMappers() {
        application.withUserConfiguration(SecondMapper.class).run(
            context -> assertThat(context).hasNotFailed().hasSingleBean(ShellyMqttDecoder.class));
    }

    // the request log of the service: every customizer of the application, on both clients
    @Test
    void shouldGiveTheClientsTheCustomizersOfTheApplication() {
        application.withUserConfiguration(CustomizerOfTheApplication.class).run(context -> {
            assertThat(context).hasNotFailed().hasSingleBean(ShellyRpcClient.class)
                .hasSingleBean(ShellyGen1Client.class);
            assertThat(CustomizerOfTheApplication.APPLIED).hasValue(2);
        });
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
    static class TwoMappers {

        @Bean
        JsonMapper strictMapper() {
            return JsonMapper.builder().build();
        }

        @Bean
        JsonMapper lenientMapper() {
            return JsonMapper.builder().build();
        }
    }

    @Configuration(proxyBeanMethods = false)
    static class TwoBuilders {

        @Bean
        WebClient.Builder oneBuilder() {
            return WebClient.builder();
        }

        @Bean
        WebClient.Builder anotherBuilder() {
            return WebClient.builder();
        }
    }

    // next to the mapper of Spring Boot, which is the primary one
    @Configuration(proxyBeanMethods = false)
    static class SecondMapper {

        @Bean
        JsonMapper lenientMapper() {
            return JsonMapper.builder().build();
        }
    }

    @Configuration(proxyBeanMethods = false)
    static class CustomizerOfTheApplication {

        static final AtomicInteger APPLIED = new AtomicInteger();

        @Bean
        ShellyHttpClientCustomizer requestLog() {
            return http -> {
                APPLIED.incrementAndGet();
                return http;
            };
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
