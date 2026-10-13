package cloud.cholewa.shelly.codec;

import java.util.Optional;

/**
 * What a Gen2+ device said in one MQTT message, decoded.
 */
public sealed interface ShellyEvent {

    /** The id the device reports under - the first part of its topics. */
    String deviceId();

    /**
     * {@code <id>/online}. {@code false} is the last will of the device: the broker publishes
     * it when a connection ends without a goodbye - for a battery sensor that is "asleep", not
     * "broken".
     */
    record Online(String deviceId, boolean online) implements ShellyEvent {
    }

    /**
     * {@code <id>/status/<component>:<index>}: the whole status of one component, not a delta
     * - a device repeats it about once a minute, changed or not.
     *
     * @param component
     *            the type of the component: {@code switch}, {@code cover}, {@code light}
     * @param index
     *            the id of the component - the channel of a device with several
     * @param status
     *            the model of the component: {@code SwitchStatus}, {@code CoverStatus},
     *            {@code Gen2LightStatus}, {@code RgbwStatus} ({@code rgb} and {@code rgbw}),
     *            {@code PresenceStatus} ({@code presencezone}), {@code IlluminanceStatus},
     *            {@code TemperatureStatus}, {@code HumidityStatus}, {@code DevicePowerStatus},
     *            {@code BthomeDeviceStatus}, {@code BthomeSensorStatus}
     */
    record Status(String deviceId, String component, Integer index, Object status) implements ShellyEvent {

        /** The status as the model asked for, or nothing when it is the status of another component. */
        public <T> Optional<T> as(Class<T> model) {
            return model.isInstance(status) ? Optional.of(model.cast(status)) : Optional.empty();
        }
    }

    /**
     * {@code <id>/events/rpc}: a notification of the RPC channel. Only a device whose RPC over
     * MQTT is enabled publishes it.
     *
     * @param method
     *            {@code NotifyStatus}, {@code NotifyFullStatus} or {@code NotifyEvent}
     * @param frame
     *            the model of that method, of the same name
     */
    record Notification(String deviceId, String method, Object frame) implements ShellyEvent {

        /** The frame as the model asked for, or nothing when it is the frame of another method. */
        public <T> Optional<T> as(Class<T> model) {
            return model.isInstance(frame) ? Optional.of(model.cast(frame)) : Optional.empty();
        }
    }
}
