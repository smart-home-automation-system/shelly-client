package cloud.cholewa.shelly.http;

/**
 * A call that was never made, because what it was given is not the address of a device - a
 * host, or {@code host:port}. A mistake in the registry, not a device that is down: a monitor
 * that counts failed calls should not count this one.
 * <p>
 * The message does not repeat what was given: it is not an address, so it may be anything.
 */
public class ShellyAddressException extends ShellyCallException {

    public ShellyAddressException(String message) {
        super(message);
    }
}
