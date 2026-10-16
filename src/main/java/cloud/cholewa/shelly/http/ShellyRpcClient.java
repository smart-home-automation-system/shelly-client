package cloud.cholewa.shelly.http;

import cloud.cholewa.shelly.model.BthomeDeviceConfig;
import cloud.cholewa.shelly.model.BthomeSensorConfig;
import cloud.cholewa.shelly.model.BthomeSensorStatus;
import cloud.cholewa.shelly.model.CoverStatus;
import cloud.cholewa.shelly.model.Gen2LightStatus;
import cloud.cholewa.shelly.model.RgbwStatus;
import cloud.cholewa.shelly.model.ShellyComponent;
import cloud.cholewa.shelly.model.ShellyComponentsPage;
import cloud.cholewa.shelly.model.SwitchStatus;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/**
 * The RPC of a Gen2+ device over HTTP: {@code POST /rpc/<Method>} with the parameters as JSON.
 * <p>
 * Every method takes the address of the device - a host or {@code host:port}, as the device
 * registry holds it - and, for a component, its id (the channel). A call ends with the answer
 * or with a {@link ShellyCallException}; a refusal the device explained is a
 * {@link ShellyRpcException}, and an address that is none a {@link ShellyAddressException}.
 * Nothing is retried: a command sent twice is the caller's decision.
 */
public class ShellyRpcClient {

    private static final ParameterizedTypeReference<Map<String, Object>> COMPONENTS = new ParameterizedTypeReference<>() {
    };

    private final ShellyHttp http;

    public ShellyRpcClient(WebClient.Builder builder, ShellyTimeouts timeouts) {
        this(builder, timeouts, UnaryOperator.identity());
    }

    /**
     * @param customizer
     *            what the consumer wants on the HTTP client of the calls - its request log.
     *            The timeouts are set after it.
     */
    public ShellyRpcClient(WebClient.Builder builder, ShellyTimeouts timeouts,
            UnaryOperator<HttpClient> customizer) {
        this.http = new ShellyHttp(builder, timeouts, customizer);
    }

    public Mono<Void> coverOpen(String address, int id) {
        return command(address, "Cover.Open", params(id));
    }

    public Mono<Void> coverClose(String address, int id) {
        return command(address, "Cover.Close", params(id));
    }

    public Mono<Void> coverStop(String address, int id) {
        return command(address, "Cover.Stop", params(id));
    }

    /**
     * @param position
     *            0 (closed) to 100 (open). The device refuses it (-106) while the cover is not
     *            calibrated - {@code pos_control} of its status says whether it is.
     */
    public Mono<Void> coverGoToPosition(String address, int id, int position) {
        Map<String, Object> params = params(id);
        params.put("pos", position);
        return command(address, "Cover.GoToPosition", params);
    }

    public Mono<CoverStatus> coverStatus(String address, int id) {
        return read(address, "Cover.GetStatus", params(id), CoverStatus.class, status -> status.getState() != null);
    }

    public Mono<Void> switchSet(String address, int id, boolean on) {
        Map<String, Object> params = params(id);
        params.put("on", on);
        return command(address, "Switch.Set", params);
    }

    public Mono<SwitchStatus> switchStatus(String address, int id) {
        return read(address, "Switch.GetStatus", params(id), SwitchStatus.class, status -> status.getOutput() != null);
    }

    /**
     * @param on
     *            {@code null} leaves the output as it is
     * @param brightness
     *            0 to 100; {@code null} leaves it as it is
     */
    public Mono<Void> lightSet(String address, int id, Boolean on, Integer brightness) {
        Map<String, Object> params = params(id);
        put(params, "on", on);
        put(params, "brightness", brightness);
        return command(address, "Light.Set", params);
    }

    public Mono<Void> lightToggle(String address, int id) {
        return command(address, "Light.Toggle", params(id));
    }

    public Mono<Gen2LightStatus> lightStatus(String address, int id) {
        return read(address, "Light.GetStatus", params(id), Gen2LightStatus.class,
            status -> status.getOutput() != null);
    }

    /**
     * The component {@code rgb} - a Plus RGBW PM in the profile rgb. A parameter left
     * {@code null} is not sent, and the device leaves that part as it is.
     *
     * @param rgb
     *            red, green and blue, 0 to 255 each
     * @param brightness
     *            0 to 100
     */
    public Mono<Void> rgbSet(String address, int id, Boolean on, List<Integer> rgb, Integer brightness) {
        Map<String, Object> params = params(id);
        put(params, "on", on);
        put(params, "rgb", rgb);
        put(params, "brightness", brightness);
        return command(address, "RGB.Set", params);
    }

    public Mono<RgbwStatus> rgbStatus(String address, int id) {
        return read(address, "RGB.GetStatus", params(id), RgbwStatus.class, status -> status.getOutput() != null);
    }

    /**
     * The component {@code rgbw} - the same device in the profile rgbw, with a white channel.
     *
     * @param white
     *            0 to 255
     */
    public Mono<Void> rgbwSet(String address, int id, Boolean on, List<Integer> rgb, Integer white,
            Integer brightness) {
        Map<String, Object> params = params(id);
        put(params, "on", on);
        put(params, "rgb", rgb);
        put(params, "white", white);
        put(params, "brightness", brightness);
        return command(address, "RGBW.Set", params);
    }

    public Mono<RgbwStatus> rgbwStatus(String address, int id) {
        return read(address, "RGBW.GetStatus", params(id), RgbwStatus.class, status -> status.getOutput() != null);
    }

    /**
     * {@code Shelly.GetStatus}: every component of the device under its key ({@code switch:0},
     * {@code sys}, {@code wifi}), each as a raw map - which components there are differs by
     * the model of the device.
     */
    public Mono<Map<String, Object>> status(String address) {
        return components(address, "Shelly.GetStatus", status -> status.containsKey("sys"));
    }

