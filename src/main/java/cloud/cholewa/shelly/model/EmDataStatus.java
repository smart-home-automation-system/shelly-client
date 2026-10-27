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

@JsonPropertyOrder({EmDataStatus.JSON_PROPERTY_ID, EmDataStatus.JSON_PROPERTY_A_TOTAL_ACT_ENERGY,
        EmDataStatus.JSON_PROPERTY_A_TOTAL_ACT_RET_ENERGY, EmDataStatus.JSON_PROPERTY_B_TOTAL_ACT_ENERGY,
        EmDataStatus.JSON_PROPERTY_B_TOTAL_ACT_RET_ENERGY, EmDataStatus.JSON_PROPERTY_C_TOTAL_ACT_ENERGY,
        EmDataStatus.JSON_PROPERTY_C_TOTAL_ACT_RET_ENERGY, EmDataStatus.JSON_PROPERTY_TOTAL_ACT,
        EmDataStatus.JSON_PROPERTY_TOTAL_ACT_RET, EmDataStatus.JSON_PROPERTY_ERRORS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class EmDataStatus {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_A_TOTAL_ACT_ENERGY = "a_total_act_energy";
    private Double aTotalActEnergy;
    public static final String JSON_PROPERTY_A_TOTAL_ACT_RET_ENERGY = "a_total_act_ret_energy";
    private Double aTotalActRetEnergy;
    public static final String JSON_PROPERTY_B_TOTAL_ACT_ENERGY = "b_total_act_energy";
    private Double bTotalActEnergy;
    public static final String JSON_PROPERTY_B_TOTAL_ACT_RET_ENERGY = "b_total_act_ret_energy";
    private Double bTotalActRetEnergy;
    public static final String JSON_PROPERTY_C_TOTAL_ACT_ENERGY = "c_total_act_energy";
    private Double cTotalActEnergy;
    public static final String JSON_PROPERTY_C_TOTAL_ACT_RET_ENERGY = "c_total_act_ret_energy";
    private Double cTotalActRetEnergy;
    public static final String JSON_PROPERTY_TOTAL_ACT = "total_act";
    private Double totalAct;
    public static final String JSON_PROPERTY_TOTAL_ACT_RET = "total_act_ret";
    private Double totalActRet;
    public static final String JSON_PROPERTY_ERRORS = "errors";
    private List<String> errors = new ArrayList<>();

    public EmDataStatus id(Integer id) {
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

    public EmDataStatus aTotalActEnergy(Double aTotalActEnergy) {
        this.aTotalActEnergy = aTotalActEnergy;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_A_TOTAL_ACT_ENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getaTotalActEnergy() {
        return aTotalActEnergy;
    }

    @JsonProperty(JSON_PROPERTY_A_TOTAL_ACT_ENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setaTotalActEnergy(Double aTotalActEnergy) {
        this.aTotalActEnergy = aTotalActEnergy;
    }

    public EmDataStatus aTotalActRetEnergy(Double aTotalActRetEnergy) {
        this.aTotalActRetEnergy = aTotalActRetEnergy;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_A_TOTAL_ACT_RET_ENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getaTotalActRetEnergy() {
        return aTotalActRetEnergy;
    }

    @JsonProperty(JSON_PROPERTY_A_TOTAL_ACT_RET_ENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setaTotalActRetEnergy(Double aTotalActRetEnergy) {
        this.aTotalActRetEnergy = aTotalActRetEnergy;
    }

    public EmDataStatus bTotalActEnergy(Double bTotalActEnergy) {
        this.bTotalActEnergy = bTotalActEnergy;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_B_TOTAL_ACT_ENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getbTotalActEnergy() {
        return bTotalActEnergy;
    }

    @JsonProperty(JSON_PROPERTY_B_TOTAL_ACT_ENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setbTotalActEnergy(Double bTotalActEnergy) {
        this.bTotalActEnergy = bTotalActEnergy;
    }

    public EmDataStatus bTotalActRetEnergy(Double bTotalActRetEnergy) {
        this.bTotalActRetEnergy = bTotalActRetEnergy;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_B_TOTAL_ACT_RET_ENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getbTotalActRetEnergy() {
        return bTotalActRetEnergy;
    }

    @JsonProperty(JSON_PROPERTY_B_TOTAL_ACT_RET_ENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setbTotalActRetEnergy(Double bTotalActRetEnergy) {
        this.bTotalActRetEnergy = bTotalActRetEnergy;
    }

    public EmDataStatus cTotalActEnergy(Double cTotalActEnergy) {
        this.cTotalActEnergy = cTotalActEnergy;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_C_TOTAL_ACT_ENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getcTotalActEnergy() {
        return cTotalActEnergy;
    }

    @JsonProperty(JSON_PROPERTY_C_TOTAL_ACT_ENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setcTotalActEnergy(Double cTotalActEnergy) {
        this.cTotalActEnergy = cTotalActEnergy;
    }

    public EmDataStatus cTotalActRetEnergy(Double cTotalActRetEnergy) {
        this.cTotalActRetEnergy = cTotalActRetEnergy;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_C_TOTAL_ACT_RET_ENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getcTotalActRetEnergy() {
        return cTotalActRetEnergy;
    }

    @JsonProperty(JSON_PROPERTY_C_TOTAL_ACT_RET_ENERGY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setcTotalActRetEnergy(Double cTotalActRetEnergy) {
        this.cTotalActRetEnergy = cTotalActRetEnergy;
    }

    public EmDataStatus totalAct(Double totalAct) {
        this.totalAct = totalAct;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TOTAL_ACT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getTotalAct() {
        return totalAct;
    }

    @JsonProperty(JSON_PROPERTY_TOTAL_ACT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTotalAct(Double totalAct) {
        this.totalAct = totalAct;
    }

    public EmDataStatus totalActRet(Double totalActRet) {
        this.totalActRet = totalActRet;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TOTAL_ACT_RET)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getTotalActRet() {
        return totalActRet;
    }

    @JsonProperty(JSON_PROPERTY_TOTAL_ACT_RET)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTotalActRet(Double totalActRet) {
        this.totalActRet = totalActRet;
    }

    public EmDataStatus errors(List<String> errors) {
        this.errors = errors;
        return this;
    }

    public EmDataStatus addErrorsItem(String errorsItem) {
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
        EmDataStatus emDataStatus = (EmDataStatus) o;
        return Objects.equals(this.id, emDataStatus.id)
                && Objects.equals(this.aTotalActEnergy, emDataStatus.aTotalActEnergy)
                && Objects.equals(this.aTotalActRetEnergy, emDataStatus.aTotalActRetEnergy)
                && Objects.equals(this.bTotalActEnergy, emDataStatus.bTotalActEnergy)
                && Objects.equals(this.bTotalActRetEnergy, emDataStatus.bTotalActRetEnergy)
                && Objects.equals(this.cTotalActEnergy, emDataStatus.cTotalActEnergy)
                && Objects.equals(this.cTotalActRetEnergy, emDataStatus.cTotalActRetEnergy)
                && Objects.equals(this.totalAct, emDataStatus.totalAct)
                && Objects.equals(this.totalActRet, emDataStatus.totalActRet)
                && Objects.equals(this.errors, emDataStatus.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aTotalActEnergy, aTotalActRetEnergy, bTotalActEnergy, bTotalActRetEnergy,
                cTotalActEnergy, cTotalActRetEnergy, totalAct, totalActRet, errors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EmDataStatus {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    aTotalActEnergy: ").append(toIndentedString(aTotalActEnergy)).append("\n");
        sb.append("    aTotalActRetEnergy: ").append(toIndentedString(aTotalActRetEnergy)).append("\n");
        sb.append("    bTotalActEnergy: ").append(toIndentedString(bTotalActEnergy)).append("\n");
        sb.append("    bTotalActRetEnergy: ").append(toIndentedString(bTotalActRetEnergy)).append("\n");
        sb.append("    cTotalActEnergy: ").append(toIndentedString(cTotalActEnergy)).append("\n");
        sb.append("    cTotalActRetEnergy: ").append(toIndentedString(cTotalActRetEnergy)).append("\n");
        sb.append("    totalAct: ").append(toIndentedString(totalAct)).append("\n");
        sb.append("    totalActRet: ").append(toIndentedString(totalActRet)).append("\n");
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
