package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ExternalTemperatureValue.JSON_PROPERTY_HW_I_D, ExternalTemperatureValue.JSON_PROPERTY_T_C,
        ExternalTemperatureValue.JSON_PROPERTY_T_F})
@JsonTypeName("ExternalTemperature_value")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class ExternalTemperatureValue {

    public static final String JSON_PROPERTY_HW_I_D = "hwID";
    private String hwID;
    public static final String JSON_PROPERTY_T_C = "tC";
    private Double tC;
    public static final String JSON_PROPERTY_T_F = "tF";
    private Double tF;

    public ExternalTemperatureValue hwID(String hwID) {
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

    public ExternalTemperatureValue tC(Double tC) {
        this.tC = tC;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_T_C)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double gettC() {
        return tC;
    }

    @JsonProperty(JSON_PROPERTY_T_C)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void settC(Double tC) {
        this.tC = tC;
    }

    public ExternalTemperatureValue tF(Double tF) {
        this.tF = tF;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_T_F)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double gettF() {
        return tF;
    }

    @JsonProperty(JSON_PROPERTY_T_F)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void settF(Double tF) {
        this.tF = tF;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExternalTemperatureValue externalTemperatureValue = (ExternalTemperatureValue) o;
        return Objects.equals(this.hwID, externalTemperatureValue.hwID)
                && Objects.equals(this.tC, externalTemperatureValue.tC)
                && Objects.equals(this.tF, externalTemperatureValue.tF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwID, tC, tF);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExternalTemperatureValue {\n");
        sb.append("    hwID: ").append(toIndentedString(hwID)).append("\n");
        sb.append("    tC: ").append(toIndentedString(tC)).append("\n");
        sb.append("    tF: ").append(toIndentedString(tF)).append("\n");
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
