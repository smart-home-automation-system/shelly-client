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

@JsonPropertyOrder({Pm1Status.JSON_PROPERTY_ID, Pm1Status.JSON_PROPERTY_VOLTAGE, Pm1Status.JSON_PROPERTY_CURRENT,
        Pm1Status.JSON_PROPERTY_APOWER, Pm1Status.JSON_PROPERTY_FREQ, Pm1Status.JSON_PROPERTY_AENERGY,
        Pm1Status.JSON_PROPERTY_RET_AENERGY, Pm1Status.JSON_PROPERTY_ERRORS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class Pm1Status {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_VOLTAGE = "voltage";
    private Double voltage;
    public static final String JSON_PROPERTY_CURRENT = "current";
    private Double current;
    public static final String JSON_PROPERTY_APOWER = "apower";
    private Double apower;
    public static final String JSON_PROPERTY_FREQ = "freq";
    private Double freq;
    public static final String JSON_PROPERTY_AENERGY = "aenergy";
    private EnergyCounter aenergy;
    public static final String JSON_PROPERTY_RET_AENERGY = "ret_aenergy";
    private EnergyCounter retAenergy;
    public static final String JSON_PROPERTY_ERRORS = "errors";
    private List<String> errors = new ArrayList<>();

    public Pm1Status id(Integer id) {
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

    public Pm1Status voltage(Double voltage) {
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

    public Pm1Status current(Double current) {
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

    public Pm1Status apower(Double apower) {
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

    public Pm1Status freq(Double freq) {
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

    public Pm1Status aenergy(EnergyCounter aenergy) {
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

    public Pm1Status retAenergy(EnergyCounter retAenergy) {
        this.retAenergy = retAenergy;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_RET_AENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public EnergyCounter getRetAenergy() {
        return retAenergy;
    }

    @JsonProperty(JSON_PROPERTY_RET_AENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRetAenergy(EnergyCounter retAenergy) {
        this.retAenergy = retAenergy;
    }

    public Pm1Status errors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    public Pm1Status addErrorsItem(String errorsItem) {
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
        Pm1Status pm1Status = (Pm1Status) o;
        return Objects.equals(this.id, pm1Status.id) && Objects.equals(this.voltage, pm1Status.voltage)
                && Objects.equals(this.current, pm1Status.current) && Objects.equals(this.apower, pm1Status.apower)
                && Objects.equals(this.freq, pm1Status.freq) && Objects.equals(this.aenergy, pm1Status.aenergy)
                && Objects.equals(this.retAenergy, pm1Status.retAenergy)
                && Objects.equals(this.errors, pm1Status.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, voltage, current, apower, freq, aenergy, retAenergy, errors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Pm1Status {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    voltage: ").append(toIndentedString(voltage)).append("\n");
        sb.append("    current: ").append(toIndentedString(current)).append("\n");
        sb.append("    apower: ").append(toIndentedString(apower)).append("\n");
        sb.append("    freq: ").append(toIndentedString(freq)).append("\n");
        sb.append("    aenergy: ").append(toIndentedString(aenergy)).append("\n");
        sb.append("    retAenergy: ").append(toIndentedString(retAenergy)).append("\n");
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
