package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({Gen1LightStatus.JSON_PROPERTY_ISON, Gen1LightStatus.JSON_PROPERTY_SOURCE,
        Gen1LightStatus.JSON_PROPERTY_HAS_TIMER, Gen1LightStatus.JSON_PROPERTY_TIMER_STARTED,
        Gen1LightStatus.JSON_PROPERTY_TIMER_DURATION, Gen1LightStatus.JSON_PROPERTY_TIMER_REMAINING,
        Gen1LightStatus.JSON_PROPERTY_MODE, Gen1LightStatus.JSON_PROPERTY_RED, Gen1LightStatus.JSON_PROPERTY_GREEN,
        Gen1LightStatus.JSON_PROPERTY_BLUE, Gen1LightStatus.JSON_PROPERTY_WHITE, Gen1LightStatus.JSON_PROPERTY_GAIN,
        Gen1LightStatus.JSON_PROPERTY_BRIGHTNESS, Gen1LightStatus.JSON_PROPERTY_TEMP,
        Gen1LightStatus.JSON_PROPERTY_EFFECT, Gen1LightStatus.JSON_PROPERTY_TRANSITION,
        Gen1LightStatus.JSON_PROPERTY_POWER, Gen1LightStatus.JSON_PROPERTY_OVERPOWER})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class Gen1LightStatus {

    public static final String JSON_PROPERTY_ISON = "ison";
    private Boolean ison;
    public static final String JSON_PROPERTY_SOURCE = "source";
    private String source;
    public static final String JSON_PROPERTY_HAS_TIMER = "has_timer";
    private Boolean hasTimer;
    public static final String JSON_PROPERTY_TIMER_STARTED = "timer_started";
    private Long timerStarted;
    public static final String JSON_PROPERTY_TIMER_DURATION = "timer_duration";
    private Integer timerDuration;
    public static final String JSON_PROPERTY_TIMER_REMAINING = "timer_remaining";
    private Integer timerRemaining;
    public static final String JSON_PROPERTY_MODE = "mode";
    private String mode;
    public static final String JSON_PROPERTY_RED = "red";
    private Integer red;
    public static final String JSON_PROPERTY_GREEN = "green";
    private Integer green;
    public static final String JSON_PROPERTY_BLUE = "blue";
    private Integer blue;
    public static final String JSON_PROPERTY_WHITE = "white";
    private Integer white;
    public static final String JSON_PROPERTY_GAIN = "gain";
    private Integer gain;
    public static final String JSON_PROPERTY_BRIGHTNESS = "brightness";
    private Integer brightness;
    public static final String JSON_PROPERTY_TEMP = "temp";
    private Integer temp;
    public static final String JSON_PROPERTY_EFFECT = "effect";
    private Integer effect;
    public static final String JSON_PROPERTY_TRANSITION = "transition";
    private Integer transition;
    public static final String JSON_PROPERTY_POWER = "power";
    private Double power;
    public static final String JSON_PROPERTY_OVERPOWER = "overpower";
    private Boolean overpower;

    public Gen1LightStatus ison(Boolean ison) {
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

    public Gen1LightStatus source(String source) {
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

    public Gen1LightStatus hasTimer(Boolean hasTimer) {
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

    public Gen1LightStatus timerStarted(Long timerStarted) {
        this.timerStarted = timerStarted;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TIMER_STARTED)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Long getTimerStarted() {
        return timerStarted;
    }

    @JsonProperty(JSON_PROPERTY_TIMER_STARTED)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTimerStarted(Long timerStarted) {
        this.timerStarted = timerStarted;
    }

    public Gen1LightStatus timerDuration(Integer timerDuration) {
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

    public Gen1LightStatus timerRemaining(Integer timerRemaining) {
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

    public Gen1LightStatus mode(String mode) {
        this.mode = mode;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_MODE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getMode() {
        return mode;
    }

    @JsonProperty(JSON_PROPERTY_MODE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setMode(String mode) {
        this.mode = mode;
    }

    public Gen1LightStatus red(Integer red) {
        this.red = red;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_RED)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getRed() {
        return red;
    }

    @JsonProperty(JSON_PROPERTY_RED)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRed(Integer red) {
        this.red = red;
    }

    public Gen1LightStatus green(Integer green) {
        this.green = green;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_GREEN)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getGreen() {
        return green;
    }

    @JsonProperty(JSON_PROPERTY_GREEN)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setGreen(Integer green) {
        this.green = green;
    }

    public Gen1LightStatus blue(Integer blue) {
        this.blue = blue;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_BLUE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getBlue() {
        return blue;
    }

    @JsonProperty(JSON_PROPERTY_BLUE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setBlue(Integer blue) {
        this.blue = blue;
    }

    public Gen1LightStatus white(Integer white) {
        this.white = white;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_WHITE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getWhite() {
        return white;
    }

    @JsonProperty(JSON_PROPERTY_WHITE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setWhite(Integer white) {
        this.white = white;
    }

    public Gen1LightStatus gain(Integer gain) {
        this.gain = gain;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_GAIN)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getGain() {
        return gain;
    }

    @JsonProperty(JSON_PROPERTY_GAIN)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setGain(Integer gain) {
        this.gain = gain;
    }

    public Gen1LightStatus brightness(Integer brightness) {
        this.brightness = brightness;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_BRIGHTNESS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getBrightness() {
        return brightness;
    }

    @JsonProperty(JSON_PROPERTY_BRIGHTNESS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }

    public Gen1LightStatus temp(Integer temp) {
        this.temp = temp;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TEMP)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getTemp() {
        return temp;
    }

    @JsonProperty(JSON_PROPERTY_TEMP)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Gen1LightStatus effect(Integer effect) {
        this.effect = effect;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_EFFECT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getEffect() {
        return effect;
    }

    @JsonProperty(JSON_PROPERTY_EFFECT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setEffect(Integer effect) {
        this.effect = effect;
    }

    public Gen1LightStatus transition(Integer transition) {
        this.transition = transition;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TRANSITION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getTransition() {
        return transition;
    }

    @JsonProperty(JSON_PROPERTY_TRANSITION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTransition(Integer transition) {
        this.transition = transition;
    }

    public Gen1LightStatus power(Double power) {
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

    public Gen1LightStatus overpower(Boolean overpower) {
        this.overpower = overpower;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_OVERPOWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getOverpower() {
        return overpower;
    }

    @JsonProperty(JSON_PROPERTY_OVERPOWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setOverpower(Boolean overpower) {
        this.overpower = overpower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Gen1LightStatus gen1LightStatus = (Gen1LightStatus) o;
        return Objects.equals(this.ison, gen1LightStatus.ison) && Objects.equals(this.source, gen1LightStatus.source)
                && Objects.equals(this.hasTimer, gen1LightStatus.hasTimer)
                && Objects.equals(this.timerStarted, gen1LightStatus.timerStarted)
                && Objects.equals(this.timerDuration, gen1LightStatus.timerDuration)
                && Objects.equals(this.timerRemaining, gen1LightStatus.timerRemaining)
                && Objects.equals(this.mode, gen1LightStatus.mode) && Objects.equals(this.red, gen1LightStatus.red)
                && Objects.equals(this.green, gen1LightStatus.green) && Objects.equals(this.blue, gen1LightStatus.blue)
                && Objects.equals(this.white, gen1LightStatus.white) && Objects.equals(this.gain, gen1LightStatus.gain)
                && Objects.equals(this.brightness, gen1LightStatus.brightness)
                && Objects.equals(this.temp, gen1LightStatus.temp)
                && Objects.equals(this.effect, gen1LightStatus.effect)
                && Objects.equals(this.transition, gen1LightStatus.transition)
                && Objects.equals(this.power, gen1LightStatus.power)
                && Objects.equals(this.overpower, gen1LightStatus.overpower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ison, source, hasTimer, timerStarted, timerDuration, timerRemaining, mode, red, green, blue,
                white, gain, brightness, temp, effect, transition, power, overpower);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Gen1LightStatus {\n");
        sb.append("    ison: ").append(toIndentedString(ison)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    hasTimer: ").append(toIndentedString(hasTimer)).append("\n");
        sb.append("    timerStarted: ").append(toIndentedString(timerStarted)).append("\n");
        sb.append("    timerDuration: ").append(toIndentedString(timerDuration)).append("\n");
        sb.append("    timerRemaining: ").append(toIndentedString(timerRemaining)).append("\n");
        sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
        sb.append("    red: ").append(toIndentedString(red)).append("\n");
        sb.append("    green: ").append(toIndentedString(green)).append("\n");
        sb.append("    blue: ").append(toIndentedString(blue)).append("\n");
        sb.append("    white: ").append(toIndentedString(white)).append("\n");
        sb.append("    gain: ").append(toIndentedString(gain)).append("\n");
        sb.append("    brightness: ").append(toIndentedString(brightness)).append("\n");
        sb.append("    temp: ").append(toIndentedString(temp)).append("\n");
        sb.append("    effect: ").append(toIndentedString(effect)).append("\n");
        sb.append("    transition: ").append(toIndentedString(transition)).append("\n");
        sb.append("    power: ").append(toIndentedString(power)).append("\n");
        sb.append("    overpower: ").append(toIndentedString(overpower)).append("\n");
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
