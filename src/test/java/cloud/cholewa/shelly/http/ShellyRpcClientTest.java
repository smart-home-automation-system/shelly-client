package cloud.cholewa.shelly.http;

import static org.assertj.core.api.Assertions.assertThat;

import cloud.cholewa.shelly.model.CoverStatus;
import cloud.cholewa.shelly.model.ShellyComponent;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;
import mockwebserver3.MockResponse;
import mockwebserver3.RecordedRequest;
import mockwebserver3.SocketEffect;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * The RPC client against a device on a local port. What a device answers - the status of a
 * cover, the two refusals, the "Content-Length required" - was read from the devices of the
 * house (read-only calls); what a command answers was not, since a command would have moved
 * something, and is the reason a command does not read its answer.
 */
class ShellyRpcClientTest {

    private final DeviceStub device = new DeviceStub();
    private final ShellyRpcClient sut = new ShellyRpcClient(WebClient.builder(), DeviceStub.TIMEOUTS);

    @AfterEach
    void tearDown() {
        device.close();
    }

    static Stream<Arguments> commands() {
        return Stream.of(
            command("Cover.Open", "{\"id\":0}", (sut, address) -> sut.coverOpen(address, 0)),
            command("Cover.Close", "{\"id\":0}", (sut, address) -> sut.coverClose(address, 0)),
            command("Cover.Stop", "{\"id\":0}", (sut, address) -> sut.coverStop(address, 0)),
            command("Cover.GoToPosition", "{\"id\":0,\"pos\":30}",
                (sut, address) -> sut.coverGoToPosition(address, 0, 30)),
            command("Switch.Set", "{\"id\":2,\"on\":true}", (sut, address) -> sut.switchSet(address, 2, true)),
            command("Switch.Set", "{\"id\":2,\"on\":false}", (sut, address) -> sut.switchSet(address, 2, false)),
            command("Light.Set", "{\"id\":0,\"on\":true,\"brightness\":40}",
                (sut, address) -> sut.lightSet(address, 0, true, 40)),
            // what is left null is not sent: the brightness alone leaves the output as it is
            command("Light.Set", "{\"id\":0,\"brightness\":40}", (sut, address) -> sut.lightSet(address, 0, null, 40)),
            command("Light.Set", "{\"id\":0,\"on\":false}", (sut, address) -> sut.lightSet(address, 0, false, null)),
            command("Light.Toggle", "{\"id\":0}", (sut, address) -> sut.lightToggle(address, 0)),
            command("RGB.Set", "{\"id\":0,\"on\":true,\"rgb\":[255,120,0],\"brightness\":80}",
                (sut, address) -> sut.rgbSet(address, 0, true, List.of(255, 120, 0), 80)),
            command("RGBW.Set", "{\"id\":0,\"on\":true,\"rgb\":[0,0,255],\"white\":128,\"brightness\":40}",
                (sut, address) -> sut.rgbwSet(address, 0, true, List.of(0, 0, 255), 128, 40)),
            command("RGBW.Set", "{\"id\":0,\"white\":10}", (sut, address) -> sut.rgbwSet(address, 0, null, null, 10, null)));
    }

    @ParameterizedTest(name = "{0} {1}")
    @MethodSource("commands")
    void shouldSendACommandAsAPostWithItsParametersAsJson(String method, String params, Call call) {
        device.answersJson("null");

        call.on(sut, device.address()).as(StepVerifier::create).verifyComplete();

        RecordedRequest request = device.asked();
        assertThat(request.getMethod()).isEqualTo("POST");
        assertThat(request.getUrl().encodedPath()).isEqualTo("/rpc/" + method);
        assertThat(request.getUrl().query()).isNull();
        assertThat(request.getHeaders().get("Content-Type")).startsWith("application/json");
        assertThat(request.getBody().utf8()).isEqualTo(params);
    }

    // a device refuses a POST without a Content-Length, so a call without a parameter still
    // has a body, and its length is announced
    @Test
    void shouldAnnounceTheLengthOfTheBodyAlsoWhenThereIsNoParameter() {
        device.answersJson("{\"sys\":{\"uptime\":12}}");

        sut.status(device.address()).as(StepVerifier::create).expectNextCount(1).verifyComplete();

        RecordedRequest request = device.asked();
        assertThat(request.getBody().utf8()).isEqualTo("{}");
        assertThat(request.getHeaders().get("Content-Length")).isEqualTo("2");
        assertThat(request.getHeaders().get("Transfer-Encoding")).isNull();
    }

