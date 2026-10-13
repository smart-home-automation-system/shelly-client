package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ComponentTemperature.JSON_PROPERTY_T_C, ComponentTemperature.JSON_PROPERTY_T_F})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class ComponentTemperature {

    public static final String JSON_PROPERTY_T_C = "tC";
    private Double tC;
    public static final String JSON_PROPERTY_T_F = "tF";
    private Double tF;

    public ComponentTemperature tC(Double tC) {
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

    public ComponentTemperature tF(Double tF) {
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
        ComponentTemperature componentTemperature = (ComponentTemperature) o;
        return Objects.equals(this.tC, componentTemperature.tC) && Objects.equals(this.tF, componentTemperature.tF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tC, tF);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ComponentTemperature {\n");
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
