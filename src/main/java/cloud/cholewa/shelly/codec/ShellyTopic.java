package cloud.cholewa.shelly.codec;

import java.util.Optional;

/**
 * What a Gen2+ device published about, read from the topic alone - before anybody looks at the
 * body. A service that gets every message of every device reads the device id here, asks its
 * registry whether the device is one of its own, and only then has the body decoded.
 * <p>
 * The topic of the device is {@code <device id>/status/switch:0}; on the broker of the house
 * it arrives as the routing key {@code <device id>.status.switch:0}. Both forms are read.
 *
 * @param deviceId
 *            the id the device reports under - the first part of the topic, in the case of the
 *            device
 * @param kind
 *            which of its topics this is
 * @param component
 *            the type of the component for a status ({@code switch}, {@code cover},
 *            {@code sys}); for {@link Kind#OTHER} the rest of the topic as it came;
 *            {@code null} otherwise
 * @param index
 *            the id of the component ({@code 0} of {@code switch:0}); {@code null} for a
 *            component there is one of ({@code sys}) and for every other kind
 */
public record ShellyTopic(String deviceId, Kind kind, String component, Integer index) {

    public enum Kind {
        /** {@code <id>/online}: {@code true} on every connect, {@code false} as the last will. */
        ONLINE,
        /** {@code <id>/status/<component>}: the whole status of one component. */
        STATUS,
        /** {@code <id>/events/rpc}: the notifications of a device whose RPC over MQTT is on. */
        RPC_EVENTS,
        /** Anything else the device publishes. */
        OTHER
    }

    private static final String STATUS = "status.";

    /**
     * @return the topic, or nothing for a text that has no device id in front of a topic - an
     *         empty key, one without a separator
     */
    public static Optional<ShellyTopic> parse(String topic) {
        if (topic == null) {
            return Optional.empty();
        }
        String key = topic.replace('/', '.');
        int end = key.indexOf('.');
        if (end <= 0 || end == key.length() - 1) {
            return Optional.empty();
        }
        String deviceId = key.substring(0, end);
        String rest = key.substring(end + 1);

        if (rest.equals("online")) {
            return Optional.of(new ShellyTopic(deviceId, Kind.ONLINE, null, null));
        }
        if (rest.equals("events.rpc")) {
            return Optional.of(new ShellyTopic(deviceId, Kind.RPC_EVENTS, null, null));
        }
        if (rest.startsWith(STATUS) && rest.length() > STATUS.length()) {
            return Optional.of(status(deviceId, rest.substring(STATUS.length())));
        }
        return Optional.of(new ShellyTopic(deviceId, Kind.OTHER, rest, null));
    }

    // "switch:0" is the component switch with the id 0, "sys" a component there is one of.
    // What follows the colon and is not a number stays a part of the name: nothing is guessed
    private static ShellyTopic status(String deviceId, String component) {
        int colon = component.lastIndexOf(':');
        if (colon > 0 && colon < component.length() - 1) {
            String id = component.substring(colon + 1);
            if (id.length() <= 9 && id.chars().allMatch(Character::isDigit)) {
                return new ShellyTopic(deviceId, Kind.STATUS, component.substring(0, colon), Integer.valueOf(id));
            }
        }
        return new ShellyTopic(deviceId, Kind.STATUS, component, null);
    }
}