    // Switch.Set answers {"was_on": ...}, a cover command answers null: a command is done
    // when the device says 2xx in JSON, whatever the JSON
    @ParameterizedTest
    @ValueSource(strings = { "null", "{\"was_on\":false}", "" })
    void shouldTakeAnyJsonAnswerOfACommandAsDone(String answer) {
        device.answersJson(answer);

        sut.switchSet(device.address(), 0, true).as(StepVerifier::create).verifyComplete();
    }

    @Test
    void shouldReadTheStatusOfACover() {
        device.answersSample("gen2/cover-status-moving.json");

        sut.coverStatus(device.address(), 0).as(StepVerifier::create).assertNext(cover -> {
            assertThat(cover.getState()).isEqualTo("closing");
            assertThat(cover.getCurrentPos()).isEqualTo(74);
            assertThat(cover.getTargetPos()).isEqualTo(30);
            assertThat(cover.getApower()).isEqualTo(118.6);
        }).verifyComplete();

        RecordedRequest request = device.asked();
        assertThat(request.getUrl().encodedPath()).isEqualTo("/rpc/Cover.GetStatus");
        assertThat(request.getBody().utf8()).isEqualTo("{\"id\":0}");
    }

    @Test
    void shouldReadTheStatusOfTheOtherComponents() {
        device.answersSample("gen2/switch-status.json");
        sut.switchStatus(device.address(), 2).as(StepVerifier::create)
            .assertNext(output -> assertThat(output.getVoltage()).isEqualTo(231.2)).verifyComplete();
        assertThat(device.asked().getUrl().encodedPath()).isEqualTo("/rpc/Switch.GetStatus");

        device.answersSample("gen2/light-status.json");
        sut.lightStatus(device.address(), 0).as(StepVerifier::create)
            .assertNext(light -> assertThat(light.getBrightness()).isEqualTo(80)).verifyComplete();
        assertThat(device.asked().getUrl().encodedPath()).isEqualTo("/rpc/Light.GetStatus");

        device.answersSample("gen2/rgb-status.json");
        sut.rgbStatus(device.address(), 0).as(StepVerifier::create)
            .assertNext(rgb -> assertThat(rgb.getRgb()).containsExactly(255, 120, 0)).verifyComplete();
        assertThat(device.asked().getUrl().encodedPath()).isEqualTo("/rpc/RGB.GetStatus");

        device.answersSample("gen2/rgbw-status.json");
        sut.rgbwStatus(device.address(), 0).as(StepVerifier::create)
            .assertNext(rgbw -> assertThat(rgbw.getWhite()).isEqualTo(128)).verifyComplete();
        assertThat(device.asked().getUrl().encodedPath()).isEqualTo("/rpc/RGBW.GetStatus");

        device.answersSample("gen2/bthomesensor-config.json");
        sut.bthomeSensorConfig(device.address(), 201).as(StepVerifier::create)
            .assertNext(config -> assertThat(config.getObjId()).isEqualTo(69)).verifyComplete();
        RecordedRequest config = device.asked();
        assertThat(config.getUrl().encodedPath()).isEqualTo("/rpc/BTHomeSensor.GetConfig");
        assertThat(config.getBody().utf8()).isEqualTo("{\"id\":201}");

        device.answersSample("gen2/bthomesensor-status-number.json");
        sut.bthomeSensorStatus(device.address(), 201).as(StepVerifier::create)
            .assertNext(status -> assertThat(status.getValue()).isEqualTo(21.6)).verifyComplete();
        assertThat(device.asked().getUrl().encodedPath()).isEqualTo("/rpc/BTHomeSensor.GetStatus");
    }

    // every component under its key, as a raw map: which there are differs by the device
    @Test
    void shouldReadTheStatusOfTheWholeDeviceAsRawComponents() {
        device.answersJson("{\"sys\":{\"uptime\":12},\"cover:0\":{\"id\":0,\"state\":\"open\"},\"wifi\":{\"rssi\":-52}}");

        sut.status(device.address()).as(StepVerifier::create).assertNext(status -> {
            assertThat(status).containsOnlyKeys("sys", "cover:0", "wifi");
            assertThat(status.get("cover:0")).asInstanceOf(org.assertj.core.api.InstanceOfAssertFactories.MAP)
                .containsEntry("state", "open");
        }).verifyComplete();

        assertThat(device.asked().getUrl().encodedPath()).isEqualTo("/rpc/Shelly.GetStatus");
    }

