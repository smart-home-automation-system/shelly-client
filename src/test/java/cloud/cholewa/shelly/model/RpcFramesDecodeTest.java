package cloud.cholewa.shelly.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * The frames of the RPC channel of a Gen2+ device: the notifications it publishes on
 * {@code <device id>/events/rpc} - when the RPC over MQTT is enabled on it - and the error of
 * a refused call.
 */
class RpcFramesDecodeTest {

    // params is a map: ts, and under the key of a component only what changed there.
    @Test
    void shouldDecodeTheNotificationOfAChangeAsARawDelta() {
        NotifyStatus notification = Samples.decode("rpc/notify-status.json", NotifyStatus.class);

        assertEquals("shellyplus2pm-a1b2c3d4e5f6", notification.getSrc());
        assertEquals("shellyplus2pm-a1b2c3d4e5f6/events", notification.getDst());
        assertEquals("NotifyStatus", notification.getMethod());
        assertEquals(Set.of("ts", "cover:0"), notification.getParams().keySet());
        assertEquals(1791888931.42, notification.getParams().get("ts"));

        Map<?, ?> cover = assertInstanceOf(Map.class, notification.getParams().get("cover:0"));
        assertEquals("closing", cover.get("state"));
        assertEquals(30, cover.get("target_pos"));
        // a delta: what did not change is not there
        assertFalse(cover.containsKey("current_pos"));
    }

    @Test
    void shouldDecodeTheNotificationOfTheWholeStatus() {
        NotifyFullStatus notification = Samples.decode("rpc/notify-full-status.json", NotifyFullStatus.class);

        assertEquals("NotifyFullStatus", notification.getMethod());
        assertEquals(Set.of("ts", "switch:0", "sys", "wifi"), notification.getParams().keySet());

        Map<?, ?> output = assertInstanceOf(Map.class, notification.getParams().get("switch:0"));
        assertEquals(Boolean.FALSE, output.get("output"));
        assertEquals("init", output.get("source"));
    }

    @Test
    void shouldDecodeTheNotificationOfEvents() {
        NotifyEvent notification = Samples.decode("rpc/notify-event.json", NotifyEvent.class);

        assertEquals("shellypro1pm-a1b2c3d4e5f6", notification.getSrc());
        assertEquals("NotifyEvent", notification.getMethod());
        assertEquals(1791888931.42, notification.getParams().getTs());
        assertEquals(2, notification.getParams().getEvents().size());

        RpcEvent press = notification.getParams().getEvents().get(1);
        assertEquals("input:0", press.getComponent());
        assertEquals(0, press.getId());
        assertEquals("single_push", press.getEvent());
        assertEquals(1791888931.9, press.getTs());
    }

    @Test
    void shouldDecodeTheErrorOfARefusedCall() {
        RpcError error = Samples.decode("rpc/error.json", RpcError.class);

        assertEquals(404, error.getCode());
        assertEquals("No handler for Cover.NoSuchMethod", error.getMessage());
    }

    // A notification without params reads as an empty map, never as null.
    @Test
    void shouldReadMissingParamsAsEmpty() {
        assertTrue(Samples.decodeText("{\"method\":\"NotifyStatus\"}", NotifyStatus.class).getParams().isEmpty());
        assertTrue(
            Samples.decodeText("{\"method\":\"NotifyFullStatus\"}", NotifyFullStatus.class).getParams().isEmpty());
    }

    // The JSON names are those of the device, underscores included - a model written back is
    // what a stub of a device answers in the tests of a consumer.
    @Test
    void shouldWriteAModelUnderTheNamesOfTheDevice() {
        String json = Samples.encode(CoverStatus.builder().state("open").posControl(true).currentPos(100).build());

        assertEquals("{\"state\":\"open\",\"pos_control\":true,\"current_pos\":100}", json);
    }
}
