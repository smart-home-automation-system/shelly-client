package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ExternalHumidityValue.JSON_PROPERTY_HW_I_D, ExternalHumidityValue.JSON_PROPERTY_HUM})
@JsonTypeName("ExternalHumidity_value")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class ExternalHumidityValue {

    public static final String JSON_PROPERTY_HW_I_D = "hwID";
    private String hwID;
    public static final String JSON_PROPERTY_HUM = "hum";
    private Double hum;

    public ExternalHumidityValue hwID(String hwID) {
        this.hwID = hwID;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_HW_I_D)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getHwID() {
        return hwID;
    }

    @JsonProperty(JSON_PROPERTY_HW_I_D)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setHwID(String hwID) {
        this.hwID = hwID;
    }

    public ExternalHumidityValue hum(Double hum) {
        this.hum = hum;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_HUM)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getHum() {
        return hum;
    }

    @JsonProperty(JSON_PROPERTY_HUM)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setHum(Double hum) {
        this.hum = hum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExternalHumidityValue externalHumidityValue = (ExternalHumidityValue) o;
        return Objects.equals(this.hwID, externalHumidityValue.hwID)
                && Objects.equals(this.hum, externalHumidityValue.hum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwID, hum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExternalHumidityValue {\n");
        sb.append("    hwID: ").append(toIndentedString(hwID)).append("\n");
        sb.append("    hum: ").append(toIndentedString(hum)).append("\n");
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
