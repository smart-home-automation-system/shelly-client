package cloud.cholewa.shelly.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
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

    // HAS-367: the temperature add-on of a Shelly 1 (SHSW-1, firmware 1.14.0, one DS18B20 - the sauna of the house,
    // the address of the sensor invented): the sensors by their index, both units, the unit the device shows
    @Test
    void shouldDecodeTheTemperatureAddOnOfASwitch() {
        Gen1DeviceStatus status = Samples.decode("gen1/addon-temperature-status.json", Gen1DeviceStatus.class);

        assertEquals("C", status.getExtSensors().getTemperatureUnit());
        assertEquals(1, status.getExtTemperature().size());
        ExternalTemperatureValue sensor = status.getExtTemperature().get("0");
        assertEquals("28aabbccddeeff00", sensor.getHwID());
        assertEquals(7.12, sensor.gettC());
        assertEquals(44.83, sensor.gettF());
        assertTrue(status.getExtHumidity().isEmpty());
        assertFalse(status.getRelays().get(0).getIson());
    }

    // a DHT22 on the add-on reports the humidity too, by the same index
    @Test
    void shouldDecodeTheHumidityOfTheAddOn() {
        Gen1DeviceStatus status = Samples.decodeText("""
            {"ext_temperature":{"0":{"hwID":"aabbcc0000000000","tC":21.4,"tF":70.52}},
             "ext_humidity":{"0":{"hwID":"aabbcc0000000000","hum":48.6}}}""", Gen1DeviceStatus.class);

        assertEquals(48.6, status.getExtHumidity().get("0").getHum());
        assertEquals("aabbcc0000000000", status.getExtHumidity().get("0").getHwID());
        assertEquals(21.4, status.getExtTemperature().get("0").gettC());
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

    // GET /meter/0 of a Plug S: the energy in watt-minutes, the overpower a draw and not a flag
    @Test
    void shouldDecodeTheMeterOfAPlug() {
        Gen1Meter meter = Samples.decode("gen1/plug-meter.json", Gen1Meter.class);

        assertEquals(482.27, meter.getPower());
        assertEquals(0.0, meter.getOverpower());
        assertTrue(meter.getIsValid());
        assertEquals(1790000000L, meter.getTimestamp());
        assertEquals(List.of(480.512, 479.903, 391.041), meter.getCounters());
        assertEquals(123456L, meter.getTotal());
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

        // a device without the add-on: no sensors, no unit
        Gen1DeviceStatus withoutAddOn = Samples.decodeText("{}", Gen1DeviceStatus.class);
        assertNull(withoutAddOn.getExtSensors());
        assertTrue(withoutAddOn.getExtTemperature().isEmpty());
        assertTrue(withoutAddOn.getExtHumidity().isEmpty());

        ExternalTemperatureValue sensor = Samples.decodeText("{}", ExternalTemperatureValue.class);
        assertNull(sensor.gettC());
        assertNull(sensor.gettF());

        Gen1Meter meter = Samples.decodeText("{}", Gen1Meter.class);
        assertNull(meter.getPower());
        assertNull(meter.getIsValid());
        assertNull(meter.getTotal());
        assertTrue(meter.getCounters().isEmpty());
    }
}
