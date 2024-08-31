
package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ ShellyUniStatusResponse.JSON_PROPERTY_TIME, ShellyUniStatusResponse.JSON_PROPERTY_UNIXTIME,
        ShellyUniStatusResponse.JSON_PROPERTY_WIFI_STA, ShellyUniStatusResponse.JSON_PROPERTY_CLOUD,
        ShellyUniStatusResponse.JSON_PROPERTY_RELAYS, ShellyUniStatusResponse.JSON_PROPERTY_EXT_TEMPERATURE })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class ShellyUniStatusResponse {
    public static final String JSON_PROPERTY_TIME = "time";
    private String time;
    public static final String JSON_PROPERTY_UNIXTIME = "unixtime";
    private Integer unixtime;
    public static final String JSON_PROPERTY_WIFI_STA = "wifi_sta";
    private WifiStatus wifiSta;
    public static final String JSON_PROPERTY_CLOUD = "cloud";
    private Cloud cloud;
    public static final String JSON_PROPERTY_RELAYS = "relays";
    private List<@Valid Relay> relays = new ArrayList<>();
    public static final String JSON_PROPERTY_EXT_TEMPERATURE = "ext_temperature";
    private Map<String, ExternalTemperatureValue> extTemperature = new HashMap<>();

    public ShellyUniStatusResponse time(String time) {

        this.time = time;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_TIME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getTime() {
        return time;
    }

    @JsonProperty(JSON_PROPERTY_TIME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTime(String time) {
        this.time = time;
    }

    public ShellyUniStatusResponse unixtime(Integer unixtime) {

        this.unixtime = unixtime;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_UNIXTIME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getUnixtime() {
        return unixtime;
    }

    @JsonProperty(JSON_PROPERTY_UNIXTIME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setUnixtime(Integer unixtime) {
        this.unixtime = unixtime;
    }

    public ShellyUniStatusResponse wifiSta(WifiStatus wifiSta) {

        this.wifiSta = wifiSta;
        return this;
    }

    @Nullable
    @Valid

    @JsonProperty(JSON_PROPERTY_WIFI_STA)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public WifiStatus getWifiSta() {
        return wifiSta;
    }

    @JsonProperty(JSON_PROPERTY_WIFI_STA)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setWifiSta(WifiStatus wifiSta) {
        this.wifiSta = wifiSta;
    }

    public ShellyUniStatusResponse cloud(Cloud cloud) {

        this.cloud = cloud;
        return this;
    }

    @Nullable
    @Valid

    @JsonProperty(JSON_PROPERTY_CLOUD)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Cloud getCloud() {
        return cloud;
    }

    @JsonProperty(JSON_PROPERTY_CLOUD)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setCloud(Cloud cloud) {
        this.cloud = cloud;
    }

    public ShellyUniStatusResponse relays(List<@Valid Relay> relays) {

        this.relays = relays;
        return this;
    }

    public ShellyUniStatusResponse addRelaysItem(Relay relaysItem) {
        if (this.relays == null) {
            this.relays = new ArrayList<>();
        }
        this.relays.add(relaysItem);
        return this;
    }

    @Nullable
    @Valid

    @JsonProperty(JSON_PROPERTY_RELAYS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<@Valid Relay> getRelays() {
        return relays;
    }

    @JsonProperty(JSON_PROPERTY_RELAYS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRelays(List<@Valid Relay> relays) {
        this.relays = relays;
    }

    public ShellyUniStatusResponse extTemperature(Map<String, ExternalTemperatureValue> extTemperature) {

        this.extTemperature = extTemperature;
        return this;
    }

    public ShellyUniStatusResponse putExtTemperatureItem(String key, ExternalTemperatureValue extTemperatureItem) {
        if (this.extTemperature == null) {
            this.extTemperature = new HashMap<>();
        }
        this.extTemperature.put(key, extTemperatureItem);
        return this;
    }

    @Nullable
    @Valid

    @JsonProperty(JSON_PROPERTY_EXT_TEMPERATURE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Map<String, ExternalTemperatureValue> getExtTemperature() {
        return extTemperature;
    }

    @JsonProperty(JSON_PROPERTY_EXT_TEMPERATURE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setExtTemperature(Map<String, ExternalTemperatureValue> extTemperature) {
        this.extTemperature = extTemperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShellyUniStatusResponse shellyUniStatusResponse = (ShellyUniStatusResponse) o;
        return Objects.equals(this.time, shellyUniStatusResponse.time)
                && Objects.equals(this.unixtime, shellyUniStatusResponse.unixtime)
                && Objects.equals(this.wifiSta, shellyUniStatusResponse.wifiSta)
                && Objects.equals(this.cloud, shellyUniStatusResponse.cloud)
                && Objects.equals(this.relays, shellyUniStatusResponse.relays)
                && Objects.equals(this.extTemperature, shellyUniStatusResponse.extTemperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, unixtime, wifiSta, cloud, relays, extTemperature);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ShellyUniStatusResponse {\n");
        sb.append("    time: ").append(toIndentedString(time)).append("\n");
        sb.append("    unixtime: ").append(toIndentedString(unixtime)).append("\n");
        sb.append("    wifiSta: ").append(toIndentedString(wifiSta)).append("\n");
        sb.append("    cloud: ").append(toIndentedString(cloud)).append("\n");
        sb.append("    relays: ").append(toIndentedString(relays)).append("\n");
        sb.append("    extTemperature: ").append(toIndentedString(extTemperature)).append("\n");
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
