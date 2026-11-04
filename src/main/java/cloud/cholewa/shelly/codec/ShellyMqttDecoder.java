package cloud.cholewa.shelly.codec;

import cloud.cholewa.shelly.model.BooleanStatus;
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
import cloud.cholewa.shelly.model.NumberStatus;
import cloud.cholewa.shelly.model.ObjectStatus;
import cloud.cholewa.shelly.model.PresenceStatus;
import cloud.cholewa.shelly.model.RgbwStatus;
import cloud.cholewa.shelly.model.SwitchStatus;
import cloud.cholewa.shelly.model.TemperatureStatus;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

/**
 * Turns one MQTT message of a Gen2+ device - the topic, or the routing key it becomes on the
 * broker, and the body - into a {@link ShellyEvent}.
 * <p>
 * Most of what a device publishes is not about the house ({@code sys}, {@code wifi},
 * {@code cloud}): for a component without a model here the answer is nothing, and the body
 * is not even read. The three-phase energy meter is ({@code em}, {@code emdata} - 2.3.0), and so are the
 * virtual components of a Shelly X device ({@code number}, {@code boolean}, {@code object} - 2.5.0). A body
 * that should be a model and is not is a {@link ShellyDecodeException}.
 */
public class ShellyMqttDecoder {

    // isOf says whether a body is the status of the component of its topic; missing names what
    // a body without it lacks
    private record Model<T>(Class<T> type, BiPredicate<T, Integer> isOf, String missing) {

        // The status of a component carries its id, and it is the id of the topic: JSON without
        // it is JSON of something else, which would otherwise decode into a model of nulls, and
        // JSON with another id is the status of another channel
        static <T> Model<T> withId(Class<T> type, Function<T, Integer> id) {
            return new Model<>(type, (status, index) -> index.equals(id.apply(status)), "the id of that component");
        }

        // The status of a virtual component has no id - the topic has the only one - so what
        // it cannot be without is the time of its value: every one the valve sent has it, and
        // "{}" or the status of another component does not
        static <T> Model<T> virtual(Class<T> type, Function<T, Long> lastUpdate) {
            return new Model<>(type, (status, index) -> lastUpdate.apply(status) != null, "the time of its value");
        }

        Object read(JsonMapper mapper, byte[] body, int index) {
            T status = mapper.readValue(body, type);
            return status != null && isOf.test(status, index) ? status : null;
        }
    }

    private static final Map<String, Model<?>> STATUSES = Map.ofEntries(
        Map.entry("switch", Model.withId(SwitchStatus.class, SwitchStatus::getId)),
        Map.entry("cover", Model.withId(CoverStatus.class, CoverStatus::getId)),
        Map.entry("light", Model.withId(Gen2LightStatus.class, Gen2LightStatus::getId)),
        Map.entry("rgb", Model.withId(RgbwStatus.class, RgbwStatus::getId)),
        Map.entry("rgbw", Model.withId(RgbwStatus.class, RgbwStatus::getId)),
        Map.entry("presencezone", Model.withId(PresenceStatus.class, PresenceStatus::getId)),
        Map.entry("illuminance", Model.withId(IlluminanceStatus.class, IlluminanceStatus::getId)),
        Map.entry("temperature", Model.withId(TemperatureStatus.class, TemperatureStatus::getId)),
        Map.entry("humidity", Model.withId(HumidityStatus.class, HumidityStatus::getId)),
        Map.entry("devicepower", Model.withId(DevicePowerStatus.class, DevicePowerStatus::getId)),
        Map.entry("bthomedevice", Model.withId(BthomeDeviceStatus.class, BthomeDeviceStatus::getId)),
        Map.entry("bthomesensor", Model.withId(BthomeSensorStatus.class, BthomeSensorStatus::getId)),
        Map.entry("em", Model.withId(EmStatus.class, EmStatus::getId)),
        Map.entry("emdata", Model.withId(EmDataStatus.class, EmDataStatus::getId)),
        Map.entry("number", Model.virtual(NumberStatus.class, NumberStatus::getLastUpdateTs)),
        Map.entry("boolean", Model.virtual(BooleanStatus.class, BooleanStatus::getLastUpdateTs)),
        Map.entry("object", Model.virtual(ObjectStatus.class, ObjectStatus::getLastUpdateTs)));

