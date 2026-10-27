package cloud.cholewa.shelly.codec;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import cloud.cholewa.shelly.model.BthomeDeviceStatus;
import cloud.cholewa.shelly.model.BthomeSensorStatus;
import cloud.cholewa.shelly.model.CoverStatus;
import cloud.cholewa.shelly.model.DevicePowerStatus;
import cloud.cholewa.shelly.model.EmDataStatus;
import cloud.cholewa.shelly.model.EmStatus;
import cloud.cholewa.shelly.model.Gen2LightStatus;
import cloud.cholewa.shelly.model.HumidityStatus;
import cloud.cholewa.shelly.model.IlluminanceStatus;
import cloud.cholewa.shelly.model.NotifyEvent;
import cloud.cholewa.shelly.model.NotifyFullStatus;
import cloud.cholewa.shelly.model.NotifyStatus;
import cloud.cholewa.shelly.model.PresenceStatus;
import cloud.cholewa.shelly.model.RgbwStatus;
import cloud.cholewa.shelly.model.SwitchStatus;
import cloud.cholewa.shelly.model.TemperatureStatus;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.json.JsonMapper;

/**
 * The decoder on what the devices of the house publish: the routing keys the broker showed
 * (HAS-220) and bodies of the shape read from the devices, with invented ids and values.
 */
class ShellyMqttDecoderTest {

    private static final String DEVICE = "shellyplus2pm-a1b2c3d4e5f6";

    private final ShellyMqttDecoder sut = new ShellyMqttDecoder(JsonMapper.builder().build());