    @Test
    void shouldReadTheConfigurationOfABluetoothDevice() {
        device.answersSample("gen2/bthomedevice-config.json");

        sut.bthomeDeviceConfig(device.address(), 200).as(StepVerifier::create)
            .assertNext(config -> assertThat(config.getAddr()).isEqualTo("00:00:5e:00:53:02")).verifyComplete();

        RecordedRequest config = device.asked();
        assertThat(config.getUrl().encodedPath()).isEqualTo("/rpc/BTHomeDevice.GetConfig");
        assertThat(config.getBody().utf8()).isEqualTo("{\"id\":200}");
    }

    // a device answers a page at a time: the next one is asked from where the last one ended
    @Test
    void shouldReadEveryPageOfTheDynamicComponents() {
        device.answersSample("rpc/components-page-1.json");
        device.answersSample("rpc/components-page-2.json");

        sut.dynamicComponents(device.address()).as(StepVerifier::create)
            .assertNext(components -> assertThat(components).extracting(ShellyComponent::getKey)
                .containsExactly("bthomedevice:200", "bthomesensor:200", "bthomesensor:201"))
            .verifyComplete();

        RecordedRequest first = device.asked();
        assertThat(first.getUrl().encodedPath()).isEqualTo("/rpc/Shelly.GetComponents");
        assertThat(first.getBody().utf8()).isEqualTo("{\"dynamic_only\":true,\"include\":[\"status\",\"config\"],\"offset\":0}");
        assertThat(device.asked().getBody().utf8()).isEqualTo("{\"dynamic_only\":true,\"include\":[\"status\",\"config\"],\"offset\":2}");
        assertThat(device.calls()).isEqualTo(2);
    }

    @Test
    void shouldReadADeviceWithoutDynamicComponentsAsNone() {
        device.answersJson("{\"components\":[],\"cfg_rev\":26,\"offset\":0,\"total\":0}");

        sut.dynamicComponents(device.address()).as(StepVerifier::create)
            .assertNext(components -> assertThat(components).isEmpty()).verifyComplete();
        assertThat(device.calls()).isEqualTo(1);
    }

    // half a list would read as components that are not there; an empty page before the total
    // would otherwise ask for the same page for ever
    @Test
    void shouldFailTheWholeReadWhenAPageFails() {
        device.answersSample("rpc/components-page-1.json");
        device.answers(500, "application/json", "{\"code\":-1,\"message\":\"busy\"}");

        sut.dynamicComponents(device.address()).as(StepVerifier::create)
            .expectError(ShellyCallException.class).verify();
        assertThat(device.calls()).isEqualTo(2);
    }

    @Test
    void shouldFailOnAnEmptyPageBeforeTheTotal() {
        device.answersJson("{\"components\":[],\"cfg_rev\":26,\"offset\":0,\"total\":3}");

        sut.dynamicComponents(device.address()).as(StepVerifier::create)
            .expectErrorSatisfies(e -> assertThat(e).isInstanceOf(ShellyCallException.class)
                .hasMessageContaining("an empty page at 0 of 3"))
            .verify();
        assertThat(device.calls()).isEqualTo(1);
    }

    // a firmware that ignores the offset answers the first page again - asked for ever otherwise
    @Test
    void shouldFailOnAPageThatStartsElsewhereThanAsked() {
        device.answersSample("rpc/components-page-1.json");
        device.answersSample("rpc/components-page-1.json");

        sut.dynamicComponents(device.address()).as(StepVerifier::create)
            .expectErrorSatisfies(e -> assertThat(e).isInstanceOf(ShellyCallException.class)
                .hasMessageContaining("asked for the page at 2, answered 0"))
            .verify();
        assertThat(device.calls()).isEqualTo(2);
    }

    @Test
    void shouldFailWhenTheConfigurationChangesBetweenTwoPages() {
        device.answersSample("rpc/components-page-1.json");
        device.answersJson(DeviceStub.sample("rpc/components-page-2.json").replace("\"cfg_rev\": 27", "\"cfg_rev\": 28"));

        sut.dynamicComponents(device.address()).as(StepVerifier::create)
            .expectErrorSatisfies(e -> assertThat(e).isInstanceOf(ShellyCallException.class)
                .hasMessageContaining("the configuration changed between two pages"))
            .verify();
    }