    public Mono<BthomeSensorConfig> bthomeSensorConfig(String address, int id) {
        return read(address, "BTHomeSensor.GetConfig", params(id), BthomeSensorConfig.class,
            config -> config.getId() != null);
    }

    public Mono<BthomeSensorStatus> bthomeSensorStatus(String address, int id) {
        return read(address, "BTHomeSensor.GetStatus", params(id), BthomeSensorStatus.class,
            status -> status.getId() != null);
    }

    public Mono<BthomeDeviceConfig> bthomeDeviceConfig(String address, int id) {
        return read(address, "BTHomeDevice.GetConfig", params(id), BthomeDeviceConfig.class,
            config -> config.getId() != null);
    }

    /**
     * {@code Shelly.GetComponents} with {@code dynamic_only}: every dynamic component of the device
     * ({@code bthomedevice}, {@code bthomesensor}, the virtual ones) with its status and its
     * config - the components {@code Shelly.GetStatus} leaves out. The status and the config are
     * asked for by name ({@code include}): without it a firmware may answer the keys alone. The
     * device answers a page at a time; the pages are read one after the other, at most
     * {@value #MAX_COMPONENTS} components. The whole read fails - half a list would read as
     * components that are not there - on a page that is not the model, one that starts elsewhere
     * than asked (a firmware that ignored the offset would be asked for the same page for ever),
     * one that brings nothing before the total is reached, a total above the limit, and a change
     * of the configuration ({@code cfg_rev}) or of the total between two pages.
     */
    public Mono<List<ShellyComponent>> dynamicComponents(String address) {
        return componentsPage(address, 0)
            .flatMap(first -> checked(address, null, first, 0))
            .expand(page -> {
                int next = page.getOffset() + page.getComponents().size();
                if (next >= page.getTotal()) {
                    return Mono.empty();
                }
                return componentsPage(address, next).flatMap(following -> checked(address, page, following, next));
            })
            .concatMapIterable(ShellyComponentsPage::getComponents)
            .collectList();
    }

    // the most dynamic components a read takes: a gateway holds a few dozen, and a device that
    // claims more would be asked page after page
    static final int MAX_COMPONENTS = 256;

    // every rule of a page in one place: where it starts, what it brings, and that it belongs to
    // the same list as the page before it (null for the first)
    private static Mono<ShellyComponentsPage> checked(String address, ShellyComponentsPage previous,
            ShellyComponentsPage page, int offset) {
        if (!Integer.valueOf(offset).equals(page.getOffset())) {
            return Mono.error(pagingFailed(address, "asked for the page at " + offset + ", answered " + page.getOffset()));
        }
        if (page.getTotal() > MAX_COMPONENTS) {
            return Mono.error(pagingFailed(address, page.getTotal() + " components, more than " + MAX_COMPONENTS));
        }
        if (previous != null && (!previous.getCfgRev().equals(page.getCfgRev())
            || !previous.getTotal().equals(page.getTotal()))) {
            return Mono.error(pagingFailed(address, "the configuration changed between two pages"));
        }
        if (page.getComponents().isEmpty() && page.getTotal() > offset) {
            return Mono.error(pagingFailed(address, "an empty page at " + offset + " of " + page.getTotal()));
        }
        return Mono.just(page);
    }

    // cfg_rev is required: without it a change between two pages could not be seen. A list the
    // device did not send starts empty in the generated model; null is a list sent as null
    private Mono<ShellyComponentsPage> componentsPage(String address, int offset) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("dynamic_only", true);
        params.put("include", List.of("status", "config"));
        params.put("offset", offset);
        return read(address, "Shelly.GetComponents", params, ShellyComponentsPage.class,
            page -> page.getTotal() != null && page.getCfgRev() != null && page.getComponents() != null);
    }

    private static ShellyCallException pagingFailed(String address, String why) {
        return new ShellyCallException("Shelly.GetComponents of " + address + ": " + why);
    }

    /**
     * {@code Webhook.ListSupported}: the events the device can call a URL for, as a raw map -
     * the shape differs between firmware versions.
     */
    public Mono<Map<String, Object>> webhookListSupported(String address) {
        return components(address, "Webhook.ListSupported", supported -> !supported.isEmpty());
    }

    private Mono<Void> command(String address, String method, Map<String, Object> params) {
        return http.command(address, method, device -> request(device, method, params));
    }

    private <T> Mono<T> read(String address, String method, Map<String, Object> params, Class<T> model,
            Predicate<T> isAnswer) {
        return http.read(address, method, device -> request(device, method, params),
            ParameterizedTypeReference.forType(model), isAnswer);
    }

    private Mono<Map<String, Object>> components(String address, String method,
            Predicate<Map<String, Object>> isAnswer) {
        return http.read(address, method, device -> request(device, method, Map.of()), COMPONENTS, isAnswer);
    }

    // Always a body, "{}" when there is no parameter: a device refuses a POST without a
    // Content-Length. And a POST, not a GET with a query: a list (rgb) has no form in a query
    // that every firmware reads.
    private WebClient.RequestHeadersSpec<?> request(ShellyHttp.Target device, String method,
            Map<String, Object> params) {
        return http.client().post().uri(device.uri("/rpc/" + method, Map.of())).contentType(MediaType.APPLICATION_JSON)
            .bodyValue(params);
    }

    private static Map<String, Object> params(int id) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("id", id);
        return params;
    }

    private static void put(Map<String, Object> params, String name, Object value) {
        if (value != null) {
            params.put(name, value);
        }
    }
}
