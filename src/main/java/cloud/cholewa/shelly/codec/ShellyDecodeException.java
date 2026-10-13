package cloud.cholewa.shelly.codec;

/**
 * A message of a component the decoder has a model for, whose body is not that model: not
 * JSON, JSON of another shape, or neither {@code true} nor {@code false} on {@code online}.
 * <p>
 * The message names the topic and not the body. Thrown, not returned: a listener that lets it
 * out of a reactive chain has the message handed back and delivered again at once - catch it,
 * log it and go on to the next message.
 */
public class ShellyDecodeException extends RuntimeException {

    public ShellyDecodeException(String message) {
        super(message);
    }

    public ShellyDecodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
