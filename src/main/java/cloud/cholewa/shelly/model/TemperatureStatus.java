package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({TemperatureStatus.JSON_PROPERTY_ID, TemperatureStatus.JSON_PROPERTY_T_C,
        TemperatureStatus.JSON_PROPERTY_T_F})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class TemperatureStatus {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_T_C = "tC";
    private Double tC;
    public static final String JSON_PROPERTY_T_F = "tF";
    private Double tF;

    public TemperatureStatus id(Integer id) {
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

    public TemperatureStatus tC(Double tC) {
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

    public TemperatureStatus tF(Double tF) {
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
        TemperatureStatus temperatureStatus = (TemperatureStatus) o;
        return Objects.equals(this.id, temperatureStatus.id) && Objects.equals(this.tC, temperatureStatus.tC)
                && Objects.equals(this.tF, temperatureStatus.tF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tC, tF);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TemperatureStatus {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
