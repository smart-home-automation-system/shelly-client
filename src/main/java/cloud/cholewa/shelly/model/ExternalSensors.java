package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ExternalSensors.JSON_PROPERTY_TEMPERATURE_UNIT})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class ExternalSensors {

    public static final String JSON_PROPERTY_TEMPERATURE_UNIT = "temperature_unit";
    private String temperatureUnit;

    public ExternalSensors temperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TEMPERATURE_UNIT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    @JsonProperty(JSON_PROPERTY_TEMPERATURE_UNIT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExternalSensors externalSensors = (ExternalSensors) o;
        return Objects.equals(this.temperatureUnit, externalSensors.temperatureUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperatureUnit);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExternalSensors {\n");
        sb.append("    temperatureUnit: ").append(toIndentedString(temperatureUnit)).append("\n");
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
