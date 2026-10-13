package cloud.cholewa.shelly.codec;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class ShellyGen1TopicTest {

    @ParameterizedTest
    @CsvSource(nullValues = "-", value = {
        "shellies/shellyswitch25-A1B2C3/roller/0, shellyswitch25-A1B2C3, roller, 0, -",
        "shellies/shellyswitch25-A1B2C3/roller/0/pos, shellyswitch25-A1B2C3, roller, 0, pos",
        "shellies/shellyswitch25-A1B2C3/roller/0/command, shellyswitch25-A1B2C3, roller, 0, command",
        "shellies/shellybulbduo-A1B2C3/light/0/status, shellybulbduo-A1B2C3, light, 0, status",
        "shellies/shellyrgbw2-A1B2C3/color/0/status, shellyrgbw2-A1B2C3, color, 0, status",
        "shellies/shellyplug-s-A1B2C3/relay/0, shellyplug-s-A1B2C3, relay, 0, -",
        "shellies/shellyplug-s-A1B2C3/relay/0/power, shellyplug-s-A1B2C3, relay, 0, power",
        "shellies/shellyswitch25-A1B2C3/online, shellyswitch25-A1B2C3, online, -, -",
        "shellies/shellyswitch25-A1B2C3/temperature, shellyswitch25-A1B2C3, temperature, -, -",
        // what lies deeper is kept as it came
        "shellies/shellyem-A1B2C3/emeter/1/returned/energy, shellyem-A1B2C3, emeter, 1, returned/energy",
        "shellies/shellymotionsensor-A1B2C3/status, shellymotionsensor-A1B2C3, status, -, -",
        "shellies/shellymotionsensor-A1B2C3/info/x, shellymotionsensor-A1B2C3, info, -, x" })
    void shouldTakeATopicApart(String topic, String deviceId, String component, Integer index, String attribute) {
        assertThat(ShellyGen1Topic.parse(topic))
            .contains(new ShellyGen1Topic(deviceId, component, index, attribute));
    }

    // as a routing key of the broker, with dots
    @ParameterizedTest
    @CsvSource(nullValues = "-", value = {
        "shellies.shellyswitch25-A1B2C3.roller.0.pos, roller, 0, pos",
        "shellies.shellyswitch25-A1B2C3.online, online, -, -" })
    void shouldReadARoutingKeyAsWell(String key, String component, Integer index, String attribute) {
        assertThat(ShellyGen1Topic.parse(key))
            .contains(new ShellyGen1Topic("shellyswitch25-A1B2C3", component, index, attribute));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "", "shellies", "shellies/announce", "shellies/shellyswitch25-A1B2C3",
        "shellies//roller/0", "shellies/shellyswitch25-A1B2C3/", "shellies/shellyswitch25-A1B2C3/roller//pos",
        "shellyplus1pm-a1b2c3d4e5f6/status/switch:0", "other/shellyswitch25-A1B2C3/roller/0" })
    void shouldReadNothingFromWhatIsNotATopicOfAGen1Device(String topic) {
        assertThat(ShellyGen1Topic.parse(topic)).isEmpty();
    }
}
