package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({MotionStatus.JSON_PROPERTY_SENSOR, MotionStatus.JSON_PROPERTY_LUX, MotionStatus.JSON_PROPERTY_BAT})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class MotionStatus {

    public static final String JSON_PROPERTY_SENSOR = "sensor";
    private MotionSensorState sensor;
    public static final String JSON_PROPERTY_LUX = "lux";
    private MotionLux lux;
    public static final String JSON_PROPERTY_BAT = "bat";
    private MotionBattery bat;

    public MotionStatus sensor(MotionSensorState sensor) {
        this.sensor = sensor;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_SENSOR)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public MotionSensorState getSensor() {
        return sensor;
    }

    @JsonProperty(JSON_PROPERTY_SENSOR)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setSensor(MotionSensorState sensor) {
        this.sensor = sensor;
    }

    public MotionStatus lux(MotionLux lux) {
        this.lux = lux;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_LUX)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public MotionLux getLux() {
        return lux;
    }

    @JsonProperty(JSON_PROPERTY_LUX)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setLux(MotionLux lux) {
        this.lux = lux;
    }

    public MotionStatus bat(MotionBattery bat) {
        this.bat = bat;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_BAT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public MotionBattery getBat() {
        return bat;
    }

    @JsonProperty(JSON_PROPERTY_BAT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setBat(MotionBattery bat) {
        this.bat = bat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MotionStatus motionStatus = (MotionStatus) o;
        return Objects.equals(this.sensor, motionStatus.sensor) && Objects.equals(this.lux, motionStatus.lux)
                && Objects.equals(this.bat, motionStatus.bat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensor, lux, bat);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MotionStatus {\n");
        sb.append("    sensor: ").append(toIndentedString(sensor)).append("\n");
        sb.append("    lux: ").append(toIndentedString(lux)).append("\n");
        sb.append("    bat: ").append(toIndentedString(bat)).append("\n");
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