    private static final Map<String, Class<?>> NOTIFICATIONS = Map.of(
        "NotifyStatus", NotifyStatus.class,
        "NotifyFullStatus", NotifyFullStatus.class,
        "NotifyEvent", NotifyEvent.class);

    private final JsonMapper mapper;

    /**
     * @param mapper
     *            the mapper of the consumer. Its settings are kept but one: an unknown
     *            property is ignored here whatever the mapper says - a device sends more
     *            fields than a model has, and a firmware adds some.
     */
    public ShellyMqttDecoder(JsonMapper mapper) {
        this.mapper = mapper.rebuild().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).build();
    }

    /** The types of the components whose status is decoded; the status of any other is skipped. */
    public static Set<String> decodedComponents() {
        return STATUSES.keySet();
    }

    /**
     * @param topic
     *            the topic of the device, or its routing key
     * @return the event; nothing for a topic that is not one of a device, for a component
     *         without a model and for anything a device publishes besides
     * @throws ShellyDecodeException
     *             when the body is not what that topic carries
     */
    public Optional<ShellyEvent> decode(String topic, byte[] body) {
        return ShellyTopic.parse(topic).flatMap(parsed -> decode(parsed, body));
    }

    /** As {@link #decode(String, byte[])}, for a topic that was parsed already - to ask the registry first. */
    public Optional<ShellyEvent> decode(ShellyTopic topic, byte[] body) {
        return switch (topic.kind()) {
            case ONLINE -> Optional.of(new ShellyEvent.Online(topic.deviceId(), online(topic, body)));
            case STATUS -> status(topic, body);
            case RPC_EVENTS -> notification(topic, body);
            case OTHER -> Optional.empty();
        };
    }

    private static boolean online(ShellyTopic topic, byte[] body) {
        String text = body == null ? "" : new String(body, StandardCharsets.UTF_8).strip();
        if (text.equals("true")) {
            return true;
        }
        if (text.equals("false")) {
            return false;
        }
        throw new ShellyDecodeException("Neither true nor false on online of " + topic.deviceId());
    }

    private Optional<ShellyEvent> status(ShellyTopic topic, byte[] body) {
        Model<?> model = STATUSES.get(topic.component());
        // a component with an id: "temperature" without one is not the thermometer
        if (model == null || topic.index() == null) {
            return Optional.empty();
        }
        String what = "the status of " + topic.component() + ":" + topic.index() + " of " + topic.deviceId();
        Object status;
        try {
            status = model.read(mapper, body == null ? new byte[0] : body, topic.index());
        } catch (JacksonException notTheModel) {
            throw new ShellyDecodeException("Not " + what, notTheModel);
        }
        if (status == null) {
            throw new ShellyDecodeException("Not " + what + ": " + model.missing() + " is not in it");
        }
        return Optional.of(new ShellyEvent.Status(topic.deviceId(), topic.component(), topic.index(), status));
    }

    // which model a notification is says its method, so the frame is read twice: as a tree for
    // the method, then as the model. A method without a model is skipped, like a component
    private Optional<ShellyEvent> notification(ShellyTopic topic, byte[] body) {
        String what = "a notification of " + topic.deviceId();
        try {
            JsonNode frame = mapper.readTree(body == null ? new byte[0] : body);
            JsonNode method = frame == null ? null : frame.get("method");
            if (method == null || !method.isString()) {
                throw new ShellyDecodeException("Not " + what + ": no method in it");
            }
            Class<?> model = NOTIFICATIONS.get(method.stringValue());
            if (model == null) {
                return Optional.empty();
            }
            // params is what a notification is for: a frame without it would decode into a
            // model with nothing in it, or with a null where a reader expects the events
            JsonNode params = frame.get("params");
            if (params == null || !params.isObject()) {
                throw new ShellyDecodeException("Not " + what + ": no params in it");
            }
            return Optional
                .of(new ShellyEvent.Notification(topic.deviceId(), method.stringValue(), mapper.treeToValue(frame, model)));
        } catch (JacksonException notAFrame) {
            throw new ShellyDecodeException("Not " + what, notAFrame);
        }
    }
}
