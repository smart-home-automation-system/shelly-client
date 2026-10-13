package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({RgbwStatus.JSON_PROPERTY_ID, RgbwStatus.JSON_PROPERTY_SOURCE, RgbwStatus.JSON_PROPERTY_OUTPUT,
        RgbwStatus.JSON_PROPERTY_RGB, RgbwStatus.JSON_PROPERTY_WHITE, RgbwStatus.JSON_PROPERTY_BRIGHTNESS,
        RgbwStatus.JSON_PROPERTY_TRANSITION, RgbwStatus.JSON_PROPERTY_TEMPERATURE, RgbwStatus.JSON_PROPERTY_AENERGY,
        RgbwStatus.JSON_PROPERTY_APOWER, RgbwStatus.JSON_PROPERTY_VOLTAGE, RgbwStatus.JSON_PROPERTY_CURRENT,
        RgbwStatus.JSON_PROPERTY_ERRORS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class RgbwStatus {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_SOURCE = "source";
    private String source;
    public static final String JSON_PROPERTY_OUTPUT = "output";
    private Boolean output;
    public static final String JSON_PROPERTY_RGB = "rgb";
    private List<Integer> rgb = new ArrayList<>();
    public static final String JSON_PROPERTY_WHITE = "white";
    private Integer white;
    public static final String JSON_PROPERTY_BRIGHTNESS = "brightness";
    private Integer brightness;
    public static final String JSON_PROPERTY_TRANSITION = "transition";
    private LightTransition transition;
    public static final String JSON_PROPERTY_TEMPERATURE = "temperature";
    private ComponentTemperature temperature;
    public static final String JSON_PROPERTY_AENERGY = "aenergy";
    private EnergyCounter aenergy;
    public static final String JSON_PROPERTY_APOWER = "apower";
    private Double apower;
    public static final String JSON_PROPERTY_VOLTAGE = "voltage";
    private Double voltage;
    public static final String JSON_PROPERTY_CURRENT = "current";
    private Double current;
    public static final String JSON_PROPERTY_ERRORS = "errors";
    private List<String> errors = new ArrayList<>();

    public RgbwStatus id(Integer id) {
        this.id = id;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getId() {
        return id;
    }

    @JsonProperty(JSON_PROPERTY_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setId(Integer id) {
        this.id = id;
    }

    public RgbwStatus source(String source) {
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

    public RgbwStatus output(Boolean output) {
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

    public RgbwStatus rgb(List<Integer> rgb) {
        this.rgb = rgb;
        return this;
    }

    public RgbwStatus addRgbItem(Integer rgbItem) {
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

    public RgbwStatus white(Integer white) {
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

    public RgbwStatus brightness(Integer brightness) {
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

    public RgbwStatus transition(LightTransition transition) {
        this.transition = transition;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_TRANSITION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public LightTransition getTransition() {
        return transition;
    }

    @JsonProperty(JSON_PROPERTY_TRANSITION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTransition(LightTransition transition) {
        this.transition = transition;
    }

    public RgbwStatus temperature(ComponentTemperature temperature) {
        this.temperature = temperature;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_TEMPERATURE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public ComponentTemperature getTemperature() {
        return temperature;
    }

    @JsonProperty(JSON_PROPERTY_TEMPERATURE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTemperature(ComponentTemperature temperature) {
        this.temperature = temperature;
    }

    public RgbwStatus aenergy(EnergyCounter aenergy) {
        this.aenergy = aenergy;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_AENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public EnergyCounter getAenergy() {
        return aenergy;
    }

    @JsonProperty(JSON_PROPERTY_AENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setAenergy(EnergyCounter aenergy) {
        this.aenergy = aenergy;
    }

    public RgbwStatus apower(Double apower) {
        this.apower = apower;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_APOWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getApower() {
        return apower;
    }

    @JsonProperty(JSON_PROPERTY_APOWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setApower(Double apower) {
        this.apower = apower;
    }

    public RgbwStatus voltage(Double voltage) {
        this.voltage = voltage;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_VOLTAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getVoltage() {
        return voltage;
    }

    @JsonProperty(JSON_PROPERTY_VOLTAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public RgbwStatus current(Double current) {
        this.current = current;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_CURRENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getCurrent() {
        return current;
    }

    @JsonProperty(JSON_PROPERTY_CURRENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setCurrent(Double current) {
        this.current = current;
    }

    public RgbwStatus errors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    public RgbwStatus addErrorsItem(String errorsItem) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(errorsItem);
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_ERRORS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<String> getErrors() {
        return errors;
    }

    @JsonProperty(JSON_PROPERTY_ERRORS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RgbwStatus rgbwStatus = (RgbwStatus) o;
        return Objects.equals(this.id, rgbwStatus.id) && Objects.equals(this.source, rgbwStatus.source)
                && Objects.equals(this.output, rgbwStatus.output) && Objects.equals(this.rgb, rgbwStatus.rgb)
                && Objects.equals(this.white, rgbwStatus.white)
                && Objects.equals(this.brightness, rgbwStatus.brightness)
                && Objects.equals(this.transition, rgbwStatus.transition)
                && Objects.equals(this.temperature, rgbwStatus.temperature)
                && Objects.equals(this.aenergy, rgbwStatus.aenergy) && Objects.equals(this.apower, rgbwStatus.apower)
                && Objects.equals(this.voltage, rgbwStatus.voltage) && Objects.equals(this.current, rgbwStatus.current)
                && Objects.equals(this.errors, rgbwStatus.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, output, rgb, white, brightness, transition, temperature, aenergy, apower,
                voltage, current, errors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RgbwStatus {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    output: ").append(toIndentedString(output)).append("\n");
        sb.append("    rgb: ").append(toIndentedString(rgb)).append("\n");
        sb.append("    white: ").append(toIndentedString(white)).append("\n");
        sb.append("    brightness: ").append(toIndentedString(brightness)).append("\n");
        sb.append("    transition: ").append(toIndentedString(transition)).append("\n");
        sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
        sb.append("    aenergy: ").append(toIndentedString(aenergy)).append("\n");
        sb.append("    apower: ").append(toIndentedString(apower)).append("\n");
        sb.append("    voltage: ").append(toIndentedString(voltage)).append("\n");
        sb.append("    current: ").append(toIndentedString(current)).append("\n");
        sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
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
