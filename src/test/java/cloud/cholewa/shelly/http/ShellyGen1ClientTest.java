package cloud.cholewa.shelly.http;

import static org.assertj.core.api.Assertions.assertThat;

import cloud.cholewa.shelly.http.ShellyGen1Client.RollerCommand;
import mockwebserver3.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

/**
 * The Gen1 client against a device on a local port. The answers of {@code /roller/0},
 * {@code /light/0}, {@code /relay/0} and {@code /status}, and the plain "Not Found" of an
 * index a device does not have, were read from the devices of the house (read-only calls).
 */
class ShellyGen1ClientTest {

    private static final String ROLLER = """
        {"state":"open","source":"http","power":118.4,"is_valid":true,"safety_switch":false,
         "overtemperature":false,"stop_reason":"normal","last_direction":"open","current_pos":40,
         "calibrating":false,"positioning":true}""";
    private static final String LIGHT = """
        {"ison":true,"source":"http","has_timer":false,"timer_started":0,"timer_duration":0,
         "timer_remaining":0,"brightness":60,"white":50,"temp":4750,"transition":0}""";
    private static final String COLOR = """
        {"ison":true,"source":"http","has_timer":false,"mode":"color","red":255,"green":120,"blue":0,
         "white":0,"gain":65,"effect":0,"transition":500,"power":7.25,"overpower":false}""";
    private static final String RELAY = """
        {"ison":true,"has_timer":false,"timer_started":0,"timer_duration":0,"timer_remaining":0,
         "overpower":false,"source":"http"}""";

    private final DeviceStub device = new DeviceStub();
    private final ShellyGen1Client sut = new ShellyGen1Client(WebClient.builder(), DeviceStub.TIMEOUTS);

    @AfterEach
    void tearDown() {
        device.close();
    }

    // the answer of a command is the status right after it: the roller has started, not arrived
    @ParameterizedTest
    @CsvSource({ "OPEN, open", "CLOSE, close", "STOP, stop" })
    void shouldTellARollerWhereToGo(RollerCommand command, String go) {
        device.answersJson(ROLLER);

        sut.roller(device.address(), 1, command).as(StepVerifier::create).assertNext(roller -> {
            assertThat(roller.getState()).isEqualTo("open");
            assertThat(roller.getCurrentPos()).isEqualTo(40);
        }).verifyComplete();

        RecordedRequest request = device.asked();
        assertThat(request.getMethod()).isEqualTo("GET");
        assertThat(request.getUrl().encodedPath()).isEqualTo("/roller/1");
        assertThat(request.getUrl().query()).isEqualTo("go=" + go);
    }

    @Test
    void shouldSendARollerToAPosition() {
        device.answersJson(ROLLER);

        sut.rollerToPosition(device.address(), 0, 30).as(StepVerifier::create).expectNextCount(1).verifyComplete();

        RecordedRequest request = device.asked();
        assertThat(request.getUrl().encodedPath()).isEqualTo("/roller/0");
        assertThat(request.getUrl().query()).isEqualTo("go=to_pos&roller_pos=30");
    }

    // without a query the device only answers how things are
    @Test
    void shouldReadARollerALightAndARelayWithoutCommandingThem() {
        device.answersJson(ROLLER);
        sut.rollerStatus(device.address(), 0).as(StepVerifier::create).expectNextCount(1).verifyComplete();
        RecordedRequest roller = device.asked();
        assertThat(roller.getUrl().encodedPath()).isEqualTo("/roller/0");
        assertThat(roller.getUrl().query()).isNull();

        device.answersJson(LIGHT);
        sut.lightStatus(device.address(), 0).as(StepVerifier::create)
            .assertNext(light -> assertThat(light.getBrightness()).isEqualTo(60)).verifyComplete();
        RecordedRequest light = device.asked();
        assertThat(light.getUrl().encodedPath()).isEqualTo("/light/0");
        assertThat(light.getUrl().query()).isNull();

        device.answersJson(COLOR);
        sut.colorStatus(device.address(), 0).as(StepVerifier::create)
            .assertNext(color -> assertThat(color.getGain()).isEqualTo(65)).verifyComplete();
        RecordedRequest color = device.asked();
        assertThat(color.getUrl().encodedPath()).isEqualTo("/color/0");
        assertThat(color.getUrl().query()).isNull();

        device.answersJson(RELAY);
        sut.relayStatus(device.address(), 1).as(StepVerifier::create)
            .assertNext(relay -> assertThat(relay.getIson()).isTrue()).verifyComplete();
        RecordedRequest relay = device.asked();
        assertThat(relay.getUrl().encodedPath()).isEqualTo("/relay/1");
        assertThat(relay.getUrl().query()).isNull();
    }

