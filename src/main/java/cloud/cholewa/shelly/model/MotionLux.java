package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({MotionLux.JSON_PROPERTY_VALUE, MotionLux.JSON_PROPERTY_ILLUMINATION,
        MotionLux.JSON_PROPERTY_IS_VALID})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class MotionLux {

    public static final String JSON_PROPERTY_VALUE = "value";
    private Integer value;
    public static final String JSON_PROPERTY_ILLUMINATION = "illumination";
    private String illumination;
    public static final String JSON_PROPERTY_IS_VALID = "is_valid";
    private Boolean isValid;

    public MotionLux value(Integer value) {
        this.value = value;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_VALUE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getValue() {
        return value;
    }

    @JsonProperty(JSON_PROPERTY_VALUE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setValue(Integer value) {
        this.value = value;
    }

    public MotionLux illumination(String illumination) {
        this.illumination = illumination;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_ILLUMINATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getIllumination() {
        return illumination;
    }

    @JsonProperty(JSON_PROPERTY_ILLUMINATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setIllumination(String illumination) {
        this.illumination = illumination;
    }

    public MotionLux isValid(Boolean isValid) {
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
        MotionLux motionLux = (MotionLux) o;
        return Objects.equals(this.value, motionLux.value) && Objects.equals(this.illumination, motionLux.illumination)
                && Objects.equals(this.isValid, motionLux.isValid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, illumination, isValid);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MotionLux {\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    illumination: ").append(toIndentedString(illumination)).append("\n");
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
