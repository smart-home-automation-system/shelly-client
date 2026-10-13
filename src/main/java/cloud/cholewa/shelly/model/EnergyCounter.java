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

@JsonPropertyOrder({EnergyCounter.JSON_PROPERTY_TOTAL, EnergyCounter.JSON_PROPERTY_BY_MINUTE,
        EnergyCounter.JSON_PROPERTY_MINUTE_TS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class EnergyCounter {

    public static final String JSON_PROPERTY_TOTAL = "total";
    private Double total;
    public static final String JSON_PROPERTY_BY_MINUTE = "by_minute";
    private List<Double> byMinute = new ArrayList<>();
    public static final String JSON_PROPERTY_MINUTE_TS = "minute_ts";
    private Long minuteTs;

    public EnergyCounter total(Double total) {
        this.total = total;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TOTAL)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getTotal() {
        return total;
    }

    @JsonProperty(JSON_PROPERTY_TOTAL)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTotal(Double total) {
        this.total = total;
    }

    public EnergyCounter byMinute(List<Double> byMinute) {
        this.byMinute = byMinute;
        return this;
    }

    public EnergyCounter addByMinuteItem(Double byMinuteItem) {
        if (this.byMinute == null) {
            this.byMinute = new ArrayList<>();
        }
        this.byMinute.add(byMinuteItem);
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_BY_MINUTE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<Double> getByMinute() {
        return byMinute;
    }

    @JsonProperty(JSON_PROPERTY_BY_MINUTE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setByMinute(List<Double> byMinute) {
        this.byMinute = byMinute;
    }

    public EnergyCounter minuteTs(Long minuteTs) {
        this.minuteTs = minuteTs;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_MINUTE_TS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Long getMinuteTs() {
        return minuteTs;
    }

    @JsonProperty(JSON_PROPERTY_MINUTE_TS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setMinuteTs(Long minuteTs) {
        this.minuteTs = minuteTs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EnergyCounter energyCounter = (EnergyCounter) o;
        return Objects.equals(this.total, energyCounter.total) && Objects.equals(this.byMinute, energyCounter.byMinute)
                && Objects.equals(this.minuteTs, energyCounter.minuteTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, byMinute, minuteTs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EnergyCounter {\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("    byMinute: ").append(toIndentedString(byMinute)).append("\n");
        sb.append("    minuteTs: ").append(toIndentedString(minuteTs)).append("\n");
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
