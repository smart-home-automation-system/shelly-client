package cloud.cholewa.shelly.http;

/**
 * A call to a device that did not end with its answer: it could not be reached, did not answer
 * in time, refused the call, or answered something that is not what a Shelly answers.
 * <p>
 * The message names the call and the address of the device, and is written for a log. It is
 * not text for a response: a service maps this exception to an error of its own.
 */
public class ShellyCallException extends RuntimeException {

    private final Integer httpStatus;

    public ShellyCallException(String message) {
        this(message, null, null);
    }

    public ShellyCallException(String message, Throwable cause) {
        this(message, null, cause);
    }

    public ShellyCallException(String message, Integer httpStatus, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    /**
     * The HTTP status the device answered with, when it answered and refused; {@code null} when
     * the call failed before an answer - the device was not reached or did not answer in time -
     * and when a 2xx carried something that is not the answer.
     */
    public Integer httpStatus() {
        return httpStatus;
    }
}
