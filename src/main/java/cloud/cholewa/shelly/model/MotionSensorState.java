package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({MotionSensorState.JSON_PROPERTY_MOTION, MotionSensorState.JSON_PROPERTY_VIBRATION,
        MotionSensorState.JSON_PROPERTY_TIMESTAMP, MotionSensorState.JSON_PROPERTY_ACTIVE,
        MotionSensorState.JSON_PROPERTY_IS_VALID})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class MotionSensorState {

    public static final String JSON_PROPERTY_MOTION = "motion";
    private Boolean motion;
    public static final String JSON_PROPERTY_VIBRATION = "vibration";
    private Boolean vibration;
    public static final String JSON_PROPERTY_TIMESTAMP = "timestamp";
    private Long timestamp;
    public static final String JSON_PROPERTY_ACTIVE = "active";
    private Boolean active;
    public static final String JSON_PROPERTY_IS_VALID = "is_valid";
    private Boolean isValid;

    public MotionSensorState motion(Boolean motion) {
        this.motion = motion;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_MOTION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getMotion() {
        return motion;
    }

    @JsonProperty(JSON_PROPERTY_MOTION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setMotion(Boolean motion) {
        this.motion = motion;
    }

    public MotionSensorState vibration(Boolean vibration) {
        this.vibration = vibration;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_VIBRATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getVibration() {
        return vibration;
    }

    @JsonProperty(JSON_PROPERTY_VIBRATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setVibration(Boolean vibration) {
        this.vibration = vibration;
    }

    public MotionSensorState timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TIMESTAMP)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty(JSON_PROPERTY_TIMESTAMP)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public MotionSensorState active(Boolean active) {
        this.active = active;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_ACTIVE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getActive() {
        return active;
    }

    @JsonProperty(JSON_PROPERTY_ACTIVE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setActive(Boolean active) {
        this.active = active;
    }

    public MotionSensorState isValid(Boolean isValid) {
        this.isValid = isValid;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_IS_VALID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getIsValid() {
        return isValid;
    }

    @JsonProperty(JSON_PROPERTY_IS_VALID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MotionSensorState motionSensorState = (MotionSensorState) o;
        return Objects.equals(this.motion, motionSensorState.motion)
                && Objects.equals(this.vibration, motionSensorState.vibration)
                && Objects.equals(this.timestamp, motionSensorState.timestamp)
                && Objects.equals(this.active, motionSensorState.active)
                && Objects.equals(this.isValid, motionSensorState.isValid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(motion, vibration, timestamp, active, isValid);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MotionSensorState {\n");
        sb.append("    motion: ").append(toIndentedString(motion)).append("\n");
        sb.append("    vibration: ").append(toIndentedString(vibration)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    active: ").append(toIndentedString(active)).append("\n");
        sb.append("    isValid: ").append(toIndentedString(isValid)).append("\n");
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
