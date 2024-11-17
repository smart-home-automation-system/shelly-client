
package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ ShellyProRelayResponse.JSON_PROPERTY_ISON, ShellyProRelayResponse.JSON_PROPERTY_TIMER_DURATION,
        ShellyProRelayResponse.JSON_PROPERTY_TIMER_REMAINING })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class ShellyProRelayResponse {
    public static final String JSON_PROPERTY_ISON = "ison";
    private Boolean ison;
    public static final String JSON_PROPERTY_TIMER_DURATION = "timer_duration";
    private Integer timerDuration;
    public static final String JSON_PROPERTY_TIMER_REMAINING = "timer_remaining";
    private Integer timerRemaining;

    public ShellyProRelayResponse ison(Boolean ison) {

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

    public ShellyProRelayResponse timerDuration(Integer timerDuration) {

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

    public ShellyProRelayResponse timerRemaining(Integer timerRemaining) {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShellyProRelayResponse shellyProRelayResponse = (ShellyProRelayResponse) o;
        return Objects.equals(this.ison, shellyProRelayResponse.ison)
                && Objects.equals(this.timerDuration, shellyProRelayResponse.timerDuration)
                && Objects.equals(this.timerRemaining, shellyProRelayResponse.timerRemaining);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ison, timerDuration, timerRemaining);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ShellyProRelayResponse {\n");
        sb.append("    ison: ").append(toIndentedString(ison)).append("\n");
        sb.append("    timerDuration: ").append(toIndentedString(timerDuration)).append("\n");
        sb.append("    timerRemaining: ").append(toIndentedString(timerRemaining)).append("\n");
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
