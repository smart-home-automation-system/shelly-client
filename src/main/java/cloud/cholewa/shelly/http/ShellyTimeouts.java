package cloud.cholewa.shelly.http;

import java.time.Duration;
import java.util.Objects;

/**
 * How long a call to a device may take.
 *
 * @param connect
 *            how long the device may take to accept the connection
 * @param response
 *            the longest silence while its answer is awaited and read
 *            <p>
 *            Both lie between one second and a minute. Zero would switch a timeout off - the
 *            unbounded wait these exist to end - and so would a value meant as milliseconds:
 *            "5000" bound as seconds is 83 minutes.
 */
public record ShellyTimeouts(Duration connect, Duration response) {

    private static final Duration SHORTEST = Duration.ofSeconds(1);
    private static final Duration LONGEST = Duration.ofMinutes(1);

    public ShellyTimeouts {
        check("connect", connect);
        check("response", response);
    }

    /**
     * The longest a whole call may last: a device that keeps sending a byte now and then never
     * runs into the response timeout, which only bounds the silence.
     */
    public Duration call() {
        return connect.plus(response);
    }

    private static void check(String name, Duration timeout) {
        Objects.requireNonNull(timeout, () -> "The " + name + " timeout of a Shelly call is missing");
        if (timeout.compareTo(SHORTEST) < 0 || timeout.compareTo(LONGEST) > 0) {
            throw new IllegalArgumentException("The " + name + " timeout of a Shelly call has to be between "
                + SHORTEST.toSeconds() + " and " + LONGEST.toSeconds() + " seconds, and is: " + timeout);
        }
    }
}
