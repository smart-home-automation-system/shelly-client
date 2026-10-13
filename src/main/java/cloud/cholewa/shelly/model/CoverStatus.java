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

@JsonPropertyOrder({CoverStatus.JSON_PROPERTY_ID, CoverStatus.JSON_PROPERTY_SOURCE, CoverStatus.JSON_PROPERTY_STATE,
        CoverStatus.JSON_PROPERTY_APOWER, CoverStatus.JSON_PROPERTY_VOLTAGE, CoverStatus.JSON_PROPERTY_CURRENT,
        CoverStatus.JSON_PROPERTY_PF, CoverStatus.JSON_PROPERTY_FREQ, CoverStatus.JSON_PROPERTY_AENERGY,
        CoverStatus.JSON_PROPERTY_TEMPERATURE, CoverStatus.JSON_PROPERTY_POS_CONTROL,
        CoverStatus.JSON_PROPERTY_LAST_DIRECTION, CoverStatus.JSON_PROPERTY_CURRENT_POS,
        CoverStatus.JSON_PROPERTY_TARGET_POS, CoverStatus.JSON_PROPERTY_ERRORS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class CoverStatus {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_SOURCE = "source";
    private String source;
    public static final String JSON_PROPERTY_STATE = "state";
    private String state;
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
    public static final String JSON_PROPERTY_POS_CONTROL = "pos_control";
    private Boolean posControl;
    public static final String JSON_PROPERTY_LAST_DIRECTION = "last_direction";
    private String lastDirection;
    public static final String JSON_PROPERTY_CURRENT_POS = "current_pos";
    private Integer currentPos;
    public static final String JSON_PROPERTY_TARGET_POS = "target_pos";
    private Integer targetPos;
    public static final String JSON_PROPERTY_ERRORS = "errors";
    private List<String> errors = new ArrayList<>();

    public CoverStatus id(Integer id) {
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

    public CoverStatus source(String source) {
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

    public CoverStatus state(String state) {
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

    public CoverStatus apower(Double apower) {
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

    public CoverStatus voltage(Double voltage) {
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

    public CoverStatus current(Double current) {
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

    public CoverStatus pf(Double pf) {
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

    public CoverStatus freq(Double freq) {
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

    public CoverStatus aenergy(EnergyCounter aenergy) {
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

    public CoverStatus temperature(ComponentTemperature temperature) {
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

    public CoverStatus posControl(Boolean posControl) {
        this.posControl = posControl;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_POS_CONTROL)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getPosControl() {
        return posControl;
    }

    @JsonProperty(JSON_PROPERTY_POS_CONTROL)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPosControl(Boolean posControl) {
        this.posControl = posControl;
    }

    public CoverStatus lastDirection(String lastDirection) {
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

    public CoverStatus currentPos(Integer currentPos) {
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

    public CoverStatus targetPos(Integer targetPos) {
        this.targetPos = targetPos;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TARGET_POS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getTargetPos() {
        return targetPos;
    }

    @JsonProperty(JSON_PROPERTY_TARGET_POS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTargetPos(Integer targetPos) {
        this.targetPos = targetPos;
    }

    public CoverStatus errors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    public CoverStatus addErrorsItem(String errorsItem) {
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
        CoverStatus coverStatus = (CoverStatus) o;
        return Objects.equals(this.id, coverStatus.id) && Objects.equals(this.source, coverStatus.source)
                && Objects.equals(this.state, coverStatus.state) && Objects.equals(this.apower, coverStatus.apower)
                && Objects.equals(this.voltage, coverStatus.voltage)
                && Objects.equals(this.current, coverStatus.current) && Objects.equals(this.pf, coverStatus.pf)
                && Objects.equals(this.freq, coverStatus.freq) && Objects.equals(this.aenergy, coverStatus.aenergy)
                && Objects.equals(this.temperature, coverStatus.temperature)
                && Objects.equals(this.posControl, coverStatus.posControl)
                && Objects.equals(this.lastDirection, coverStatus.lastDirection)
                && Objects.equals(this.currentPos, coverStatus.currentPos)
                && Objects.equals(this.targetPos, coverStatus.targetPos)
                && Objects.equals(this.errors, coverStatus.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, state, apower, voltage, current, pf, freq, aenergy, temperature, posControl,
                lastDirection, currentPos, targetPos, errors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CoverStatus {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    state: ").append(toIndentedString(state)).append("\n");
        sb.append("    apower: ").append(toIndentedString(apower)).append("\n");
        sb.append("    voltage: ").append(toIndentedString(voltage)).append("\n");
        sb.append("    current: ").append(toIndentedString(current)).append("\n");
        sb.append("    pf: ").append(toIndentedString(pf)).append("\n");
        sb.append("    freq: ").append(toIndentedString(freq)).append("\n");
        sb.append("    aenergy: ").append(toIndentedString(aenergy)).append("\n");
        sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
        sb.append("    posControl: ").append(toIndentedString(posControl)).append("\n");
        sb.append("    lastDirection: ").append(toIndentedString(lastDirection)).append("\n");
        sb.append("    currentPos: ").append(toIndentedString(currentPos)).append("\n");
        sb.append("    targetPos: ").append(toIndentedString(targetPos)).append("\n");
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
