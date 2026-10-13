package cloud.cholewa.shelly.codec;

import static org.assertj.core.api.Assertions.assertThat;

import cloud.cholewa.shelly.codec.ShellyTopic.Kind;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

/** The keys are those the broker of the house showed, with invented device ids. */
class ShellyTopicTest {

    @ParameterizedTest
    @CsvSource(nullValues = "-", value = {
        "shellyplus1pm-a1b2c3d4e5f6.status.switch:0, shellyplus1pm-a1b2c3d4e5f6, switch, 0",
        "shellypro4pm-a1b2c3d4e5f6.status.switch:3, shellypro4pm-a1b2c3d4e5f6, switch, 3",
        "shellyplus2pm-a1b2c3d4e5f6.status.cover:0, shellyplus2pm-a1b2c3d4e5f6, cover, 0",
        "shellypresence-a1b2c3d4e5f6.status.presencezone:200, shellypresence-a1b2c3d4e5f6, presencezone, 200",
        // components there is one of have no id
        "shellyplus1pm-a1b2c3d4e5f6.status.sys, shellyplus1pm-a1b2c3d4e5f6, sys, -",
        "shellyplugsg3-a1b2c3d4e5f6.status.plugs_ui, shellyplugsg3-a1b2c3d4e5f6, plugs_ui, -",
        // not every id starts with shelly, and the case is the device's
        "neowatervalve-a1b2c3d4e5f6.status.number:201, neowatervalve-a1b2c3d4e5f6, number, 201",
        "ShellyWallDisplay-000000A1B2C3.status.switch:0, ShellyWallDisplay-000000A1B2C3, switch, 0" })
    void shouldReadTheStatusKeyOfAComponent(String key, String deviceId, String component, Integer index) {
        assertThat(ShellyTopic.parse(key)).contains(new ShellyTopic(deviceId, Kind.STATUS, component, index));
    }

    // the topic as the device publishes it, before the broker turns the slashes into dots
    @Test
    void shouldReadTheTopicOfTheDeviceAsWell() {
        assertThat(ShellyTopic.parse("shellyplus2pm-a1b2c3d4e5f6/status/cover:0"))
            .contains(new ShellyTopic("shellyplus2pm-a1b2c3d4e5f6", Kind.STATUS, "cover", 0));
        assertThat(ShellyTopic.parse("shellyplus2pm-a1b2c3d4e5f6/online"))
            .contains(new ShellyTopic("shellyplus2pm-a1b2c3d4e5f6", Kind.ONLINE, null, null));
    }

    @Test
    void shouldTellOnlineAndTheNotificationsApart() {
        assertThat(ShellyTopic.parse("shellyplus1pm-a1b2c3d4e5f6.online"))
            .contains(new ShellyTopic("shellyplus1pm-a1b2c3d4e5f6", Kind.ONLINE, null, null));
        assertThat(ShellyTopic.parse("shellyplus1pm-a1b2c3d4e5f6.events.rpc"))
            .contains(new ShellyTopic("shellyplus1pm-a1b2c3d4e5f6", Kind.RPC_EVENTS, null, null));
    }

    // what else a device publishes keeps its device id, so the registry can still be asked
    @ParameterizedTest
    @CsvSource({
        "shellyplus1pm-a1b2c3d4e5f6.rpc, rpc",
        "shellyplus1pm-a1b2c3d4e5f6.status, status",
        "shellyplus1pm-a1b2c3d4e5f6.status., status.",
        "shellyplus1pm-a1b2c3d4e5f6.debug.log, debug.log",
        "shellyplus1pm-a1b2c3d4e5f6.onlinex, onlinex" })
    void shouldKeepAnyOtherKeyAsOther(String key, String rest) {
        assertThat(ShellyTopic.parse(key))
            .contains(new ShellyTopic("shellyplus1pm-a1b2c3d4e5f6", Kind.OTHER, rest, null));
    }

    // nothing is guessed: what follows the colon and is no number stays a part of the name
    @ParameterizedTest
    @CsvSource({ "id.status.switch:x, switch:x", "id.status.switch:, switch:", "id.status.:0, :0",
        "id.status.switch:-1, switch:-1", "id.status.switch:99999999999, switch:99999999999" })
    void shouldNotInventAnIndex(String key, String component) {
        assertThat(ShellyTopic.parse(key)).contains(new ShellyTopic("id", Kind.STATUS, component, null));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "", "online", ".online", "shellyplus1pm-a1b2c3d4e5f6.", "." })
    void shouldReadNothingFromAKeyWithoutADeviceAndATopic(String key) {
        assertThat(ShellyTopic.parse(key)).isEmpty();
    }
}
