package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({DevicePowerStatus.JSON_PROPERTY_ID, DevicePowerStatus.JSON_PROPERTY_BATTERY,
        DevicePowerStatus.JSON_PROPERTY_EXTERNAL})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class DevicePowerStatus {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_BATTERY = "battery";
    private DeviceBattery battery;
    public static final String JSON_PROPERTY_EXTERNAL = "external";
    private ExternalPower external;

    public DevicePowerStatus id(Integer id) {
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

    public DevicePowerStatus battery(DeviceBattery battery) {
        this.battery = battery;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_BATTERY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public DeviceBattery getBattery() {
        return battery;
    }

    @JsonProperty(JSON_PROPERTY_BATTERY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setBattery(DeviceBattery battery) {
        this.battery = battery;
    }

    public DevicePowerStatus external(ExternalPower external) {
        this.external = external;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_EXTERNAL)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public ExternalPower getExternal() {
        return external;
    }

    @JsonProperty(JSON_PROPERTY_EXTERNAL)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setExternal(ExternalPower external) {
        this.external = external;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DevicePowerStatus devicePowerStatus = (DevicePowerStatus) o;
        return Objects.equals(this.id, devicePowerStatus.id) && Objects.equals(this.battery, devicePowerStatus.battery)
                && Objects.equals(this.external, devicePowerStatus.external);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, battery, external);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DevicePowerStatus {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    battery: ").append(toIndentedString(battery)).append("\n");
        sb.append("    external: ").append(toIndentedString(external)).append("\n");
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
