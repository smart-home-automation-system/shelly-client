package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({BthomeSensorStatus.JSON_PROPERTY_ID, BthomeSensorStatus.JSON_PROPERTY_VALUE,
        BthomeSensorStatus.JSON_PROPERTY_LAST_UPDATED_TS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class BthomeSensorStatus {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_VALUE = "value";
    private Object value;
    public static final String JSON_PROPERTY_LAST_UPDATED_TS = "last_updated_ts";
    private Long lastUpdatedTs;

    public BthomeSensorStatus id(Integer id) {
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

    public BthomeSensorStatus value(Object value) {
        this.value = value;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_VALUE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Object getValue() {
        return value;
    }

    @JsonProperty(JSON_PROPERTY_VALUE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setValue(Object value) {
        this.value = value;
    }

    public BthomeSensorStatus lastUpdatedTs(Long lastUpdatedTs) {
        this.lastUpdatedTs = lastUpdatedTs;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_LAST_UPDATED_TS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Long getLastUpdatedTs() {
        return lastUpdatedTs;
    }

    @JsonProperty(JSON_PROPERTY_LAST_UPDATED_TS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setLastUpdatedTs(Long lastUpdatedTs) {
        this.lastUpdatedTs = lastUpdatedTs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BthomeSensorStatus bthomeSensorStatus = (BthomeSensorStatus) o;
        return Objects.equals(this.id, bthomeSensorStatus.id) && Objects.equals(this.value, bthomeSensorStatus.value)
                && Objects.equals(this.lastUpdatedTs, bthomeSensorStatus.lastUpdatedTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, lastUpdatedTs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BthomeSensorStatus {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    lastUpdatedTs: ").append(toIndentedString(lastUpdatedTs)).append("\n");
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
