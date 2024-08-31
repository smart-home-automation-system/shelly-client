
package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ WifiStatus.JSON_PROPERTY_CONNECTED, WifiStatus.JSON_PROPERTY_SSID, WifiStatus.JSON_PROPERTY_IP,
        WifiStatus.JSON_PROPERTY_RSSI })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class WifiStatus {
    public static final String JSON_PROPERTY_CONNECTED = "connected";
    private Boolean connected;
    public static final String JSON_PROPERTY_SSID = "ssid";
    private String ssid;
    public static final String JSON_PROPERTY_IP = "ip";
    private String ip;
    public static final String JSON_PROPERTY_RSSI = "rssi";
    private Integer rssi;

    public WifiStatus connected(Boolean connected) {

        this.connected = connected;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_CONNECTED)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getConnected() {
        return connected;
    }

    @JsonProperty(JSON_PROPERTY_CONNECTED)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public WifiStatus ssid(String ssid) {

        this.ssid = ssid;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_SSID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getSsid() {
        return ssid;
    }

    @JsonProperty(JSON_PROPERTY_SSID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public WifiStatus ip(String ip) {

        this.ip = ip;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_IP)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getIp() {
        return ip;
    }

    @JsonProperty(JSON_PROPERTY_IP)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setIp(String ip) {
        this.ip = ip;
    }

    public WifiStatus rssi(Integer rssi) {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WifiStatus wifiStatus = (WifiStatus) o;
        return Objects.equals(this.connected, wifiStatus.connected) && Objects.equals(this.ssid, wifiStatus.ssid)
                && Objects.equals(this.ip, wifiStatus.ip) && Objects.equals(this.rssi, wifiStatus.rssi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connected, ssid, ip, rssi);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WifiStatus {\n");
        sb.append("    connected: ").append(toIndentedString(connected)).append("\n");
        sb.append("    ssid: ").append(toIndentedString(ssid)).append("\n");
        sb.append("    ip: ").append(toIndentedString(ip)).append("\n");
        sb.append("    rssi: ").append(toIndentedString(rssi)).append("\n");
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
