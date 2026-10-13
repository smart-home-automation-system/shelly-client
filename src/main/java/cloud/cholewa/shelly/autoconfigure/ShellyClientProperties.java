package cloud.cholewa.shelly.autoconfigure;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.boot.convert.DurationUnit;

/**
 * The settings of the clients this library registers.
 *
 * @param enabled
 *            false keeps the library from registering anything - for a service that
 *            wants the models alone
 * @param connectTimeout
 *            how long a device may take to accept the connection
 * @param responseTimeout
 *            the longest silence while its answer is awaited and read
 *            <p>
 *            A bare number is seconds - without a unit it would bind as milliseconds. Both
 *            timeouts lie between one second and a minute; the application does not start
 *            with one outside.
 */
@ConfigurationProperties(prefix = "shelly.client")
public record ShellyClientProperties(
        @DefaultValue("true") boolean enabled,
        @DurationUnit(ChronoUnit.SECONDS) @DefaultValue("3s") Duration connectTimeout,
        @DurationUnit(ChronoUnit.SECONDS) @DefaultValue("5s") Duration responseTimeout) {
}
