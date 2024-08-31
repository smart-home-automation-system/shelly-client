
package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ ExternalTemperatureValue.JSON_PROPERTY_HW_I_D, ExternalTemperatureValue.JSON_PROPERTY_T_C })
@JsonTypeName("ExternalTemperature_value")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class ExternalTemperatureValue {
    public static final String JSON_PROPERTY_HW_I_D = "hwID";
    private String hwID;
    public static final String JSON_PROPERTY_T_C = "tC";
    private Float tC;

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

    public ExternalTemperatureValue tC(Float tC) {

        this.tC = tC;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_T_C)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Float gettC() {
        return tC;
    }

    @JsonProperty(JSON_PROPERTY_T_C)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void settC(Float tC) {
        this.tC = tC;
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
                && Objects.equals(this.tC, externalTemperatureValue.tC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwID, tC);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExternalTemperatureValue {\n");
        sb.append("    hwID: ").append(toIndentedString(hwID)).append("\n");
        sb.append("    tC: ").append(toIndentedString(tC)).append("\n");
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