    @Test
    void shouldFailWhenTheTotalChangesBetweenTwoPages() {
        device.answersSample("rpc/components-page-1.json");
        device.answersJson(DeviceStub.sample("rpc/components-page-2.json").replace("\"total\": 3", "\"total\": 4"));

        sut.dynamicComponents(device.address()).as(StepVerifier::create)
            .expectErrorSatisfies(e -> assertThat(e).hasMessageContaining("the configuration changed between two pages"))
            .verify();
    }

    // a device that claims more would be asked page after page
    @Test
    void shouldRefuseMoreComponentsThanTheLimit() {
        device.answersJson("{\"components\":[{\"key\":\"number:200\"}],\"cfg_rev\":1,\"offset\":0,\"total\":10000}");

        sut.dynamicComponents(device.address()).as(StepVerifier::create)
            .expectErrorSatisfies(e -> assertThat(e).isInstanceOf(ShellyCallException.class)
                .hasMessageContaining("10000 components, more than 256"))
            .verify();
        assertThat(device.calls()).isEqualTo(1);
    }

    // without cfg_rev a change between two pages could not be seen
    @Test
    void shouldFailAPageWithoutTheRevisionOfTheConfiguration() {
        device.answersJson("{\"components\":[],\"offset\":0,\"total\":0}");

        sut.dynamicComponents(device.address()).as(StepVerifier::create)
            .expectError(ShellyCallException.class).verify();
    }

    @Test
    void shouldFailAPageThatIsNotTheModel() {
        device.answersJson("{\"components\":[]}");

        sut.dynamicComponents(device.address()).as(StepVerifier::create)
            .expectError(ShellyCallException.class).verify();
    }

    @Test
    void shouldReadTheSupportedWebhooksAsARawMap() {
        device.answersJson("{\"types\":{\"cover.open\":{},\"cover.closed\":{}}}");

        sut.webhookListSupported(device.address()).as(StepVerifier::create)
            .assertNext(supported -> assertThat(supported).containsOnlyKeys("types")).verifyComplete();

        assertThat(device.asked().getUrl().encodedPath()).isEqualTo("/rpc/Webhook.ListSupported");
    }

    // The refusal of a device, as read from one: a 500 with the code and the words of the RPC.
    @Test
    void shouldTellARefusalOfTheDeviceWithItsCodeAndWords() {
        device.answers(500, "application/json", "{\"code\":-105,\"message\":\"Argument 'id', value 9 not found!\"}");

        sut.coverOpen(device.address(), 9).as(StepVerifier::create).expectErrorSatisfies(failure -> {
            assertThat(failure).isInstanceOf(ShellyRpcException.class);
            ShellyRpcException refusal = (ShellyRpcException) failure;
            assertThat(refusal.code()).isEqualTo(-105);
            assertThat(refusal.rpcMessage()).isEqualTo("Argument 'id', value 9 not found!");
            assertThat(refusal.httpStatus()).isEqualTo(500);
            assertThat(refusal).hasMessageContaining("Cover.Open on " + device.address())
                .hasMessageContaining("-105");
        }).verify();
    }

    @Test
    void shouldTellARefusalOfAReadTheSameWay() {
        device.answers(404, "application/json", "{\"code\":404,\"message\":\"No handler for Cover.GetStatus\"}");

        sut.coverStatus(device.address(), 0).as(StepVerifier::create)
            .expectErrorSatisfies(failure -> assertThat(failure).isInstanceOf(ShellyRpcException.class)
                .extracting(refusal -> ((ShellyRpcException) refusal).code()).isEqualTo(404))
            .verify();
    }

    // What answers is not always the device: a proxy or a Gen1 device on the address refuses
    // in text, and the status is all there is to tell.
    @ParameterizedTest
    @MethodSource("refusalsWithoutAnRpcError")
    void shouldTellARefusalThatIsNotAnRpcErrorByItsStatus(int status, String contentType, String body) {
        device.answers(status, contentType, body);

        sut.coverStatus(device.address(), 0).as(StepVerifier::create).expectErrorSatisfies(failure -> {
            assertThat(failure).isExactlyInstanceOf(ShellyCallException.class)
                .hasMessageContaining("refused with the status " + status);
            assertThat(((ShellyCallException) failure).httpStatus()).isEqualTo(status);
        }).verify();
    }

