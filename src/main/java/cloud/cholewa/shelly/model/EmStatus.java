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

@JsonPropertyOrder({EmStatus.JSON_PROPERTY_ID, EmStatus.JSON_PROPERTY_A_CURRENT, EmStatus.JSON_PROPERTY_A_VOLTAGE,
        EmStatus.JSON_PROPERTY_A_ACT_POWER, EmStatus.JSON_PROPERTY_A_APRT_POWER, EmStatus.JSON_PROPERTY_A_PF,
        EmStatus.JSON_PROPERTY_A_FREQ, EmStatus.JSON_PROPERTY_A_ERRORS, EmStatus.JSON_PROPERTY_B_CURRENT,
        EmStatus.JSON_PROPERTY_B_VOLTAGE, EmStatus.JSON_PROPERTY_B_ACT_POWER, EmStatus.JSON_PROPERTY_B_APRT_POWER,
        EmStatus.JSON_PROPERTY_B_PF, EmStatus.JSON_PROPERTY_B_FREQ, EmStatus.JSON_PROPERTY_B_ERRORS,
        EmStatus.JSON_PROPERTY_C_CURRENT, EmStatus.JSON_PROPERTY_C_VOLTAGE, EmStatus.JSON_PROPERTY_C_ACT_POWER,
        EmStatus.JSON_PROPERTY_C_APRT_POWER, EmStatus.JSON_PROPERTY_C_PF, EmStatus.JSON_PROPERTY_C_FREQ,
        EmStatus.JSON_PROPERTY_C_ERRORS, EmStatus.JSON_PROPERTY_N_CURRENT, EmStatus.JSON_PROPERTY_N_ERRORS,
        EmStatus.JSON_PROPERTY_TOTAL_CURRENT, EmStatus.JSON_PROPERTY_TOTAL_ACT_POWER,
        EmStatus.JSON_PROPERTY_TOTAL_APRT_POWER, EmStatus.JSON_PROPERTY_ERRORS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class EmStatus {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_A_CURRENT = "a_current";
    private Double aCurrent;
    public static final String JSON_PROPERTY_A_VOLTAGE = "a_voltage";
    private Double aVoltage;
    public static final String JSON_PROPERTY_A_ACT_POWER = "a_act_power";
    private Double aActPower;
    public static final String JSON_PROPERTY_A_APRT_POWER = "a_aprt_power";
    private Double aAprtPower;
    public static final String JSON_PROPERTY_A_PF = "a_pf";
    private Double aPf;
    public static final String JSON_PROPERTY_A_FREQ = "a_freq";
    private Double aFreq;
    public static final String JSON_PROPERTY_A_ERRORS = "a_errors";
    private List<String> aErrors = new ArrayList<>();
    public static final String JSON_PROPERTY_B_CURRENT = "b_current";
    private Double bCurrent;
    public static final String JSON_PROPERTY_B_VOLTAGE = "b_voltage";
    private Double bVoltage;
    public static final String JSON_PROPERTY_B_ACT_POWER = "b_act_power";
    private Double bActPower;
    public static final String JSON_PROPERTY_B_APRT_POWER = "b_aprt_power";
    private Double bAprtPower;
    public static final String JSON_PROPERTY_B_PF = "b_pf";
    private Double bPf;
    public static final String JSON_PROPERTY_B_FREQ = "b_freq";
    private Double bFreq;
    public static final String JSON_PROPERTY_B_ERRORS = "b_errors";
    private List<String> bErrors = new ArrayList<>();
    public static final String JSON_PROPERTY_C_CURRENT = "c_current";
    private Double cCurrent;
    public static final String JSON_PROPERTY_C_VOLTAGE = "c_voltage";
    private Double cVoltage;
    public static final String JSON_PROPERTY_C_ACT_POWER = "c_act_power";
    private Double cActPower;
    public static final String JSON_PROPERTY_C_APRT_POWER = "c_aprt_power";
    private Double cAprtPower;
    public static final String JSON_PROPERTY_C_PF = "c_pf";
    private Double cPf;
    public static final String JSON_PROPERTY_C_FREQ = "c_freq";
    private Double cFreq;
    public static final String JSON_PROPERTY_C_ERRORS = "c_errors";
    private List<String> cErrors = new ArrayList<>();
    public static final String JSON_PROPERTY_N_CURRENT = "n_current";
    private Double nCurrent;
    public static final String JSON_PROPERTY_N_ERRORS = "n_errors";
    private List<String> nErrors = new ArrayList<>();
    public static final String JSON_PROPERTY_TOTAL_CURRENT = "total_current";
    private Double totalCurrent;
    public static final String JSON_PROPERTY_TOTAL_ACT_POWER = "total_act_power";
    private Double totalActPower;
    public static final String JSON_PROPERTY_TOTAL_APRT_POWER = "total_aprt_power";
    private Double totalAprtPower;
    public static final String JSON_PROPERTY_ERRORS = "errors";
    private List<String> errors = new ArrayList<>();

    public EmStatus id(Integer id) {
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

    public EmStatus aCurrent(Double aCurrent) {
        this.aCurrent = aCurrent;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_A_CURRENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getaCurrent() {
        return aCurrent;
    }

    @JsonProperty(JSON_PROPERTY_A_CURRENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setaCurrent(Double aCurrent) {
        this.aCurrent = aCurrent;
    }

    public EmStatus aVoltage(Double aVoltage) {
        this.aVoltage = aVoltage;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_A_VOLTAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getaVoltage() {
        return aVoltage;
    }

    @JsonProperty(JSON_PROPERTY_A_VOLTAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setaVoltage(Double aVoltage) {
        this.aVoltage = aVoltage;
    }

    public EmStatus aActPower(Double aActPower) {
        this.aActPower = aActPower;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_A_ACT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getaActPower() {
        return aActPower;
    }

    @JsonProperty(JSON_PROPERTY_A_ACT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setaActPower(Double aActPower) {
        this.aActPower = aActPower;
    }

    public EmStatus aAprtPower(Double aAprtPower) {
        this.aAprtPower = aAprtPower;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_A_APRT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getaAprtPower() {
        return aAprtPower;
    }

    @JsonProperty(JSON_PROPERTY_A_APRT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setaAprtPower(Double aAprtPower) {
        this.aAprtPower = aAprtPower;
    }

    public EmStatus aPf(Double aPf) {
        this.aPf = aPf;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_A_PF)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getaPf() {
        return aPf;
    }

    @JsonProperty(JSON_PROPERTY_A_PF)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setaPf(Double aPf) {
        this.aPf = aPf;
    }

    public EmStatus aFreq(Double aFreq) {
        this.aFreq = aFreq;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_A_FREQ)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getaFreq() {
        return aFreq;
    }

    @JsonProperty(JSON_PROPERTY_A_FREQ)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setaFreq(Double aFreq) {
        this.aFreq = aFreq;
    }

    public EmStatus aErrors(List<String> aErrors) {
        this.aErrors = aErrors;
        return this;
    }

    public EmStatus addAErrorsItem(String aErrorsItem) {
        if (this.aErrors == null) {
            this.aErrors = new ArrayList<>();
        }
        this.aErrors.add(aErrorsItem);
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_A_ERRORS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<String> getaErrors() {
        return aErrors;
    }

    @JsonProperty(JSON_PROPERTY_A_ERRORS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setaErrors(List<String> aErrors) {
        this.aErrors = aErrors;
    }

    public EmStatus bCurrent(Double bCurrent) {
        this.bCurrent = bCurrent;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_B_CURRENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getbCurrent() {
        return bCurrent;
    }

    @JsonProperty(JSON_PROPERTY_B_CURRENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setbCurrent(Double bCurrent) {
        this.bCurrent = bCurrent;
    }

    public EmStatus bVoltage(Double bVoltage) {
        this.bVoltage = bVoltage;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_B_VOLTAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getbVoltage() {
        return bVoltage;
    }

    @JsonProperty(JSON_PROPERTY_B_VOLTAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setbVoltage(Double bVoltage) {
        this.bVoltage = bVoltage;
    }

    public EmStatus bActPower(Double bActPower) {
        this.bActPower = bActPower;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_B_ACT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getbActPower() {
        return bActPower;
    }

    @JsonProperty(JSON_PROPERTY_B_ACT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setbActPower(Double bActPower) {
        this.bActPower = bActPower;
    }

    public EmStatus bAprtPower(Double bAprtPower) {
        this.bAprtPower = bAprtPower;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_B_APRT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getbAprtPower() {
        return bAprtPower;
    }

    @JsonProperty(JSON_PROPERTY_B_APRT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setbAprtPower(Double bAprtPower) {
        this.bAprtPower = bAprtPower;
    }

    public EmStatus bPf(Double bPf) {
        this.bPf = bPf;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_B_PF)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getbPf() {
        return bPf;
    }

    @JsonProperty(JSON_PROPERTY_B_PF)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setbPf(Double bPf) {
        this.bPf = bPf;
    }

    public EmStatus bFreq(Double bFreq) {
        this.bFreq = bFreq;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_B_FREQ)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getbFreq() {
        return bFreq;
    }

    @JsonProperty(JSON_PROPERTY_B_FREQ)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setbFreq(Double bFreq) {
        this.bFreq = bFreq;
    }

    public EmStatus bErrors(List<String> bErrors) {
        this.bErrors = bErrors;
        return this;
    }

    public EmStatus addBErrorsItem(String bErrorsItem) {
        if (this.bErrors == null) {
            this.bErrors = new ArrayList<>();
        }
        this.bErrors.add(bErrorsItem);
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_B_ERRORS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<String> getbErrors() {
        return bErrors;
    }

    @JsonProperty(JSON_PROPERTY_B_ERRORS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setbErrors(List<String> bErrors) {
        this.bErrors = bErrors;
    }

    public EmStatus cCurrent(Double cCurrent) {
        this.cCurrent = cCurrent;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_C_CURRENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getcCurrent() {
        return cCurrent;
    }

    @JsonProperty(JSON_PROPERTY_C_CURRENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setcCurrent(Double cCurrent) {
        this.cCurrent = cCurrent;
    }

    public EmStatus cVoltage(Double cVoltage) {
        this.cVoltage = cVoltage;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_C_VOLTAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getcVoltage() {
        return cVoltage;
    }

    @JsonProperty(JSON_PROPERTY_C_VOLTAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setcVoltage(Double cVoltage) {
        this.cVoltage = cVoltage;
    }

    public EmStatus cActPower(Double cActPower) {
        this.cActPower = cActPower;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_C_ACT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getcActPower() {
        return cActPower;
    }

    @JsonProperty(JSON_PROPERTY_C_ACT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setcActPower(Double cActPower) {
        this.cActPower = cActPower;
    }

    public EmStatus cAprtPower(Double cAprtPower) {
        this.cAprtPower = cAprtPower;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_C_APRT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getcAprtPower() {
        return cAprtPower;
    }

    @JsonProperty(JSON_PROPERTY_C_APRT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setcAprtPower(Double cAprtPower) {
        this.cAprtPower = cAprtPower;
    }

    public EmStatus cPf(Double cPf) {
        this.cPf = cPf;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_C_PF)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getcPf() {
        return cPf;
    }

    @JsonProperty(JSON_PROPERTY_C_PF)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setcPf(Double cPf) {
        this.cPf = cPf;
    }

    public EmStatus cFreq(Double cFreq) {
        this.cFreq = cFreq;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_C_FREQ)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getcFreq() {
        return cFreq;
    }

    @JsonProperty(JSON_PROPERTY_C_FREQ)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setcFreq(Double cFreq) {
        this.cFreq = cFreq;
    }

    public EmStatus cErrors(List<String> cErrors) {
        this.cErrors = cErrors;
        return this;
    }

    public EmStatus addCErrorsItem(String cErrorsItem) {
        if (this.cErrors == null) {
            this.cErrors = new ArrayList<>();
        }
        this.cErrors.add(cErrorsItem);
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_C_ERRORS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<String> getcErrors() {
        return cErrors;
    }

    @JsonProperty(JSON_PROPERTY_C_ERRORS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setcErrors(List<String> cErrors) {
        this.cErrors = cErrors;
    }

    public EmStatus nCurrent(Double nCurrent) {
        this.nCurrent = nCurrent;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_N_CURRENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getnCurrent() {
        return nCurrent;
    }

    @JsonProperty(JSON_PROPERTY_N_CURRENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setnCurrent(Double nCurrent) {
        this.nCurrent = nCurrent;
    }

    public EmStatus nErrors(List<String> nErrors) {
        this.nErrors = nErrors;
        return this;
    }

    public EmStatus addNErrorsItem(String nErrorsItem) {
        if (this.nErrors == null) {
            this.nErrors = new ArrayList<>();
        }
        this.nErrors.add(nErrorsItem);
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_N_ERRORS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<String> getnErrors() {
        return nErrors;
    }

    @JsonProperty(JSON_PROPERTY_N_ERRORS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setnErrors(List<String> nErrors) {
        this.nErrors = nErrors;
    }

    public EmStatus totalCurrent(Double totalCurrent) {
        this.totalCurrent = totalCurrent;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TOTAL_CURRENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getTotalCurrent() {
        return totalCurrent;
    }

    @JsonProperty(JSON_PROPERTY_TOTAL_CURRENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTotalCurrent(Double totalCurrent) {
        this.totalCurrent = totalCurrent;
    }

    public EmStatus totalActPower(Double totalActPower) {
        this.totalActPower = totalActPower;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TOTAL_ACT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getTotalActPower() {
        return totalActPower;
    }

    @JsonProperty(JSON_PROPERTY_TOTAL_ACT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTotalActPower(Double totalActPower) {
        this.totalActPower = totalActPower;
    }

    public EmStatus totalAprtPower(Double totalAprtPower) {
        this.totalAprtPower = totalAprtPower;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TOTAL_APRT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getTotalAprtPower() {
        return totalAprtPower;
    }

    @JsonProperty(JSON_PROPERTY_TOTAL_APRT_POWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTotalAprtPower(Double totalAprtPower) {
        this.totalAprtPower = totalAprtPower;
    }

    public EmStatus errors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    public EmStatus addErrorsItem(String errorsItem) {
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
        EmStatus emStatus = (EmStatus) o;
        return Objects.equals(this.id, emStatus.id) && Objects.equals(this.aCurrent, emStatus.aCurrent)
                && Objects.equals(this.aVoltage, emStatus.aVoltage)
                && Objects.equals(this.aActPower, emStatus.aActPower)
                && Objects.equals(this.aAprtPower, emStatus.aAprtPower) && Objects.equals(this.aPf, emStatus.aPf)
                && Objects.equals(this.aFreq, emStatus.aFreq) && Objects.equals(this.aErrors, emStatus.aErrors)
                && Objects.equals(this.bCurrent, emStatus.bCurrent) && Objects.equals(this.bVoltage, emStatus.bVoltage)
                && Objects.equals(this.bActPower, emStatus.bActPower)
                && Objects.equals(this.bAprtPower, emStatus.bAprtPower) && Objects.equals(this.bPf, emStatus.bPf)
                && Objects.equals(this.bFreq, emStatus.bFreq) && Objects.equals(this.bErrors, emStatus.bErrors)
                && Objects.equals(this.cCurrent, emStatus.cCurrent) && Objects.equals(this.cVoltage, emStatus.cVoltage)
                && Objects.equals(this.cActPower, emStatus.cActPower)
                && Objects.equals(this.cAprtPower, emStatus.cAprtPower) && Objects.equals(this.cPf, emStatus.cPf)
                && Objects.equals(this.cFreq, emStatus.cFreq) && Objects.equals(this.cErrors, emStatus.cErrors)
                && Objects.equals(this.nCurrent, emStatus.nCurrent) && Objects.equals(this.nErrors, emStatus.nErrors)
                && Objects.equals(this.totalCurrent, emStatus.totalCurrent)
                && Objects.equals(this.totalActPower, emStatus.totalActPower)
                && Objects.equals(this.totalAprtPower, emStatus.totalAprtPower)
                && Objects.equals(this.errors, emStatus.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aCurrent, aVoltage, aActPower, aAprtPower, aPf, aFreq, aErrors, bCurrent, bVoltage,
                bActPower, bAprtPower, bPf, bFreq, bErrors, cCurrent, cVoltage, cActPower, cAprtPower, cPf, cFreq,
                cErrors, nCurrent, nErrors, totalCurrent, totalActPower, totalAprtPower, errors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EmStatus {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    aCurrent: ").append(toIndentedString(aCurrent)).append("\n");
        sb.append("    aVoltage: ").append(toIndentedString(aVoltage)).append("\n");
        sb.append("    aActPower: ").append(toIndentedString(aActPower)).append("\n");
        sb.append("    aAprtPower: ").append(toIndentedString(aAprtPower)).append("\n");
        sb.append("    aPf: ").append(toIndentedString(aPf)).append("\n");
        sb.append("    aFreq: ").append(toIndentedString(aFreq)).append("\n");
        sb.append("    aErrors: ").append(toIndentedString(aErrors)).append("\n");
        sb.append("    bCurrent: ").append(toIndentedString(bCurrent)).append("\n");
        sb.append("    bVoltage: ").append(toIndentedString(bVoltage)).append("\n");
        sb.append("    bActPower: ").append(toIndentedString(bActPower)).append("\n");
        sb.append("    bAprtPower: ").append(toIndentedString(bAprtPower)).append("\n");
        sb.append("    bPf: ").append(toIndentedString(bPf)).append("\n");
        sb.append("    bFreq: ").append(toIndentedString(bFreq)).append("\n");
        sb.append("    bErrors: ").append(toIndentedString(bErrors)).append("\n");
        sb.append("    cCurrent: ").append(toIndentedString(cCurrent)).append("\n");
        sb.append("    cVoltage: ").append(toIndentedString(cVoltage)).append("\n");
        sb.append("    cActPower: ").append(toIndentedString(cActPower)).append("\n");
        sb.append("    cAprtPower: ").append(toIndentedString(cAprtPower)).append("\n");
        sb.append("    cPf: ").append(toIndentedString(cPf)).append("\n");
        sb.append("    cFreq: ").append(toIndentedString(cFreq)).append("\n");
        sb.append("    cErrors: ").append(toIndentedString(cErrors)).append("\n");
        sb.append("    nCurrent: ").append(toIndentedString(nCurrent)).append("\n");
        sb.append("    nErrors: ").append(toIndentedString(nErrors)).append("\n");
        sb.append("    totalCurrent: ").append(toIndentedString(totalCurrent)).append("\n");
        sb.append("    totalActPower: ").append(toIndentedString(totalActPower)).append("\n");
        sb.append("    totalAprtPower: ").append(toIndentedString(totalAprtPower)).append("\n");
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
