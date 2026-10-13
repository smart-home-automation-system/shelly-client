package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({RollerStatus.JSON_PROPERTY_STATE, RollerStatus.JSON_PROPERTY_SOURCE,
        RollerStatus.JSON_PROPERTY_POWER, RollerStatus.JSON_PROPERTY_IS_VALID, RollerStatus.JSON_PROPERTY_SAFETY_SWITCH,
        RollerStatus.JSON_PROPERTY_OVERTEMPERATURE, RollerStatus.JSON_PROPERTY_STOP_REASON,
        RollerStatus.JSON_PROPERTY_LAST_DIRECTION, RollerStatus.JSON_PROPERTY_CURRENT_POS,
        RollerStatus.JSON_PROPERTY_CALIBRATING, RollerStatus.JSON_PROPERTY_POSITIONING})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class RollerStatus {

    public static final String JSON_PROPERTY_STATE = "state";
    private String state;
    public static final String JSON_PROPERTY_SOURCE = "source";
    private String source;
    public static final String JSON_PROPERTY_POWER = "power";
    private Double power;
    public static final String JSON_PROPERTY_IS_VALID = "is_valid";
    private Boolean isValid;
    public static final String JSON_PROPERTY_SAFETY_SWITCH = "safety_switch";
    private Boolean safetySwitch;
    public static final String JSON_PROPERTY_OVERTEMPERATURE = "overtemperature";
    private Boolean overtemperature;
    public static final String JSON_PROPERTY_STOP_REASON = "stop_reason";
    private String stopReason;
    public static final String JSON_PROPERTY_LAST_DIRECTION = "last_direction";
    private String lastDirection;
    public static final String JSON_PROPERTY_CURRENT_POS = "current_pos";
    private Integer currentPos;
    public static final String JSON_PROPERTY_CALIBRATING = "calibrating";
    private Boolean calibrating;
    public static final String JSON_PROPERTY_POSITIONING = "positioning";
    private Boolean positioning;

    public RollerStatus state(String state) {
        this.state = state;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_STATE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getState() {
        return state;
    }

    @JsonProperty(JSON_PROPERTY_STATE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setState(String state) {
        this.state = state;
    }

    public RollerStatus source(String source) {
        this.source = source;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_SOURCE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getSource() {
        return source;
    }

    @JsonProperty(JSON_PROPERTY_SOURCE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setSource(String source) {
        this.source = source;
    }

    public RollerStatus power(Double power) {
        this.power = power;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getPower() {
        return power;
    }

    @JsonProperty(JSON_PROPERTY_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPower(Double power) {
        this.power = power;
    }

    public RollerStatus isValid(Boolean isValid) {
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

    public RollerStatus safetySwitch(Boolean safetySwitch) {
        this.safetySwitch = safetySwitch;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_SAFETY_SWITCH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getSafetySwitch() {
        return safetySwitch;
    }

    @JsonProperty(JSON_PROPERTY_SAFETY_SWITCH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setSafetySwitch(Boolean safetySwitch) {
        this.safetySwitch = safetySwitch;
    }

    public RollerStatus overtemperature(Boolean overtemperature) {
        this.overtemperature = overtemperature;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_OVERTEMPERATURE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getOvertemperature() {
        return overtemperature;
    }

    @JsonProperty(JSON_PROPERTY_OVERTEMPERATURE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setOvertemperature(Boolean overtemperature) {
        this.overtemperature = overtemperature;
    }

    public RollerStatus stopReason(String stopReason) {
        this.stopReason = stopReason;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_STOP_REASON)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getStopReason() {
        return stopReason;
    }

    @JsonProperty(JSON_PROPERTY_STOP_REASON)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    public RollerStatus lastDirection(String lastDirection) {
        this.lastDirection = lastDirection;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_LAST_DIRECTION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getLastDirection() {
        return lastDirection;
    }

    @JsonProperty(JSON_PROPERTY_LAST_DIRECTION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setLastDirection(String lastDirection) {
        this.lastDirection = lastDirection;
    }

    public RollerStatus currentPos(Integer currentPos) {
        this.currentPos = currentPos;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_CURRENT_POS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getCurrentPos() {
        return currentPos;
    }

    @JsonProperty(JSON_PROPERTY_CURRENT_POS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setCurrentPos(Integer currentPos) {
        this.currentPos = currentPos;
    }

    public RollerStatus calibrating(Boolean calibrating) {
        this.calibrating = calibrating;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_CALIBRATING)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getCalibrating() {
        return calibrating;
    }

    @JsonProperty(JSON_PROPERTY_CALIBRATING)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setCalibrating(Boolean calibrating) {
        this.calibrating = calibrating;
    }

    public RollerStatus positioning(Boolean positioning) {
        this.positioning = positioning;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_POSITIONING)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getPositioning() {
        return positioning;
    }

    @JsonProperty(JSON_PROPERTY_POSITIONING)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPositioning(Boolean positioning) {
        this.positioning = positioning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RollerStatus rollerStatus = (RollerStatus) o;
        return Objects.equals(this.state, rollerStatus.state) && Objects.equals(this.source, rollerStatus.source)
                && Objects.equals(this.power, rollerStatus.power) && Objects.equals(this.isValid, rollerStatus.isValid)
                && Objects.equals(this.safetySwitch, rollerStatus.safetySwitch)
                && Objects.equals(this.overtemperature, rollerStatus.overtemperature)
                && Objects.equals(this.stopReason, rollerStatus.stopReason)
                && Objects.equals(this.lastDirection, rollerStatus.lastDirection)
                && Objects.equals(this.currentPos, rollerStatus.currentPos)
                && Objects.equals(this.calibrating, rollerStatus.calibrating)
                && Objects.equals(this.positioning, rollerStatus.positioning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, source, power, isValid, safetySwitch, overtemperature, stopReason, lastDirection,
                currentPos, calibrating, positioning);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RollerStatus {\n");
        sb.append("    state: ").append(toIndentedString(state)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    power: ").append(toIndentedString(power)).append("\n");
        sb.append("    isValid: ").append(toIndentedString(isValid)).append("\n");
        sb.append("    safetySwitch: ").append(toIndentedString(safetySwitch)).append("\n");
        sb.append("    overtemperature: ").append(toIndentedString(overtemperature)).append("\n");
        sb.append("    stopReason: ").append(toIndentedString(stopReason)).append("\n");
        sb.append("    lastDirection: ").append(toIndentedString(lastDirection)).append("\n");
        sb.append("    currentPos: ").append(toIndentedString(currentPos)).append("\n");
        sb.append("    calibrating: ").append(toIndentedString(calibrating)).append("\n");
        sb.append("    positioning: ").append(toIndentedString(positioning)).append("\n");
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
