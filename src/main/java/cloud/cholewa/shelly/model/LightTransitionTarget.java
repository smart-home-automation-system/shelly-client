package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({LightTransitionTarget.JSON_PROPERTY_OUTPUT, LightTransitionTarget.JSON_PROPERTY_BRIGHTNESS,
        LightTransitionTarget.JSON_PROPERTY_RGB, LightTransitionTarget.JSON_PROPERTY_WHITE})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class LightTransitionTarget {

    public static final String JSON_PROPERTY_OUTPUT = "output";
    private Boolean output;
    public static final String JSON_PROPERTY_BRIGHTNESS = "brightness";
    private Integer brightness;
    public static final String JSON_PROPERTY_RGB = "rgb";
    private List<Integer> rgb = new ArrayList<>();
    public static final String JSON_PROPERTY_WHITE = "white";
    private Integer white;

    public LightTransitionTarget output(Boolean output) {
        this.output = output;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_OUTPUT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getOutput() {
        return output;
    }

    @JsonProperty(JSON_PROPERTY_OUTPUT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setOutput(Boolean output) {
        this.output = output;
    }

    public LightTransitionTarget brightness(Integer brightness) {
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

    public LightTransitionTarget rgb(List<Integer> rgb) {
        this.rgb = rgb;
        return this;
    }

    public LightTransitionTarget addRgbItem(Integer rgbItem) {
        if (this.rgb == null) {
            this.rgb = new ArrayList<>();
        }
        this.rgb.add(rgbItem);
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_RGB)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<Integer> getRgb() {
        return rgb;
    }

    @JsonProperty(JSON_PROPERTY_RGB)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRgb(List<Integer> rgb) {
        this.rgb = rgb;
    }

    public LightTransitionTarget white(Integer white) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LightTransitionTarget lightTransitionTarget = (LightTransitionTarget) o;
        return Objects.equals(this.output, lightTransitionTarget.output)
                && Objects.equals(this.brightness, lightTransitionTarget.brightness)
                && Objects.equals(this.rgb, lightTransitionTarget.rgb)
                && Objects.equals(this.white, lightTransitionTarget.white);
    }

    @Override
    public int hashCode() {
        return Objects.hash(output, brightness, rgb, white);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LightTransitionTarget {\n");
        sb.append("    output: ").append(toIndentedString(output)).append("\n");
        sb.append("    brightness: ").append(toIndentedString(brightness)).append("\n");
        sb.append("    rgb: ").append(toIndentedString(rgb)).append("\n");
        sb.append("    white: ").append(toIndentedString(white)).append("\n");
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