    static Stream<Arguments> statuses() {
        return Stream.of(
            Arguments.of("switch:2", "gen2/switch-status.json", SwitchStatus.class),
            Arguments.of("cover:0", "gen2/cover-status.json", CoverStatus.class),
            Arguments.of("light:0", "gen2/light-status.json", Gen2LightStatus.class),
            Arguments.of("rgb:0", "gen2/rgb-status.json", RgbwStatus.class),
            Arguments.of("rgbw:0", "gen2/rgbw-status.json", RgbwStatus.class),
            Arguments.of("presencezone:200", "gen2/presencezone-status.json", PresenceStatus.class),
            Arguments.of("illuminance:0", "gen2/illuminance-status.json", IlluminanceStatus.class),
            Arguments.of("temperature:0", "gen2/temperature-status.json", TemperatureStatus.class),
            Arguments.of("humidity:0", "gen2/humidity-status.json", HumidityStatus.class),
            Arguments.of("devicepower:0", "gen2/devicepower-status.json", DevicePowerStatus.class),
            Arguments.of("bthomedevice:200", "gen2/bthomedevice-status.json", BthomeDeviceStatus.class),
            Arguments.of("bthomesensor:201", "gen2/bthomesensor-status-number.json", BthomeSensorStatus.class),
            Arguments.of("em:0", "gen2/em-status.json", EmStatus.class),
            Arguments.of("emdata:0", "gen2/emdata-status.json", EmDataStatus.class));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("statuses")
    void shouldDecodeTheStatusOfAComponentIntoItsModel(String component, String sample, Class<?> model) {
        Optional<ShellyEvent> event = sut.decode(DEVICE + ".status." + component, sample(sample));

        assertThat(event).get().isInstanceOf(ShellyEvent.Status.class);
        ShellyEvent.Status status = (ShellyEvent.Status) event.get();
        assertThat(status.deviceId()).isEqualTo(DEVICE);
        assertThat(status.component() + ":" + status.index()).isEqualTo(component);
        assertThat(status.status()).isInstanceOf(model);
        assertThat(status.as(model)).isPresent();
    }

    @Test
    void shouldDecodeEveryComponentItSaysItDecodes() {
        assertThat(statuses().map(arguments -> ((String) arguments.get()[0]).split(":")[0]))
            .containsExactlyInAnyOrderElementsOf(ShellyMqttDecoder.decodedComponents());
    }

    // the whole component, as a switch repeats it about once a minute
    @Test
    void shouldGiveTheStatusWithItsValues() {
        ShellyEvent.Status event = (ShellyEvent.Status) sut
            .decode("shellypro4pm-a1b2c3d4e5f6.status.switch:2", sample("gen2/switch-status.json")).orElseThrow();

        SwitchStatus output = event.as(SwitchStatus.class).orElseThrow();
        assertThat(output.getOutput()).isTrue();
        assertThat(output.getSource()).isEqualTo("init");
        assertThat(output.getApower()).isEqualTo(41.3);
        assertThat(event.index()).isEqualTo(2);
        // asked for as the model of another component, it is nothing - not a cast that fails
        assertThat(event.as(CoverStatus.class)).isEmpty();
    }

    // the three-phase meter at the grid entry (HAS-327): the readings about every 5 s, the counters once a minute
    @Test
    void shouldGiveTheReadingsAndTheCountersOfAThreePhaseMeter() {
        String meter = "shellypro3em-a1b2c3d4e5f6";

        ShellyEvent.Status readings = (ShellyEvent.Status) sut
            .decode(meter + ".status.em:0", sample("gen2/em-status.json")).orElseThrow();
        assertThat(readings.deviceId()).isEqualTo(meter);
        assertThat(readings.as(EmStatus.class)).get().extracting(EmStatus::getTotalActPower).isEqualTo(-113.5);

        ShellyEvent.Status counters = (ShellyEvent.Status) sut
            .decode(meter + ".status.emdata:0", sample("gen2/emdata-status.json")).orElseThrow();
        assertThat(counters.as(EmDataStatus.class)).get().extracting(EmDataStatus::getTotalActRet)
            .isEqualTo(2158851.5);
        assertThat(counters.as(EmStatus.class)).isEmpty();
    }

    // the topic of the device and the routing key of the broker are the same message
    @Test
    void shouldDecodeTheTopicOfTheDeviceAsWell() {
        Optional<ShellyEvent> event = sut.decode(DEVICE + "/status/cover:0", sample("gen2/cover-status-moving.json"));

        assertThat(event).get().isInstanceOfSatisfying(ShellyEvent.Status.class,
            status -> assertThat(status.as(CoverStatus.class)).get().extracting(CoverStatus::getTargetPos)
                .isEqualTo(30));
    }

    @Test
    void shouldDecodeAParsedTopicSoThatTheRegistryIsAskedFirst() {
        ShellyTopic topic = ShellyTopic.parse(DEVICE + ".status.cover:0").orElseThrow();

        assertThat(topic.deviceId()).isEqualTo(DEVICE);
        assertThat(sut.decode(topic, sample("gen2/cover-status.json"))).get()
            .isInstanceOf(ShellyEvent.Status.class);
    }

    @ParameterizedTest
    @ValueSource(strings = { "true", "false", " true\n" })
    void shouldDecodeOnline(String body) {
        assertThat(sut.decode(DEVICE + ".online", body.getBytes(StandardCharsets.UTF_8)))
            .contains(new ShellyEvent.Online(DEVICE, Boolean.parseBoolean(body.strip())));
    }

    // What says nothing about the house: no model, no event - and the body is never read,
    // which the bodies that are no JSON show.
    @ParameterizedTest
    @ValueSource(strings = { "status.sys", "status.mqtt", "status.cloud", "status.wifi", "status.ble",
        "status.bthome", "status.plugs_ui", "status.pm1:0", "status.em1:0", "status.em1data:0", "status.input:0",
        "status.number:201", "status.presence", "status.modbus", "rpc", "status", "debug.log",
        // a component that has a model, without its id: not that component
        "status.temperature", "status.switch", "status.switch:x" })
    void shouldSkipWhatHasNoModelWithoutReadingTheBody(String key) {
        assertThat(sut.decode(DEVICE + "." + key, "not json at all".getBytes(StandardCharsets.UTF_8))).isEmpty();
        assertThat(sut.decode(DEVICE + "." + key, null)).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "online", ".status.switch:0" })
    void shouldSkipAKeyThatIsNotOfADevice(String key) {
        assertThat(sut.decode(key, sample("gen2/switch-status.json"))).isEmpty();
        assertThat(sut.decode((String) null, sample("gen2/switch-status.json"))).isEmpty();
    }

    // "{}" decodes into a switch of nulls, which a consumer would read as "nothing known" at
    // best and "off" at worst. The message names the topic and never the body.
    @ParameterizedTest
    @ValueSource(strings = { "", "{}", "null", "true", "[]", "{\"output\":true}", "not json", "{\"id\":0,\"output\":\"" })
    void shouldRefuseABodyThatIsNotTheStatusOfTheComponent(String body) {
        assertThatThrownBy(() -> sut.decode(DEVICE + ".status.switch:0", body.getBytes(StandardCharsets.UTF_8)))
            .isInstanceOf(ShellyDecodeException.class).hasMessageContaining("switch:0 of " + DEVICE)
            .satisfies(failure -> {
                if (!body.isEmpty()) {
                    assertThat(failure.getMessage()).doesNotContain(body);
                }
            });
    }

