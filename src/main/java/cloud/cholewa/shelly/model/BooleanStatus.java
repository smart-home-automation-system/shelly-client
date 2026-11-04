package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({BooleanStatus.JSON_PROPERTY_VALUE, BooleanStatus.JSON_PROPERTY_SOURCE,
        BooleanStatus.JSON_PROPERTY_LAST_UPDATE_TS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class BooleanStatus {

    public static final String JSON_PROPERTY_VALUE = "value";
    private Boolean value;
    public static final String JSON_PROPERTY_SOURCE = "source";
    private String source;
    public static final String JSON_PROPERTY_LAST_UPDATE_TS = "last_update_ts";
    private Long lastUpdateTs;

    public BooleanStatus value(Boolean value) {
        this.value = value;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_VALUE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getValue() {
        return value;
    }

    @JsonProperty(JSON_PROPERTY_VALUE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setValue(Boolean value) {
        this.value = value;
    }

    public BooleanStatus source(String source) {
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

    public BooleanStatus lastUpdateTs(Long lastUpdateTs) {
        this.lastUpdateTs = lastUpdateTs;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_LAST_UPDATE_TS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Long getLastUpdateTs() {
        return lastUpdateTs;
    }

    @JsonProperty(JSON_PROPERTY_LAST_UPDATE_TS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setLastUpdateTs(Long lastUpdateTs) {
        this.lastUpdateTs = lastUpdateTs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BooleanStatus booleanStatus = (BooleanStatus) o;
        return Objects.equals(this.value, booleanStatus.value) && Objects.equals(this.source, booleanStatus.source)
                && Objects.equals(this.lastUpdateTs, booleanStatus.lastUpdateTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, source, lastUpdateTs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BooleanStatus {\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    lastUpdateTs: ").append(toIndentedString(lastUpdateTs)).append("\n");
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