    @ParameterizedTest
    @CsvSource(nullValues = "-", value = {
        "true, 60, turn=on&brightness=60",
        "false, -, turn=off",
        // the brightness alone: the light stays on or off as it is
        "-, 25, brightness=25" })
    void shouldSetALightSendingOnlyWhatWasGiven(Boolean on, Integer brightness, String query) {
        device.answersJson(LIGHT);

        sut.light(device.address(), 0, on, brightness).as(StepVerifier::create).expectNextCount(1).verifyComplete();

        RecordedRequest request = device.asked();
        assertThat(request.getUrl().encodedPath()).isEqualTo("/light/0");
        assertThat(request.getUrl().query()).isEqualTo(query);
    }

    @Test
    void shouldToggleALight() {
        device.answersJson(LIGHT);

        sut.lightToggle(device.address(), 0).as(StepVerifier::create).expectNextCount(1).verifyComplete();

        assertThat(device.asked().getUrl().query()).isEqualTo("turn=toggle");
    }

    // an RGBW2 in the mode color: the level is gain, there is no brightness
    @Test
    void shouldSetTheColorsOfAnRgbw2() {
        device.answersJson(COLOR);

        sut.color(device.address(), 0, true, 255, 120, 0, null, 65).as(StepVerifier::create)
            .assertNext(color -> assertThat(color.getMode()).isEqualTo("color")).verifyComplete();

        RecordedRequest request = device.asked();
        assertThat(request.getUrl().encodedPath()).isEqualTo("/color/0");
        assertThat(request.getUrl().query()).isEqualTo("turn=on&red=255&green=120&blue=0&gain=65");
    }

    @ParameterizedTest
    @CsvSource({ "true, on", "false, off" })
    void shouldSwitchARelay(boolean on, String turn) {
        device.answersJson(RELAY);

        sut.relay(device.address(), 0, on).as(StepVerifier::create)
            .assertNext(relay -> assertThat(relay.getSource()).isEqualTo("http")).verifyComplete();

        RecordedRequest request = device.asked();
        assertThat(request.getUrl().encodedPath()).isEqualTo("/relay/0");
        assertThat(request.getUrl().query()).isEqualTo("turn=" + turn);
    }

    @Test
    void shouldReadTheStatusOfADevice() {
        device.answersSample("gen1/roller-status.json");

        sut.status(device.address()).as(StepVerifier::create).assertNext(status -> {
            assertThat(status.getRollers()).hasSize(1);
            assertThat(status.getRollers().get(0).getCurrentPos()).isEqualTo(100);
            assertThat(status.getRelays()).isEmpty();
        }).verifyComplete();

        RecordedRequest request = device.asked();
        assertThat(request.getUrl().encodedPath()).isEqualTo("/status");
        assertThat(request.getUrl().query()).isNull();
    }

    @Test
    void shouldReadTheStatusOfAMotionSensor() {
        device.answersSample("gen1/motion-status.json");

        sut.motionStatus(device.address()).as(StepVerifier::create).assertNext(status -> {
            assertThat(status.getSensor().getMotion()).isTrue();
            assertThat(status.getLux().getValue()).isEqualTo(23);
        }).verifyComplete();

        assertThat(device.asked().getUrl().encodedPath()).isEqualTo("/status");
    }

