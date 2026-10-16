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

@JsonPropertyOrder({ShellyComponent.JSON_PROPERTY_KEY, ShellyComponent.JSON_PROPERTY_STATUS,
        ShellyComponent.JSON_PROPERTY_CONFIG})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class ShellyComponent {

    public static final String JSON_PROPERTY_KEY = "key";
    private String key;
    public static final String JSON_PROPERTY_STATUS = "status";
    private Map<String, Object> status = new HashMap<>();
    public static final String JSON_PROPERTY_CONFIG = "config";
    private Map<String, Object> config = new HashMap<>();

    public ShellyComponent key(String key) {
        this.key = key;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_KEY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getKey() {
        return key;
    }

    @JsonProperty(JSON_PROPERTY_KEY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setKey(String key) {
        this.key = key;
    }

    public ShellyComponent status(Map<String, Object> status) {
        this.status = status;
        return this;
    }

    public ShellyComponent putStatusItem(String key, Object statusItem) {
        if (this.status == null) {
            this.status = new HashMap<>();
        }
        this.status.put(key, statusItem);
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_STATUS)
    @JsonInclude(content = JsonInclude.Include.ALWAYS, value = JsonInclude.Include.USE_DEFAULTS)
    public Map<String, Object> getStatus() {
        return status;
    }

    @JsonProperty(JSON_PROPERTY_STATUS)
    @JsonInclude(content = JsonInclude.Include.ALWAYS, value = JsonInclude.Include.USE_DEFAULTS)
    public void setStatus(Map<String, Object> status) {
        this.status = status;
    }

    public ShellyComponent config(Map<String, Object> config) {
        this.config = config;
        return this;
    }

    public ShellyComponent putConfigItem(String key, Object configItem) {
        if (this.config == null) {
            this.config = new HashMap<>();
        }
        this.config.put(key, configItem);
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_CONFIG)
    @JsonInclude(content = JsonInclude.Include.ALWAYS, value = JsonInclude.Include.USE_DEFAULTS)
    public Map<String, Object> getConfig() {
        return config;
    }

    @JsonProperty(JSON_PROPERTY_CONFIG)
    @JsonInclude(content = JsonInclude.Include.ALWAYS, value = JsonInclude.Include.USE_DEFAULTS)
    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShellyComponent shellyComponent = (ShellyComponent) o;
        return Objects.equals(this.key, shellyComponent.key) && Objects.equals(this.status, shellyComponent.status)
                && Objects.equals(this.config, shellyComponent.config);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, status, config);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ShellyComponent {\n");
        sb.append("    key: ").append(toIndentedString(key)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    config: ").append(toIndentedString(config)).append("\n");
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
