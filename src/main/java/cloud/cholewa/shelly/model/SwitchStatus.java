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

@JsonPropertyOrder({SwitchStatus.JSON_PROPERTY_ID, SwitchStatus.JSON_PROPERTY_SOURCE, SwitchStatus.JSON_PROPERTY_OUTPUT,
        SwitchStatus.JSON_PROPERTY_APOWER, SwitchStatus.JSON_PROPERTY_VOLTAGE, SwitchStatus.JSON_PROPERTY_CURRENT,
        SwitchStatus.JSON_PROPERTY_PF, SwitchStatus.JSON_PROPERTY_FREQ, SwitchStatus.JSON_PROPERTY_AENERGY,
        SwitchStatus.JSON_PROPERTY_TEMPERATURE, SwitchStatus.JSON_PROPERTY_ERRORS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class SwitchStatus {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_SOURCE = "source";
    private String source;
    public static final String JSON_PROPERTY_OUTPUT = "output";
    private Boolean output;
    public static final String JSON_PROPERTY_APOWER = "apower";
    private Double apower;
    public static final String JSON_PROPERTY_VOLTAGE = "voltage";
    private Double voltage;
    public static final String JSON_PROPERTY_CURRENT = "current";
    private Double current;
    public static final String JSON_PROPERTY_PF = "pf";
    private Double pf;
    public static final String JSON_PROPERTY_FREQ = "freq";
    private Double freq;
    public static final String JSON_PROPERTY_AENERGY = "aenergy";
    private EnergyCounter aenergy;
    public static final String JSON_PROPERTY_TEMPERATURE = "temperature";
    private ComponentTemperature temperature;
    public static final String JSON_PROPERTY_ERRORS = "errors";
    private List<String> errors = new ArrayList<>();

    public SwitchStatus id(Integer id) {
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

    public SwitchStatus source(String source) {
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

    public SwitchStatus output(Boolean output) {
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

    public SwitchStatus apower(Double apower) {
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

    public SwitchStatus voltage(Double voltage) {
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

    public SwitchStatus current(Double current) {
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

    public SwitchStatus pf(Double pf) {
        this.pf = pf;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_PF)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getPf() {
        return pf;
    }

    @JsonProperty(JSON_PROPERTY_PF)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPf(Double pf) {
        this.pf = pf;
    }

    public SwitchStatus freq(Double freq) {
        this.freq = freq;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_FREQ)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getFreq() {
        return freq;
    }

    @JsonProperty(JSON_PROPERTY_FREQ)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setFreq(Double freq) {
        this.freq = freq;
    }

    public SwitchStatus aenergy(EnergyCounter aenergy) {
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

    public SwitchStatus temperature(ComponentTemperature temperature) {
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

    public SwitchStatus errors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    public SwitchStatus addErrorsItem(String errorsItem) {
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
        SwitchStatus switchStatus = (SwitchStatus) o;
        return Objects.equals(this.id, switchStatus.id) && Objects.equals(this.source, switchStatus.source)
                && Objects.equals(this.output, switchStatus.output) && Objects.equals(this.apower, switchStatus.apower)
                && Objects.equals(this.voltage, switchStatus.voltage)
                && Objects.equals(this.current, switchStatus.current) && Objects.equals(this.pf, switchStatus.pf)
                && Objects.equals(this.freq, switchStatus.freq) && Objects.equals(this.aenergy, switchStatus.aenergy)
                && Objects.equals(this.temperature, switchStatus.temperature)
                && Objects.equals(this.errors, switchStatus.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, output, apower, voltage, current, pf, freq, aenergy, temperature, errors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SwitchStatus {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    output: ").append(toIndentedString(output)).append("\n");
        sb.append("    apower: ").append(toIndentedString(apower)).append("\n");
        sb.append("    voltage: ").append(toIndentedString(voltage)).append("\n");
        sb.append("    current: ").append(toIndentedString(current)).append("\n");
        sb.append("    pf: ").append(toIndentedString(pf)).append("\n");
        sb.append("    freq: ").append(toIndentedString(freq)).append("\n");
        sb.append("    aenergy: ").append(toIndentedString(aenergy)).append("\n");
        sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
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
