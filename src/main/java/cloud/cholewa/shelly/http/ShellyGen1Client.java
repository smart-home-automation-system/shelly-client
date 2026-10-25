package cloud.cholewa.shelly.http;

import cloud.cholewa.shelly.model.Gen1DeviceStatus;
import cloud.cholewa.shelly.model.Gen1LightStatus;
import cloud.cholewa.shelly.model.Gen1Meter;
import cloud.cholewa.shelly.model.MotionStatus;
import cloud.cholewa.shelly.model.Relay;
import cloud.cholewa.shelly.model.RollerStatus;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/**
 * The HTTP API of a Gen1 device: a {@code GET} per call, the command in the query.
 * <p>
 * Every method takes the address of the device - a host or {@code host:port}, as the device
 * registry holds it - and the index of the roller, light or relay. A command answers with the
 * status of what it commanded, as the device gives it right after: for a roller that is the
 * start of the movement, not its end. A call ends with that answer or with a
 * {@link ShellyCallException} - a {@link ShellyAddressException} for an address that is none.
 * Nothing is retried.
 */
public class ShellyGen1Client {

    /** What a roller is told to do. */
    public enum RollerCommand {
        OPEN("open"), CLOSE("close"), STOP("stop");

        private final String go;

        RollerCommand(String go) {
            this.go = go;
        }
    }

    private static final Predicate<RollerStatus> IS_ROLLER = roller -> roller.getState() != null;
    private static final Predicate<Gen1LightStatus> IS_LIGHT = light -> light.getIson() != null;
    private static final Predicate<Relay> IS_RELAY = relay -> relay.getIson() != null;
    private static final Predicate<Gen1Meter> IS_METER = meter -> meter.getIsValid() != null;

    private final ShellyHttp http;

    public ShellyGen1Client(WebClient.Builder builder, ShellyTimeouts timeouts) {
        this(builder, timeouts, UnaryOperator.identity());
    }

    /**
     * @param customizer
     *            what the consumer wants on the HTTP client of the calls - its request log.
     *            The timeouts are set after it.
     */
    public ShellyGen1Client(WebClient.Builder builder, ShellyTimeouts timeouts,
            UnaryOperator<HttpClient> customizer) {
        this.http = new ShellyHttp(builder, timeouts, customizer);
    }

    /**
     * {@code GET /status} of a device with rollers, lights or relays. Every Gen1 device says
     * how it is connected, so an answer without {@code wifi_sta} is not one.
     */
    public Mono<Gen1DeviceStatus> status(String address) {
        return get(address, "/status", Map.of(), Gen1DeviceStatus.class, status -> status.getWifiSta() != null);
    }

    /** {@code GET /status} of a Shelly Motion. */
    public Mono<MotionStatus> motionStatus(String address) {
        return get(address, "/status", Map.of(), MotionStatus.class, status -> status.getSensor() != null);
    }

    public Mono<RollerStatus> roller(String address, int index, RollerCommand command) {
        return get(address, "/roller/" + index, Map.of("go", command.go), RollerStatus.class, IS_ROLLER);
    }

    /**
     * @param position
     *            0 (closed) to 100 (open). A roller that was not calibrated does not move:
     *            {@code positioning} of its status says whether it was.
     */
    public Mono<RollerStatus> rollerToPosition(String address, int index, int position) {
        Map<String, Object> query = new LinkedHashMap<>();
        query.put("go", "to_pos");
        query.put("roller_pos", position);
        return get(address, "/roller/" + index, query, RollerStatus.class, IS_ROLLER);
    }

    public Mono<RollerStatus> rollerStatus(String address, int index) {
        return get(address, "/roller/" + index, Map.of(), RollerStatus.class, IS_ROLLER);
    }

    /**
     * {@code /light} - a bulb, a Duo, a dimmer. A parameter left {@code null} is not sent, and
     * the device leaves that part as it is.
     *
     * @param brightness
     *            0 to 100
     */
    public Mono<Gen1LightStatus> light(String address, int index, Boolean on, Integer brightness) {
        Map<String, Object> query = new LinkedHashMap<>();
        query.put("turn", turn(on));
        query.put("brightness", brightness);
        return get(address, "/light/" + index, query, Gen1LightStatus.class, IS_LIGHT);
    }

    public Mono<Gen1LightStatus> lightToggle(String address, int index) {
        return get(address, "/light/" + index, Map.of("turn", "toggle"), Gen1LightStatus.class, IS_LIGHT);
    }

    public Mono<Gen1LightStatus> lightStatus(String address, int index) {
        return get(address, "/light/" + index, Map.of(), Gen1LightStatus.class, IS_LIGHT);
    }

    /**
     * {@code /color} - an RGBW2 in the mode color. A parameter left {@code null} is not sent.
     *
     * @param red
     *            0 to 255, as {@code green}, {@code blue} and {@code white}
     * @param gain
     *            0 to 100 - the level of the colors; this mode has no brightness
     */
    public Mono<Gen1LightStatus> color(String address, int index, Boolean on, Integer red, Integer green,
            Integer blue, Integer white, Integer gain) {
        Map<String, Object> query = new LinkedHashMap<>();
        query.put("turn", turn(on));
        query.put("red", red);
        query.put("green", green);
        query.put("blue", blue);
        query.put("white", white);
        query.put("gain", gain);
        return get(address, "/color/" + index, query, Gen1LightStatus.class, IS_LIGHT);
    }

    public Mono<Gen1LightStatus> colorStatus(String address, int index) {
        return get(address, "/color/" + index, Map.of(), Gen1LightStatus.class, IS_LIGHT);
    }

    public Mono<Relay> relay(String address, int index, boolean on) {
        return get(address, "/relay/" + index, Map.of("turn", turn(on)), Relay.class, IS_RELAY);
    }

    public Mono<Relay> relayStatus(String address, int index) {
        return get(address, "/relay/" + index, Map.of(), Relay.class, IS_RELAY);
    }

    /**
     * {@code GET /meter/<index>} - the power meter of a plug, index 0 for a Plug S. An answer is
     * one with {@code is_valid}: every meter says whether its reading holds, and a reading that
     * does not ({@code false}) is an answer the caller judges, not a failed call.
     */
    public Mono<Gen1Meter> meter(String address, int index) {
        return get(address, "/meter/" + index, Map.of(), Gen1Meter.class, IS_METER);
    }

    private <T> Mono<T> get(String address, String path, Map<String, ?> query, Class<T> model,
            Predicate<T> isAnswer) {
        return http.read(address, "GET " + path + sent(query), device -> http.client().get().uri(device.uri(path, query)),
            ParameterizedTypeReference.forType(model), isAnswer);
    }

    // the query as it is sent, for the message of a failure: what was left null is not in it
    private static String sent(Map<String, ?> query) {
        String sent = query.entrySet().stream().filter(parameter -> parameter.getValue() != null)
            .map(parameter -> parameter.getKey() + "=" + parameter.getValue()).collect(Collectors.joining("&"));
        return sent.isEmpty() ? "" : "?" + sent;
    }

    private static String turn(Boolean on) {
        return on == null ? null : on ? "on" : "off";
    }
}
