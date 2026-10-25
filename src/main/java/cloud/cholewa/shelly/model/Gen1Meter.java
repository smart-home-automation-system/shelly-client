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

@JsonPropertyOrder({Gen1Meter.JSON_PROPERTY_POWER, Gen1Meter.JSON_PROPERTY_OVERPOWER, Gen1Meter.JSON_PROPERTY_IS_VALID,
        Gen1Meter.JSON_PROPERTY_TIMESTAMP, Gen1Meter.JSON_PROPERTY_COUNTERS, Gen1Meter.JSON_PROPERTY_TOTAL})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class Gen1Meter {

    public static final String JSON_PROPERTY_POWER = "power";
    private Double power;
    public static final String JSON_PROPERTY_OVERPOWER = "overpower";
    private Double overpower;
    public static final String JSON_PROPERTY_IS_VALID = "is_valid";
    private Boolean isValid;
    public static final String JSON_PROPERTY_TIMESTAMP = "timestamp";
    private Long timestamp;
    public static final String JSON_PROPERTY_COUNTERS = "counters";
    private List<Double> counters = new ArrayList<>();
    public static final String JSON_PROPERTY_TOTAL = "total";
    private Long total;

    public Gen1Meter power(Double power) {
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

    public Gen1Meter overpower(Double overpower) {
        this.overpower = overpower;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_OVERPOWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getOverpower() {
        return overpower;
    }

    @JsonProperty(JSON_PROPERTY_OVERPOWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setOverpower(Double overpower) {
        this.overpower = overpower;
    }

    public Gen1Meter isValid(Boolean isValid) {
        this.isValid = isValid;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_IS_VALID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getIsValid() {
        return isValid;
    }

    @JsonProperty(JSON_PROPERTY_IS_VALID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Gen1Meter timestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TIMESTAMP)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty(JSON_PROPERTY_TIMESTAMP)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Gen1Meter counters(List<Double> counters) {
        this.counters = counters;
        return this;
    }

    public Gen1Meter addCountersItem(Double countersItem) {
        if (this.counters == null) {
            this.counters = new ArrayList<>();
        }
        this.counters.add(countersItem);
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_COUNTERS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<Double> getCounters() {
        return counters;
    }

    @JsonProperty(JSON_PROPERTY_COUNTERS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setCounters(List<Double> counters) {
        this.counters = counters;
    }

    public Gen1Meter total(Long total) {
        this.total = total;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TOTAL)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Long getTotal() {
        return total;
    }

    @JsonProperty(JSON_PROPERTY_TOTAL)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Gen1Meter gen1Meter = (Gen1Meter) o;
        return Objects.equals(this.power, gen1Meter.power) && Objects.equals(this.overpower, gen1Meter.overpower)
                && Objects.equals(this.isValid, gen1Meter.isValid)
                && Objects.equals(this.timestamp, gen1Meter.timestamp)
                && Objects.equals(this.counters, gen1Meter.counters) && Objects.equals(this.total, gen1Meter.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, overpower, isValid, timestamp, counters, total);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Gen1Meter {\n");
        sb.append("    power: ").append(toIndentedString(power)).append("\n");
        sb.append("    overpower: ").append(toIndentedString(overpower)).append("\n");
        sb.append("    isValid: ").append(toIndentedString(isValid)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
        sb.append("    counters: ").append(toIndentedString(counters)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
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
