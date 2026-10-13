package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({LightTransition.JSON_PROPERTY_TARGET, LightTransition.JSON_PROPERTY_STARTED_AT,
        LightTransition.JSON_PROPERTY_DURATION})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class LightTransition {

    public static final String JSON_PROPERTY_TARGET = "target";
    private LightTransitionTarget target;
    public static final String JSON_PROPERTY_STARTED_AT = "started_at";
    private Double startedAt;
    public static final String JSON_PROPERTY_DURATION = "duration";
    private Double duration;

    public LightTransition target(LightTransitionTarget target) {
        this.target = target;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_TARGET)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public LightTransitionTarget getTarget() {
        return target;
    }

    @JsonProperty(JSON_PROPERTY_TARGET)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTarget(LightTransitionTarget target) {
        this.target = target;
    }

    public LightTransition startedAt(Double startedAt) {
        this.startedAt = startedAt;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_STARTED_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getStartedAt() {
        return startedAt;
    }

    @JsonProperty(JSON_PROPERTY_STARTED_AT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setStartedAt(Double startedAt) {
        this.startedAt = startedAt;
    }

    public LightTransition duration(Double duration) {
        this.duration = duration;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_DURATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getDuration() {
        return duration;
    }

    @JsonProperty(JSON_PROPERTY_DURATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDuration(Double duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LightTransition lightTransition = (LightTransition) o;
        return Objects.equals(this.target, lightTransition.target)
                && Objects.equals(this.startedAt, lightTransition.startedAt)
                && Objects.equals(this.duration, lightTransition.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, startedAt, duration);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LightTransition {\n");
        sb.append("    target: ").append(toIndentedString(target)).append("\n");
        sb.append("    startedAt: ").append(toIndentedString(startedAt)).append("\n");
        sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
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