    // a reading the meter does not hold is an answer, judged by the caller
    @Test
    void shouldReadTheMeterOfAPlug() {
        device.answersSample("gen1/plug-meter.json");

        sut.meter(device.address(), 0).as(StepVerifier::create)
            .assertNext(meter -> assertThat(meter.getPower()).isEqualTo(482.27)).verifyComplete();
        RecordedRequest request = device.asked();
        assertThat(request.getUrl().encodedPath()).isEqualTo("/meter/0");
        assertThat(request.getUrl().query()).isNull();

        device.answersJson("{\"power\":0.00,\"is_valid\":false,\"total\":0}");
        sut.meter(device.address(), 0).as(StepVerifier::create)
            .assertNext(meter -> assertThat(meter.getIsValid()).isFalse()).verifyComplete();
    }

    // JSON without is_valid is not the meter - a relay answered, or a page of another device
    @Test
    void shouldFailAMeterAnswerThatIsNotOne() {
        device.answersJson(RELAY);

        sut.meter(device.address(), 0).as(StepVerifier::create).expectError(ShellyCallException.class).verify();
    }

    // A Gen1 device refuses in plain text - "Not Found" for a roller it does not have.
    @Test
    void shouldTellARefusalByItsStatus() {
        device.answers(404, "text/plain", "Not Found");

        sut.roller(device.address(), 9, RollerCommand.OPEN).as(StepVerifier::create).expectErrorSatisfies(failure -> {
            assertThat(failure).isExactlyInstanceOf(ShellyCallException.class)
                .hasMessageContaining("GET /roller/9?go=open on " + device.address())
                .hasMessageContaining("refused with the status 404");
            assertThat(((ShellyCallException) failure).httpStatus()).isEqualTo(404);
        }).verify();
    }

    // "{}" decodes into a relay of nulls, which would read as a relay that is off
    @ParameterizedTest
    @ValueSource(strings = { "", "{}", "null", "{\"unexpected\":true}", "[]" })
    void shouldFailACommandWhoseAnswerIsNotTheModel(String answer) {
        device.answersJson(answer);

        sut.relay(device.address(), 0, true).as(StepVerifier::create)
            .expectErrorSatisfies(failure -> assertThat(failure).isExactlyInstanceOf(ShellyCallException.class)
                .hasMessageContaining("GET /relay/0?turn=on on " + device.address()))
            .verify();
    }

    // the status of a Gen2+ device, or of anything else, is not the status of a Gen1 device
    @Test
    void shouldFailAStatusThatIsNotOfAGen1Device() {
        device.answersJson("{\"sys\":{\"uptime\":12},\"switch:0\":{\"id\":0,\"output\":true}}");

        sut.status(device.address()).as(StepVerifier::create).expectError(ShellyCallException.class).verify();
    }

    @Test
    void shouldFailACallWhoseAnswerIsNotJson() {
        device.answers(200, "text/html", "<html>Welcome to the hotel</html>");

        sut.rollerStatus(device.address(), 0).as(StepVerifier::create).expectError(ShellyCallException.class).verify();
    }

    @Test
    void shouldFailACallTheDeviceDoesNotAnswerInTime() {
        device.answersLate(RELAY);

        sut.relay(device.address(), 0, true).as(StepVerifier::create)
            .expectErrorSatisfies(failure -> assertThat(failure).isExactlyInstanceOf(ShellyCallException.class)
                .hasMessageContaining("failed"))
            .verify();
    }

    @Test
    void shouldFailACallToADeviceThatIsNotThere() {
        sut.status("localhost:1").as(StepVerifier::create).expectError(ShellyCallException.class).verify();
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "192.0.2.7/relay/1?turn=on&", "http://192.0.2.7", "192.0.2.7?turn=on" })
    void shouldRefuseAnAddressThatIsNotAHost(String address) {
        sut.relay(address, 0, false).as(StepVerifier::create)
            .expectErrorSatisfies(failure -> assertThat(failure).isExactlyInstanceOf(ShellyAddressException.class)
                .hasMessage("GET /relay/0?turn=off: not the address of a device (a host, or host:port)"))
            .verify();

        assertThat(device.calls()).isZero();
    }
}
