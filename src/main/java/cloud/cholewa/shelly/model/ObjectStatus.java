package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ObjectStatus.JSON_PROPERTY_VALUE, ObjectStatus.JSON_PROPERTY_SOURCE,
        ObjectStatus.JSON_PROPERTY_LAST_UPDATE_TS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class ObjectStatus {

    public static final String JSON_PROPERTY_VALUE = "value";
    private Map<String, Object> value = new HashMap<>();
    public static final String JSON_PROPERTY_SOURCE = "source";
    private String source;
    public static final String JSON_PROPERTY_LAST_UPDATE_TS = "last_update_ts";
    private Long lastUpdateTs;

    public ObjectStatus value(Map<String, Object> value) {
        this.value = value;
        return this;
    }

    public ObjectStatus putValueItem(String key, Object valueItem) {
        if (this.value == null) {
            this.value = new HashMap<>();
        }
        this.value.put(key, valueItem);
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_VALUE)
    @JsonInclude(content = JsonInclude.Include.ALWAYS, value = JsonInclude.Include.USE_DEFAULTS)
    public Map<String, Object> getValue() {
        return value;
    }

    @JsonProperty(JSON_PROPERTY_VALUE)
    @JsonInclude(content = JsonInclude.Include.ALWAYS, value = JsonInclude.Include.USE_DEFAULTS)
    public void setValue(Map<String, Object> value) {
        this.value = value;
    }

    public ObjectStatus source(String source) {
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

    public ObjectStatus lastUpdateTs(Long lastUpdateTs) {
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
        ObjectStatus objectStatus = (ObjectStatus) o;
        return Objects.equals(this.value, objectStatus.value) && Objects.equals(this.source, objectStatus.source)
                && Objects.equals(this.lastUpdateTs, objectStatus.lastUpdateTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, source, lastUpdateTs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ObjectStatus {\n");
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
