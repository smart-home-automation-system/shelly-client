package cloud.cholewa.shelly.codec;

import java.util.Arrays;
import java.util.Optional;

/**
 * A topic of a Gen1 device, taken apart: {@code shellies/<device id>/roller/0/pos}. A parser
 * of the topic alone - the body of a Gen1 topic is a word ({@code open}), a number or, under
 * {@code status}, the JSON of the models {@code RollerStatus}, {@code Gen1LightStatus} and
 * {@code Relay}, and reading it is left to the caller.
 * <p>
 * No Gen1 device of the house is on MQTT; this is here so that the day one is, its topics are
 * read the same way everywhere.
 *
 * @param deviceId
 *            the id of the device - the part after {@code shellies}
 * @param component
 *            {@code roller}, {@code light}, {@code relay}, {@code online}, and whatever else
 *            the device has
 * @param index
 *            the number of the roller, light or relay; {@code null} for a topic without one
 *            ({@code online})
 * @param attribute
 *            what of the component the topic carries: {@code null} for its state
 *            ({@code roller/0}: open, close or stop), {@code pos}, {@code status},
 *            {@code power}, {@code command} - the rest of the topic as it came
 */
public record ShellyGen1Topic(String deviceId, String component, Integer index, String attribute) {

    private static final String ROOT = "shellies";

    /**
     * @param topic
     *            the topic, with {@code /} or - as a routing key - with {@code .}
     * @return the topic, or nothing for one that is not {@code shellies/<id>/<component>...}:
     *         another root, {@code shellies/announce}
     */
    public static Optional<ShellyGen1Topic> parse(String topic) {
        if (topic == null) {
            return Optional.empty();
        }
        String[] parts = topic.replace('/', '.').split("\\.", -1);
        if (parts.length < 3 || !parts[0].equals(ROOT) || Arrays.stream(parts).anyMatch(String::isEmpty)) {
            return Optional.empty();
        }
        boolean indexed = parts.length > 3 && parts[3].length() <= 9 && parts[3].chars().allMatch(Character::isDigit);
        int rest = indexed ? 4 : 3;
        String attribute = parts.length > rest ? String.join("/", Arrays.copyOfRange(parts, rest, parts.length)) : null;

        return Optional
            .of(new ShellyGen1Topic(parts[1], parts[2], indexed ? Integer.valueOf(parts[3]) : null, attribute));
    }
}
