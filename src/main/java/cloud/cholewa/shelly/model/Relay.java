
package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ Relay.JSON_PROPERTY_ISON, Relay.JSON_PROPERTY_HAS_TIMER, Relay.JSON_PROPERTY_TIMER_STARTED,
        Relay.JSON_PROPERTY_TIMER_DURATION, Relay.JSON_PROPERTY_TIMER_REMAINING, Relay.JSON_PROPERTY_SOURCE })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class Relay {
    public static final String JSON_PROPERTY_ISON = "ison";
    private Boolean ison;
    public static final String JSON_PROPERTY_HAS_TIMER = "has_timer";
    private Boolean hasTimer;
    public static final String JSON_PROPERTY_TIMER_STARTED = "timer_started";
    private Integer timerStarted;
    public static final String JSON_PROPERTY_TIMER_DURATION = "timer_duration";
    private Integer timerDuration;
    public static final String JSON_PROPERTY_TIMER_REMAINING = "timer_remaining";
    private Integer timerRemaining;
    public static final String JSON_PROPERTY_SOURCE = "source";
    private String source;

    public Relay ison(Boolean ison) {

        this.ison = ison;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_ISON)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getIson() {
        return ison;
    }

    @JsonProperty(JSON_PROPERTY_ISON)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setIson(Boolean ison) {
        this.ison = ison;
    }

    public Relay hasTimer(Boolean hasTimer) {

        this.hasTimer = hasTimer;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_HAS_TIMER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getHasTimer() {
        return hasTimer;
    }

    @JsonProperty(JSON_PROPERTY_HAS_TIMER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setHasTimer(Boolean hasTimer) {
        this.hasTimer = hasTimer;
    }

    public Relay timerStarted(Integer timerStarted) {

        this.timerStarted = timerStarted;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_TIMER_STARTED)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getTimerStarted() {
        return timerStarted;
    }

    @JsonProperty(JSON_PROPERTY_TIMER_STARTED)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTimerStarted(Integer timerStarted) {
        this.timerStarted = timerStarted;
    }

    public Relay timerDuration(Integer timerDuration) {

        this.timerDuration = timerDuration;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_TIMER_DURATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getTimerDuration() {
        return timerDuration;
    }

    @JsonProperty(JSON_PROPERTY_TIMER_DURATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTimerDuration(Integer timerDuration) {
        this.timerDuration = timerDuration;
    }

    public Relay timerRemaining(Integer timerRemaining) {

        this.timerRemaining = timerRemaining;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_TIMER_REMAINING)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getTimerRemaining() {
        return timerRemaining;
    }

    @JsonProperty(JSON_PROPERTY_TIMER_REMAINING)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTimerRemaining(Integer timerRemaining) {
        this.timerRemaining = timerRemaining;
    }

    public Relay source(String source) {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Relay relay = (Relay) o;
        return Objects.equals(this.ison, relay.ison) && Objects.equals(this.hasTimer, relay.hasTimer)
                && Objects.equals(this.timerStarted, relay.timerStarted)
                && Objects.equals(this.timerDuration, relay.timerDuration)
                && Objects.equals(this.timerRemaining, relay.timerRemaining)
                && Objects.equals(this.source, relay.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ison, hasTimer, timerStarted, timerDuration, timerRemaining, source);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Relay {\n");
        sb.append("    ison: ").append(toIndentedString(ison)).append("\n");
        sb.append("    hasTimer: ").append(toIndentedString(hasTimer)).append("\n");
        sb.append("    timerStarted: ").append(toIndentedString(timerStarted)).append("\n");
        sb.append("    timerDuration: ").append(toIndentedString(timerDuration)).append("\n");
        sb.append("    timerRemaining: ").append(toIndentedString(timerRemaining)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
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
