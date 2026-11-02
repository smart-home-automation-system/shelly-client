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

@JsonPropertyOrder({Gen1DeviceStatus.JSON_PROPERTY_MODE, Gen1DeviceStatus.JSON_PROPERTY_UNIXTIME,
        Gen1DeviceStatus.JSON_PROPERTY_WIFI_STA, Gen1DeviceStatus.JSON_PROPERTY_CLOUD,
        Gen1DeviceStatus.JSON_PROPERTY_ROLLERS, Gen1DeviceStatus.JSON_PROPERTY_LIGHTS,
        Gen1DeviceStatus.JSON_PROPERTY_RELAYS, Gen1DeviceStatus.JSON_PROPERTY_EXT_SENSORS,
        Gen1DeviceStatus.JSON_PROPERTY_EXT_TEMPERATURE, Gen1DeviceStatus.JSON_PROPERTY_EXT_HUMIDITY})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class Gen1DeviceStatus {

    public static final String JSON_PROPERTY_MODE = "mode";
    private String mode;
    public static final String JSON_PROPERTY_UNIXTIME = "unixtime";
    private Long unixtime;
    public static final String JSON_PROPERTY_WIFI_STA = "wifi_sta";
    private WifiStatus wifiSta;
    public static final String JSON_PROPERTY_CLOUD = "cloud";
    private Cloud cloud;
    public static final String JSON_PROPERTY_ROLLERS = "rollers";
    private List<@Valid RollerStatus> rollers = new ArrayList<>();
    public static final String JSON_PROPERTY_LIGHTS = "lights";
    private List<@Valid Gen1LightStatus> lights = new ArrayList<>();
    public static final String JSON_PROPERTY_RELAYS = "relays";
    private List<@Valid Relay> relays = new ArrayList<>();
    public static final String JSON_PROPERTY_EXT_SENSORS = "ext_sensors";
    private ExternalSensors extSensors;
    public static final String JSON_PROPERTY_EXT_TEMPERATURE = "ext_temperature";
    private Map<String, ExternalTemperatureValue> extTemperature = new HashMap<>();
    public static final String JSON_PROPERTY_EXT_HUMIDITY = "ext_humidity";
    private Map<String, ExternalHumidityValue> extHumidity = new HashMap<>();

    public Gen1DeviceStatus mode(String mode) {
        this.mode = mode;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_MODE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getMode() {
        return mode;
    }

    @JsonProperty(JSON_PROPERTY_MODE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setMode(String mode) {
        this.mode = mode;
    }

    public Gen1DeviceStatus unixtime(Long unixtime) {
        this.unixtime = unixtime;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_UNIXTIME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Long getUnixtime() {
        return unixtime;
    }

    @JsonProperty(JSON_PROPERTY_UNIXTIME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setUnixtime(Long unixtime) {
        this.unixtime = unixtime;
    }

    public Gen1DeviceStatus wifiSta(WifiStatus wifiSta) {
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

    public Gen1DeviceStatus cloud(Cloud cloud) {
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

    public Gen1DeviceStatus rollers(List<@Valid RollerStatus> rollers) {
        this.rollers = rollers;
        return this;
    }

    public Gen1DeviceStatus addRollersItem(RollerStatus rollersItem) {
        if (this.rollers == null) {
            this.rollers = new ArrayList<>();
        }
        this.rollers.add(rollersItem);
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_ROLLERS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<@Valid RollerStatus> getRollers() {
        return rollers;
    }

    @JsonProperty(JSON_PROPERTY_ROLLERS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRollers(List<@Valid RollerStatus> rollers) {
        this.rollers = rollers;
    }

    public Gen1DeviceStatus lights(List<@Valid Gen1LightStatus> lights) {
        this.lights = lights;
        return this;
    }

    public Gen1DeviceStatus addLightsItem(Gen1LightStatus lightsItem) {
        if (this.lights == null) {
            this.lights = new ArrayList<>();
        }
        this.lights.add(lightsItem);
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_LIGHTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<@Valid Gen1LightStatus> getLights() {
        return lights;
    }

    @JsonProperty(JSON_PROPERTY_LIGHTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setLights(List<@Valid Gen1LightStatus> lights) {
        this.lights = lights;
    }

    public Gen1DeviceStatus relays(List<@Valid Relay> relays) {
        this.relays = relays;
        return this;
    }

    public Gen1DeviceStatus addRelaysItem(Relay relaysItem) {
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

    public Gen1DeviceStatus extSensors(ExternalSensors extSensors) {
        this.extSensors = extSensors;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_EXT_SENSORS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public ExternalSensors getExtSensors() {
        return extSensors;
    }

    @JsonProperty(JSON_PROPERTY_EXT_SENSORS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setExtSensors(ExternalSensors extSensors) {
        this.extSensors = extSensors;
    }

    public Gen1DeviceStatus extTemperature(Map<String, ExternalTemperatureValue> extTemperature) {
        this.extTemperature = extTemperature;
        return this;
    }

    public Gen1DeviceStatus putExtTemperatureItem(String key, ExternalTemperatureValue extTemperatureItem) {
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

    public Gen1DeviceStatus extHumidity(Map<String, ExternalHumidityValue> extHumidity) {
        this.extHumidity = extHumidity;
        return this;
    }

    public Gen1DeviceStatus putExtHumidityItem(String key, ExternalHumidityValue extHumidityItem) {
        if (this.extHumidity == null) {
            this.extHumidity = new HashMap<>();
        }
        this.extHumidity.put(key, extHumidityItem);
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_EXT_HUMIDITY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Map<String, ExternalHumidityValue> getExtHumidity() {
        return extHumidity;
    }

    @JsonProperty(JSON_PROPERTY_EXT_HUMIDITY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setExtHumidity(Map<String, ExternalHumidityValue> extHumidity) {
        this.extHumidity = extHumidity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Gen1DeviceStatus gen1DeviceStatus = (Gen1DeviceStatus) o;
        return Objects.equals(this.mode, gen1DeviceStatus.mode)
                && Objects.equals(this.unixtime, gen1DeviceStatus.unixtime)
                && Objects.equals(this.wifiSta, gen1DeviceStatus.wifiSta)
                && Objects.equals(this.cloud, gen1DeviceStatus.cloud)
                && Objects.equals(this.rollers, gen1DeviceStatus.rollers)
                && Objects.equals(this.lights, gen1DeviceStatus.lights)
                && Objects.equals(this.relays, gen1DeviceStatus.relays)
                && Objects.equals(this.extSensors, gen1DeviceStatus.extSensors)
                && Objects.equals(this.extTemperature, gen1DeviceStatus.extTemperature)
                && Objects.equals(this.extHumidity, gen1DeviceStatus.extHumidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mode, unixtime, wifiSta, cloud, rollers, lights, relays, extSensors, extTemperature,
                extHumidity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Gen1DeviceStatus {\n");
        sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
        sb.append("    unixtime: ").append(toIndentedString(unixtime)).append("\n");
        sb.append("    wifiSta: ").append(toIndentedString(wifiSta)).append("\n");
        sb.append("    cloud: ").append(toIndentedString(cloud)).append("\n");
        sb.append("    rollers: ").append(toIndentedString(rollers)).append("\n");
        sb.append("    lights: ").append(toIndentedString(lights)).append("\n");
        sb.append("    relays: ").append(toIndentedString(relays)).append("\n");
        sb.append("    extSensors: ").append(toIndentedString(extSensors)).append("\n");
        sb.append("    extTemperature: ").append(toIndentedString(extTemperature)).append("\n");
        sb.append("    extHumidity: ").append(toIndentedString(extHumidity)).append("\n");
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
