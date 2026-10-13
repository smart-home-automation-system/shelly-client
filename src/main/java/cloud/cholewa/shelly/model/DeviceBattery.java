package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({DeviceBattery.JSON_PROPERTY_V, DeviceBattery.JSON_PROPERTY_PERCENT})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class DeviceBattery {

    public static final String JSON_PROPERTY_V = "V";
    private Double V;
    public static final String JSON_PROPERTY_PERCENT = "percent";
    private Integer percent;

    public DeviceBattery V(Double V) {
        this.V = V;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_V)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getV() {
        return V;
    }

    @JsonProperty(JSON_PROPERTY_V)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setV(Double V) {
        this.V = V;
    }

    public DeviceBattery percent(Integer percent) {
        this.percent = percent;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_PERCENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getPercent() {
        return percent;
    }

    @JsonProperty(JSON_PROPERTY_PERCENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceBattery deviceBattery = (DeviceBattery) o;
        return Objects.equals(this.V, deviceBattery.V) && Objects.equals(this.percent, deviceBattery.percent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(V, percent);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeviceBattery {\n");
        sb.append("    V: ").append(toIndentedString(V)).append("\n");
        sb.append("    percent: ").append(toIndentedString(percent)).append("\n");
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