    static Stream<Arguments> refusalsWithoutAnRpcError() {
        return Stream.of(
            Arguments.of(404, "text/plain", "Not Found"),
            Arguments.of(400, null, "NOT_FOUND: Content-Length required but not present"),
            Arguments.of(502, "text/html", "<html>Bad Gateway</html>"),
            Arguments.of(500, "application/json", ""),
            Arguments.of(500, "application/json", "{\"unexpected\":true}"),
            Arguments.of(500, "application/json", "{\"code\":"));
    }

    // JSON of another shape decodes all the same, into a model of nulls - and would read as a
    // cover that is nowhere. An answer without a body is no answer either.
    @ParameterizedTest
    @ValueSource(strings = { "", "{}", "null", "{\"unexpected\":true}", "[]" })
    void shouldFailAReadWhoseAnswerIsNotTheModel(String answer) {
        device.answersJson(answer);

        sut.coverStatus(device.address(), 0).as(StepVerifier::create)
            .expectErrorSatisfies(failure -> assertThat(failure).isInstanceOf(ShellyCallException.class)
                .hasMessageContaining("Cover.GetStatus on " + device.address()))
            .verify();
    }

    @Test
    void shouldFailAReadWhoseAnswerIsNotJson() {
        device.answers(200, "text/html", "<html>Welcome to the hotel</html>");

        sut.coverStatus(device.address(), 0).as(StepVerifier::create).expectError(ShellyCallException.class).verify();
    }

    // a 200 with a page is a portal, not a device that did what it was told
    @Test
    void shouldFailACommandWhoseAnswerIsNotJson() {
        device.answers(200, "text/html", "<html>Welcome to the hotel</html>");

        sut.coverOpen(device.address(), 0).as(StepVerifier::create)
            .expectErrorSatisfies(failure -> assertThat(failure).isExactlyInstanceOf(ShellyCallException.class)
                .hasMessageContaining("not the answer of a Shelly"))
            .verify();
    }

    @Test
    void shouldFailACallTheDeviceDoesNotAnswerInTime() {
        device.answersLate("null");

        sut.coverOpen(device.address(), 0).as(StepVerifier::create)
            .expectErrorSatisfies(failure -> assertThat(failure).isExactlyInstanceOf(ShellyCallException.class)
                .hasMessageContaining("Cover.Open on " + device.address() + " failed").hasCauseInstanceOf(Exception.class))
            .verify();
    }

    @Test
    void shouldFailAReadWhoseAnswerBreaksOff() {
        device.enqueue(new MockResponse.Builder().code(200).addHeader("Content-Type", "application/json")
            .body(DeviceStub.sample("gen2/cover-status.json")).onResponseBody(new SocketEffect.CloseSocket())
            .build());

        sut.coverStatus(device.address(), 0).as(StepVerifier::create).expectError(ShellyCallException.class).verify();
    }

    @Test
    void shouldFailACallToADeviceThatIsNotThere() {
        sut.coverOpen("localhost:1", 0).as(StepVerifier::create)
            .expectErrorSatisfies(failure -> assertThat(failure).isExactlyInstanceOf(ShellyCallException.class)
                .hasMessageContaining("Cover.Open on localhost:1 failed"))
            .verify();
    }

    // The address comes from a registry somebody types into: it is a host, with a port or
    // without, and nothing that could lead the call elsewhere. A mistake of the registry is
    // told apart from a device that is down, and what was typed is not repeated - it is no
    // address, so it may be a password in front of an @.
    @ParameterizedTest
    @ValueSource(strings = { "", " ", "192.0.2.7/rpc/Shelly.Reboot?", "192.0.2.7#", "http://192.0.2.7",
        "admin:secret@192.0.2.7", "192.0.2.7:", "192.0.2.7:port", "192.0.2.7 ", "a b", "192.0.2.7?x=1", "[::1]",
        "-host", "host-" })
    void shouldRefuseAnAddressThatIsNotAHost(String address) {
        sut.coverOpen(address, 0).as(StepVerifier::create).expectErrorSatisfies(failure -> {
            assertThat(failure).isExactlyInstanceOf(ShellyAddressException.class)
                .hasMessage("Cover.Open: not the address of a device (a host, or host:port)");
            assertThat(((ShellyCallException) failure).httpStatus()).isNull();
        }).verify();

        assertThat(device.calls()).isZero();
    }

