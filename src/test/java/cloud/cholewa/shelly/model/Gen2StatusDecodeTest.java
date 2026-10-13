package cloud.cholewa.shelly.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * The status of one component of a Gen2+ device - the body of the MQTT topic
 * {@code <device id>/status/<component>:<id>} and the answer of {@code <Component>.GetStatus}.
 * The samples of the cover, the light, the rgb, the presence zone and the illuminance have the
 * shape read from the devices of the house (firmware 1.7.5 and 2.0.1); the Bluetooth
 * components follow the documentation of Shelly - no device of the house has one paired yet.
 */
class Gen2StatusDecodeTest {

    @Test
    void shouldDecodeACoverAtRest() {
        CoverStatus cover = Samples.decode("gen2/cover-status.json", CoverStatus.class);

        assertEquals(0, cover.getId());
        assertEquals("limit_switch", cover.getSource());
        assertEquals("open", cover.getState());
        assertEquals(0.0, cover.getApower());
        assertEquals(232.4, cover.getVoltage());
        assertEquals(0.0, cover.getCurrent());
        assertEquals(0.0, cover.getPf());
        assertEquals(50.0, cover.getFreq());
        assertEquals(668.733, cover.getAenergy().getTotal());
        assertEquals(List.of(0.0, 0.0, 0.0), cover.getAenergy().getByMinute());
        assertEquals(1791888840L, cover.getAenergy().getMinuteTs());
        assertEquals(40.4, cover.getTemperature().gettC());
        assertEquals(104.7, cover.getTemperature().gettF());
        assertTrue(cover.getPosControl());
        assertEquals("open", cover.getLastDirection());
        assertEquals(100, cover.getCurrentPos());
        // sent only on the way to a position
        assertNull(cover.getTargetPos());
        assertTrue(cover.getErrors().isEmpty());
    }

    @Test
    void shouldDecodeACoverOnItsWayToAPosition() {
        CoverStatus cover = Samples.decode("gen2/cover-status-moving.json", CoverStatus.class);

        assertEquals("closing", cover.getState());
        assertEquals("HTTP_in", cover.getSource());
        assertEquals("close", cover.getLastDirection());
        assertEquals(74, cover.getCurrentPos());
        assertEquals(30, cover.getTargetPos());
        assertEquals(118.6, cover.getApower());
    }

    // The switch of a device with a meter: the measurements have fractions, which the older
    // ShellyPro4StatusResponse of this library cuts off.
    @Test
    void shouldDecodeASwitchWithItsMeasurements() {
        SwitchStatus output = Samples.decode("gen2/switch-status.json", SwitchStatus.class);

        assertEquals(2, output.getId());
        assertEquals("init", output.getSource());
        assertTrue(output.getOutput());
        assertEquals(41.3, output.getApower());
        assertEquals(231.2, output.getVoltage());
        assertEquals(0.184, output.getCurrent());
        assertEquals(0.97, output.getPf());
        assertEquals(50.0, output.getFreq());
        assertEquals(5123.45, output.getAenergy().getTotal());
        assertEquals(39.1, output.getTemperature().gettC());
        assertTrue(output.getErrors().isEmpty());
    }

    @Test
    void shouldDecodeADimmerWithAChangeUnderWay() {
        Gen2LightStatus light = Samples.decode("gen2/light-status.json", Gen2LightStatus.class);

        assertEquals(0, light.getId());
        assertEquals("HTTP_in", light.getSource());
        assertTrue(light.getOutput());
        assertEquals(80, light.getBrightness());
        assertTrue(light.getTransition().getTarget().getOutput());
        assertEquals(20, light.getTransition().getTarget().getBrightness());
        assertEquals(1791888931.42, light.getTransition().getStartedAt());
        assertEquals(3.0, light.getTransition().getDuration());
        assertEquals(37.0, light.getTemperature().gettC());
        assertEquals(1688.578, light.getAenergy().getTotal());
        assertEquals(7.2, light.getApower());
        assertEquals(0.031, light.getCurrent());
        assertEquals(228.0, light.getVoltage());
    }

    @Test
    void shouldDecodeAnRgbLight() {
        RgbwStatus rgb = Samples.decode("gen2/rgb-status.json", RgbwStatus.class);

        assertEquals("SHC", rgb.getSource());
        assertTrue(rgb.getOutput());
        assertEquals(List.of(255, 120, 0), rgb.getRgb());
        assertEquals(100, rgb.getBrightness());
        // the profile rgb has no white channel
        assertNull(rgb.getWhite());
        assertNull(rgb.getTransition());
        assertEquals(11.4, rgb.getApower());
        assertEquals(12.1, rgb.getVoltage());
    }

    @Test
    void shouldDecodeAnRgbwLight() {
        RgbwStatus rgbw = Samples.decode("gen2/rgbw-status.json", RgbwStatus.class);

        assertEquals("init", rgbw.getSource());
        assertFalse(rgbw.getOutput());
        assertEquals(List.of(0, 0, 255), rgbw.getRgb());
        assertEquals(128, rgbw.getWhite());
        assertEquals(40, rgbw.getBrightness());
    }

    @Test
    void shouldDecodeAPresenceZone() {
        PresenceStatus zone = Samples.decode("gen2/presencezone-status.json", PresenceStatus.class);

        assertEquals(200, zone.getId());
        assertTrue(zone.getValue());
        assertEquals(2, zone.getNumObjects());
    }

