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

@JsonPropertyOrder({Gen2LightStatus.JSON_PROPERTY_ID, Gen2LightStatus.JSON_PROPERTY_SOURCE,
        Gen2LightStatus.JSON_PROPERTY_OUTPUT, Gen2LightStatus.JSON_PROPERTY_BRIGHTNESS,
        Gen2LightStatus.JSON_PROPERTY_TRANSITION, Gen2LightStatus.JSON_PROPERTY_TEMPERATURE,
        Gen2LightStatus.JSON_PROPERTY_AENERGY, Gen2LightStatus.JSON_PROPERTY_APOWER,
        Gen2LightStatus.JSON_PROPERTY_VOLTAGE, Gen2LightStatus.JSON_PROPERTY_CURRENT,
        Gen2LightStatus.JSON_PROPERTY_ERRORS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class Gen2LightStatus {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_SOURCE = "source";
    private String source;
    public static final String JSON_PROPERTY_OUTPUT = "output";
    private Boolean output;
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

    public Gen2LightStatus id(Integer id) {
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

    public Gen2LightStatus source(String source) {
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

    public Gen2LightStatus output(Boolean output) {
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

    public Gen2LightStatus brightness(Integer brightness) {
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

    public Gen2LightStatus transition(LightTransition transition) {
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

    public Gen2LightStatus temperature(ComponentTemperature temperature) {
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

    public Gen2LightStatus aenergy(EnergyCounter aenergy) {
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

    public Gen2LightStatus apower(Double apower) {
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

    public Gen2LightStatus voltage(Double voltage) {
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

    public Gen2LightStatus current(Double current) {
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

    public Gen2LightStatus errors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    public Gen2LightStatus addErrorsItem(String errorsItem) {
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
        Gen2LightStatus gen2LightStatus = (Gen2LightStatus) o;
        return Objects.equals(this.id, gen2LightStatus.id) && Objects.equals(this.source, gen2LightStatus.source)
                && Objects.equals(this.output, gen2LightStatus.output)
                && Objects.equals(this.brightness, gen2LightStatus.brightness)
                && Objects.equals(this.transition, gen2LightStatus.transition)
                && Objects.equals(this.temperature, gen2LightStatus.temperature)
                && Objects.equals(this.aenergy, gen2LightStatus.aenergy)
                && Objects.equals(this.apower, gen2LightStatus.apower)
                && Objects.equals(this.voltage, gen2LightStatus.voltage)
                && Objects.equals(this.current, gen2LightStatus.current)
                && Objects.equals(this.errors, gen2LightStatus.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, output, brightness, transition, temperature, aenergy, apower, voltage, current,
                errors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Gen2LightStatus {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    output: ").append(toIndentedString(output)).append("\n");
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