    @ParameterizedTest
    @ValueSource(strings = { "192.0.2.7:0", "192.0.2.7:65536", "192.0.2.7:99999" })
    void shouldRefuseAPortNoDeviceListensOn(String address) {
        sut.coverStatus(address, 0).as(StepVerifier::create)
            .expectErrorSatisfies(failure -> assertThat(failure).isExactlyInstanceOf(ShellyAddressException.class)
                .hasMessageContaining("not a port of a device"))
            .verify();
    }

    @Test
    void shouldRefuseAMissingAddress() {
        sut.coverStatus(null, 0).as(StepVerifier::create).expectError(ShellyAddressException.class).verify();
    }

    // What a service wants on the HTTP client of the calls - its request log - reaches the
    // device calls through the customizer; the timeouts are set after it and stay.
    @Test
    void shouldApplyTheCustomizerOfTheConsumerAndKeepTheTimeouts() {
        ShellyRpcClient customized = new ShellyRpcClient(WebClient.builder(), DeviceStub.TIMEOUTS,
            http -> http.headers(headers -> headers.set("X-Logged-By", "the-service"))
                .responseTimeout(java.time.Duration.ofMinutes(10)));

        device.answersJson("null");
        customized.coverOpen(device.address(), 0).as(StepVerifier::create).verifyComplete();
        assertThat(device.asked().getHeaders().get("X-Logged-By")).isEqualTo("the-service");

        device.answersLate("null");
        customized.coverOpen(device.address(), 0).as(StepVerifier::create).expectError(ShellyCallException.class)
            .verify(java.time.Duration.ofSeconds(5));
    }

    // nothing happens before somebody subscribes, and every subscription is a call of its own
    @Test
    void shouldCallTheDeviceOncePerSubscription() {
        Mono<Void> open = sut.coverOpen(device.address(), 0);
        assertThat(device.calls()).isZero();

        device.answersJson("null");
        device.answersJson("null");
        open.as(StepVerifier::create).verifyComplete();
        open.as(StepVerifier::create).verifyComplete();

        assertThat(device.calls()).isEqualTo(2);
    }

    // the builder of the consumer is cloned: what it carries reaches the device, and the
    // builder itself is left as it was
    @Test
    void shouldKeepWhatTheBuilderOfTheConsumerCarriesAndLeaveTheBuilderAlone() {
        WebClient.Builder builder = WebClient.builder().defaultHeader("X-From", "a-service");
        ShellyRpcClient client = new ShellyRpcClient(builder, DeviceStub.TIMEOUTS);
        device.answersJson("null");

        client.coverStop(device.address(), 0).as(StepVerifier::create).verifyComplete();
        assertThat(device.asked().getHeaders().get("X-From")).isEqualTo("a-service");

        // still the builder of the consumer: a client built from it afterwards is its own
        device.answersJson("{}");
        builder.build().get().uri("http://" + device.address() + "/else").retrieve().toBodilessEntity()
            .as(StepVerifier::create).expectNextCount(1).verifyComplete();
        assertThat(device.asked().getUrl().encodedPath()).isEqualTo("/else");
    }

    // a call that hangs longer than the two timeouts together is cut off even when the device
    // keeps the connection alive
    @Test
    void shouldBoundAWholeCall() {
        assertThat(DeviceStub.TIMEOUTS.call()).isEqualTo(java.time.Duration.ofSeconds(2));

        Mono<CoverStatus> never = Mono.never();
        new ShellyHttp(WebClient.builder(), DeviceStub.TIMEOUTS, http -> http).bounded(never, "Cover.GetStatus on x")
            .as(StepVerifier::create)
            .expectErrorSatisfies(failure -> assertThat(failure).isExactlyInstanceOf(ShellyCallException.class)
                .hasCauseInstanceOf(TimeoutException.class))
            .verify(java.time.Duration.ofSeconds(5));
    }

    @FunctionalInterface
    interface Call {
        Mono<Void> on(ShellyRpcClient sut, String address);
    }

    private static Arguments command(String method, String params, Call call) {
        return Arguments.of(method, params, call);
    }
}
