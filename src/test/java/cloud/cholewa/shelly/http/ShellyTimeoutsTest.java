package cloud.cholewa.shelly.http;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ShellyTimeoutsTest {

    @Test
    void shouldTakeTimeoutsBetweenASecondAndAMinute() {
        ShellyTimeouts shortest = new ShellyTimeouts(Duration.ofSeconds(1), Duration.ofSeconds(1));
        ShellyTimeouts longest = new ShellyTimeouts(Duration.ofSeconds(60), Duration.ofSeconds(60));

        assertThat(shortest.call()).isEqualTo(Duration.ofSeconds(2));
        assertThat(longest.call()).isEqualTo(Duration.ofSeconds(120));
    }

    // zero switches a timeout off, and "5000" meant as milliseconds is 83 minutes of seconds
    @ParameterizedTest
    @ValueSource(strings = { "PT0S", "PT0.999S", "PT-1S", "PT60.001S", "PT5000S" })
    void shouldRefuseATimeoutOutsideTheBounds(Duration timeout) {
        assertThatThrownBy(() -> new ShellyTimeouts(timeout, Duration.ofSeconds(5)))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("connect timeout")
            .hasMessageContaining(timeout.toString());
        assertThatThrownBy(() -> new ShellyTimeouts(Duration.ofSeconds(5), timeout))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("response timeout");
    }

    @Test
    void shouldRefuseAMissingTimeout() {
        assertThatThrownBy(() -> new ShellyTimeouts(null, Duration.ofSeconds(5)))
            .isInstanceOf(NullPointerException.class).hasMessageContaining("connect timeout");
        assertThatThrownBy(() -> new ShellyTimeouts(Duration.ofSeconds(5), null))
            .isInstanceOf(NullPointerException.class).hasMessageContaining("response timeout");
    }
}