    // the topic says channel 1 and the body channel 0: handed on, a consumer would switch
    // its picture of the wrong channel
    @Test
    void shouldRefuseAStatusOfAnotherChannelThanItsTopic() {
        assertThatThrownBy(() -> sut.decode("shellypro4pm-a1b2c3d4e5f6.status.switch:1", sample("gen2/switch-status.json")))
            .isInstanceOf(ShellyDecodeException.class).hasMessageContaining("switch:1");
    }

    @Test
    void shouldRefuseAStatusWithoutABody() {
        assertThatThrownBy(() -> sut.decode(DEVICE + ".status.switch:0", null))
            .isInstanceOf(ShellyDecodeException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "1", "yes", "{\"online\":true}", "TRUE" })
    void shouldRefuseOnlineThatIsNeitherTrueNorFalse(String body) {
        assertThatThrownBy(() -> sut.decode(DEVICE + ".online", body.getBytes(StandardCharsets.UTF_8)))
            .isInstanceOf(ShellyDecodeException.class).hasMessageContaining(DEVICE);
        assertThatThrownBy(() -> sut.decode(DEVICE + ".online", null)).isInstanceOf(ShellyDecodeException.class);
    }

    // a device sends more than a model has; a mapper told to fail on that is not obeyed here
    @Test
    void shouldIgnoreUnknownFieldsWhateverTheMapperOfTheConsumerSays() {
        JsonMapper strict = JsonMapper.builder().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).build();

        assertThat(new ShellyMqttDecoder(strict).decode(DEVICE + ".status.switch:2", sample("gen2/switch-status.json")))
            .isPresent();
        // and the mapper of the consumer is as strict as it was
        assertThat(strict.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)).isTrue();
    }

    // The house does not get these - its devices have the RPC over MQTT off - but a device
    // that has it on publishes them on events/rpc.
    @Test
    void shouldDecodeTheNotificationsByTheirMethod() {
        assertThat(sut.decode(DEVICE + ".events.rpc", sample("rpc/notify-status.json"))).get()
            .isInstanceOfSatisfying(ShellyEvent.Notification.class, notification -> {
                assertThat(notification.deviceId()).isEqualTo(DEVICE);
                assertThat(notification.method()).isEqualTo("NotifyStatus");
                assertThat(notification.as(NotifyStatus.class)).get()
                    .satisfies(frame -> assertThat(frame.getParams()).containsKeys("ts", "cover:0"));
                assertThat(notification.as(NotifyEvent.class)).isEmpty();
            });

        assertThat(sut.decode(DEVICE + ".events.rpc", sample("rpc/notify-full-status.json"))).get()
            .isInstanceOfSatisfying(ShellyEvent.Notification.class,
                notification -> assertThat(notification.frame()).isInstanceOf(NotifyFullStatus.class));

        assertThat(sut.decode(DEVICE + ".events.rpc", sample("rpc/notify-event.json"))).get()
            .isInstanceOfSatisfying(ShellyEvent.Notification.class,
                notification -> assertThat(notification.as(NotifyEvent.class)).get()
                    .satisfies(frame -> assertThat(frame.getParams().getEvents()).hasSize(2)));
    }

    @Test
    void shouldSkipANotificationOfAMethodItHasNoModelFor() {
        byte[] frame = "{\"src\":\"x\",\"method\":\"NotifySomethingNew\"}".getBytes(StandardCharsets.UTF_8);

        assertThat(sut.decode(DEVICE + ".events.rpc", frame)).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "{}", "null", "[]", "not json", "{\"method\":7}", "{\"method\":null}",
        "{\"method\":\"NotifyEvent\",\"params\":[]}",
        // a frame without its params would decode into a model with nothing, or a null, in it
        "{\"method\":\"NotifyStatus\"}", "{\"method\":\"NotifyEvent\"}", "{\"method\":\"NotifyEvent\",\"params\":null}",
        "{\"method\":\"NotifyFullStatus\",\"params\":\"x\"}" })
    void shouldRefuseABodyThatIsNotANotification(String body) {
        assertThatThrownBy(() -> sut.decode(DEVICE + ".events.rpc", body.getBytes(StandardCharsets.UTF_8)))
            .isInstanceOf(ShellyDecodeException.class).hasMessageContaining(DEVICE);
    }

    private static byte[] sample(String name) {
        try (InputStream json = ShellyMqttDecoderTest.class.getResourceAsStream("/samples/" + name)) {
            if (json == null) {
                throw new IllegalArgumentException("No sample: " + name);
            }
            return json.readAllBytes();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