    // A Presence Gen4 sends the word alone.
    @Test
    void shouldDecodeAnIlluminanceWithoutLux() {
        IlluminanceStatus illuminance = Samples.decode("gen2/illuminance-status.json", IlluminanceStatus.class);

        assertEquals(0, illuminance.getId());
        assertEquals("bright", illuminance.getIllumination());
        assertNull(illuminance.getLux());
    }

    @Test
    void shouldDecodeWhatABatteryThermometerPublishes() {
        TemperatureStatus temperature = Samples.decode("gen2/temperature-status.json", TemperatureStatus.class);
        assertEquals(21.4, temperature.gettC());
        assertEquals(70.5, temperature.gettF());

        HumidityStatus humidity = Samples.decode("gen2/humidity-status.json", HumidityStatus.class);
        assertEquals(48.2, humidity.getRh());

        DevicePowerStatus power = Samples.decode("gen2/devicepower-status.json", DevicePowerStatus.class);
        assertEquals(5.47, power.getBattery().getV());
        assertEquals(76, power.getBattery().getPercent());
        assertFalse(power.getExternal().getPresent());
    }

    @Test
    void shouldDecodeABluetoothDevice() {
        BthomeDeviceStatus device = Samples.decode("gen2/bthomedevice-status.json", BthomeDeviceStatus.class);

        assertEquals(200, device.getId());
        assertEquals(-68, device.getRssi());
        assertEquals(100, device.getBattery());
        assertEquals(41, device.getPacketId());
        assertEquals(1791888700L, device.getLastUpdatedTs());
    }

    // The value of a Bluetooth sensor has the type of what it measures: the model keeps it as
    // Jackson reads it - a Boolean for a window, and for a number whatever the JSON spelled:
    // a Double with a fraction, an Integer without one. A reader takes it as a Number.
    @Test
    void shouldKeepTheValueOfABluetoothSensorInItsOwnType() {
        BthomeSensorStatus temperature =
            Samples.decode("gen2/bthomesensor-status-number.json", BthomeSensorStatus.class);
        assertEquals(201, temperature.getId());
        assertEquals(21.6, temperature.getValue());
        assertEquals(1791888700L, temperature.getLastUpdatedTs());

        BthomeSensorStatus window = Samples.decode("gen2/bthomesensor-status-boolean.json", BthomeSensorStatus.class);
        assertEquals(Boolean.TRUE, window.getValue());

        BthomeSensorStatus whole = Samples.decodeText("{\"id\":201,\"value\":21}", BthomeSensorStatus.class);
        assertEquals(21, whole.getValue());
        assertEquals(21.0, assertInstanceOf(Number.class, whole.getValue()).doubleValue());
    }

    @Test
    void shouldDecodeTheConfigurationOfABluetoothSensor() {
        BthomeSensorConfig config = Samples.decode("gen2/bthomesensor-config.json", BthomeSensorConfig.class);

        assertEquals(201, config.getId());
        assertEquals("00:00:5e:00:53:01", config.getAddr());
        assertEquals(69, config.getObjId());
        assertEquals(0, config.getIdx());
        assertEquals("Outside temperature", config.getName());
    }

    // "Not sent" has to stay apart from "off" and from zero: nothing has a default.
    @Test
    void shouldLeaveWhatADeviceDoesNotSendUnset() {
        CoverStatus cover = Samples.decodeText("{}", CoverStatus.class);
        assertNull(cover.getState());
        assertNull(cover.getCurrentPos());
        assertNull(cover.getPosControl());

        Gen2LightStatus light = Samples.decodeText("{}", Gen2LightStatus.class);
        assertNull(light.getOutput());
        assertNull(light.getBrightness());

        RgbwStatus rgbw = Samples.decodeText("{}", RgbwStatus.class);
        assertNull(rgbw.getOutput());
        assertNull(rgbw.getWhite());

        PresenceStatus zone = Samples.decodeText("{}", PresenceStatus.class);
        assertNull(zone.getValue());
        assertNull(zone.getNumObjects());

        SwitchStatus output = Samples.decodeText("{}", SwitchStatus.class);
        assertNull(output.getOutput());
        assertNull(output.getApower());
    }

    // The one exception: a list. The generator starts it empty, so a list a device did not
    // send cannot be told from one it sent empty - ask isEmpty(), never get(0).
    @Test
    void shouldReadAListADeviceDoesNotSendAsEmpty() {
        assertTrue(Samples.decodeText("{}", RgbwStatus.class).getRgb().isEmpty());
        assertTrue(Samples.decodeText("{}", CoverStatus.class).getErrors().isEmpty());
        assertTrue(Samples.decodeText("{}", EnergyCounter.class).getByMinute().isEmpty());
    }

    // ...and only for a model that was decoded or made with new: the builder leaves a list it
    // was not given null. A stub built for a test has to give every list its code reads.
    @Test
    void shouldLeaveAListTheBuilderWasNotGivenNull() {
        assertNull(RgbwStatus.builder().build().getRgb());
        assertNull(CoverStatus.builder().build().getErrors());
        assertTrue(new RgbwStatus().getRgb().isEmpty());
    }
}
