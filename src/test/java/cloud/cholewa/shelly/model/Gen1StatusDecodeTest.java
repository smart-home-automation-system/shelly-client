package cloud.cholewa.shelly.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * What a Gen1 device answers to {@code GET /status}, by the kind of the device. The samples
 * have the shape read from the devices of the house (firmware 1.14.0, the Motion 2.2.4), with
 * invented names and addresses.
 */
class Gen1StatusDecodeTest {

    @Test
    void shouldDecodeARollerOfAShelly25() {
        Gen1DeviceStatus status = Samples.decode("gen1/roller-status.json", Gen1DeviceStatus.class);

        assertEquals(1, status.getRollers().size());
        RollerStatus roller = status.getRollers().get(0);
        assertEquals("stop", roller.getState());
        assertEquals("schedule", roller.getSource());
        assertEquals(0.0, roller.getPower());
        assertTrue(roller.getIsValid());
        assertFalse(roller.getSafetySwitch());
        assertFalse(roller.getOvertemperature());
        assertEquals("normal", roller.getStopReason());
        assertEquals("open", roller.getLastDirection());
        assertEquals(100, roller.getCurrentPos());
        assertFalse(roller.getCalibrating());
        assertTrue(roller.getPositioning());

        assertEquals(1791888887L, status.getUnixtime());
        assertTrue(status.getWifiSta().getConnected());
        assertTrue(status.getCloud().getConnected());
        // a roller has neither lights nor relays, and no mode in this firmware
        assertTrue(status.getLights().isEmpty());
        assertTrue(status.getRelays().isEmpty());
        assertNull(status.getMode());
    }

    // In the mode color the level of the light is gain; brightness is not sent at all.
    @Test
    void shouldDecodeAnRgbw2InTheModeColor() {
        Gen1DeviceStatus status = Samples.decode("gen1/rgbw2-color-status.json", Gen1DeviceStatus.class);

        assertEquals("color", status.getMode());
        Gen1LightStatus light = status.getLights().get(0);
        assertTrue(light.getIson());
        assertEquals("http", light.getSource());
        assertEquals("color", light.getMode());
        assertEquals(255, light.getRed());
        assertEquals(120, light.getGreen());
        assertEquals(0, light.getBlue());
        assertEquals(0, light.getWhite());
        assertEquals(65, light.getGain());
        assertNull(light.getBrightness());
        assertNull(light.getTemp());
        assertEquals(0, light.getEffect());
        assertEquals(500, light.getTransition());
        assertEquals(7.25, light.getPower());
        assertFalse(light.getOverpower());
        assertFalse(light.getHasTimer());
    }

    // A Duo has no colors and no mode: brightness, the share of cold white and the temperature.
    @Test
    void shouldDecodeADuo() {
        Gen1DeviceStatus status = Samples.decode("gen1/duo-status.json", Gen1DeviceStatus.class);

        assertNull(status.getMode());
        Gen1LightStatus light = status.getLights().get(0);
        assertFalse(light.getIson());
        assertEquals("cloud", light.getSource());
        assertEquals(100, light.getBrightness());
        assertEquals(50, light.getWhite());
        assertEquals(4750, light.getTemp());
        assertNull(light.getMode());
        assertNull(light.getRed());
        assertNull(light.getGain());
    }

    // The relays of the envelope are the Relay the library had before.
    @Test
    void shouldDecodeTheRelaysOfASwitch() {
        Gen1DeviceStatus status = Samples.decode("gen1/relay-status.json", Gen1DeviceStatus.class);

        assertEquals(1, status.getRelays().size());
        assertTrue(status.getRelays().get(0).getIson());
        assertEquals("input", status.getRelays().get(0).getSource());
        assertFalse(status.getCloud().getConnected());
        assertTrue(status.getRollers().isEmpty());
        assertTrue(status.getLights().isEmpty());
    }

    @Test
    void shouldDecodeAMotionSensor() {
        MotionStatus status = Samples.decode("gen1/motion-status.json", MotionStatus.class);

        assertTrue(status.getSensor().getMotion());
        assertFalse(status.getSensor().getVibration());
        assertEquals(1791867200L, status.getSensor().getTimestamp());
        assertTrue(status.getSensor().getActive());
        assertTrue(status.getSensor().getIsValid());
        assertEquals(23, status.getLux().getValue());
        assertEquals("twilight", status.getLux().getIllumination());
        assertTrue(status.getLux().getIsValid());
        assertEquals(94, status.getBat().getValue());
        assertEquals(3.817, status.getBat().getVoltage());
    }

    // "Not sent" has to stay apart from "off" and from zero: nothing has a default.
    @Test
    void shouldLeaveWhatADeviceDoesNotSendUnset() {
        RollerStatus roller = Samples.decodeText("{}", RollerStatus.class);
        assertNull(roller.getState());
        assertNull(roller.getCurrentPos());
        assertNull(roller.getPositioning());

        Gen1LightStatus light = Samples.decodeText("{}", Gen1LightStatus.class);
        assertNull(light.getIson());
        assertNull(light.getBrightness());
        assertNull(light.getGain());

        MotionStatus motion = Samples.decodeText("{}", MotionStatus.class);
        assertNull(motion.getSensor());
        assertNull(motion.getLux());
        assertNull(motion.getBat());
    }
}
