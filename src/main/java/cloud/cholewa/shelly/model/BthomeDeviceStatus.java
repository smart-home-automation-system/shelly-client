package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({BthomeDeviceStatus.JSON_PROPERTY_ID, BthomeDeviceStatus.JSON_PROPERTY_RSSI,
        BthomeDeviceStatus.JSON_PROPERTY_BATTERY, BthomeDeviceStatus.JSON_PROPERTY_PACKET_ID,
        BthomeDeviceStatus.JSON_PROPERTY_LAST_UPDATED_TS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class BthomeDeviceStatus {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_RSSI = "rssi";
    private Integer rssi;
    public static final String JSON_PROPERTY_BATTERY = "battery";
    private Integer battery;
    public static final String JSON_PROPERTY_PACKET_ID = "packet_id";
    private Integer packetId;
    public static final String JSON_PROPERTY_LAST_UPDATED_TS = "last_updated_ts";
    private Long lastUpdatedTs;

    public BthomeDeviceStatus id(Integer id) {
        this.id = id;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getId() {
        return id;
    }

    @JsonProperty(JSON_PROPERTY_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setId(Integer id) {
        this.id = id;
    }

    public BthomeDeviceStatus rssi(Integer rssi) {
        this.rssi = rssi;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_RSSI)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getRssi() {
        return rssi;
    }

    @JsonProperty(JSON_PROPERTY_RSSI)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }

    public BthomeDeviceStatus battery(Integer battery) {
        this.battery = battery;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_BATTERY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getBattery() {
        return battery;
    }

    @JsonProperty(JSON_PROPERTY_BATTERY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public BthomeDeviceStatus packetId(Integer packetId) {
        this.packetId = packetId;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_PACKET_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getPacketId() {
        return packetId;
    }

    @JsonProperty(JSON_PROPERTY_PACKET_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPacketId(Integer packetId) {
        this.packetId = packetId;
    }

    public BthomeDeviceStatus lastUpdatedTs(Long lastUpdatedTs) {
        this.lastUpdatedTs = lastUpdatedTs;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_LAST_UPDATED_TS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Long getLastUpdatedTs() {
        return lastUpdatedTs;
    }

    @JsonProperty(JSON_PROPERTY_LAST_UPDATED_TS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setLastUpdatedTs(Long lastUpdatedTs) {
        this.lastUpdatedTs = lastUpdatedTs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BthomeDeviceStatus bthomeDeviceStatus = (BthomeDeviceStatus) o;
        return Objects.equals(this.id, bthomeDeviceStatus.id) && Objects.equals(this.rssi, bthomeDeviceStatus.rssi)
                && Objects.equals(this.battery, bthomeDeviceStatus.battery)
                && Objects.equals(this.packetId, bthomeDeviceStatus.packetId)
                && Objects.equals(this.lastUpdatedTs, bthomeDeviceStatus.lastUpdatedTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rssi, battery, packetId, lastUpdatedTs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BthomeDeviceStatus {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    rssi: ").append(toIndentedString(rssi)).append("\n");
        sb.append("    battery: ").append(toIndentedString(battery)).append("\n");
        sb.append("    packetId: ").append(toIndentedString(packetId)).append("\n");
        sb.append("    lastUpdatedTs: ").append(toIndentedString(lastUpdatedTs)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
