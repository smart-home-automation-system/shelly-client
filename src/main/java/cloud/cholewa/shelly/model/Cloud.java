
package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ Cloud.JSON_PROPERTY_ENABLED, Cloud.JSON_PROPERTY_CONNECTED })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class Cloud {
    public static final String JSON_PROPERTY_ENABLED = "enabled";
    private Boolean enabled;
    public static final String JSON_PROPERTY_CONNECTED = "connected";
    private Boolean connected;

    public Cloud enabled(Boolean enabled) {

        this.enabled = enabled;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_ENABLED)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getEnabled() {
        return enabled;
    }

    @JsonProperty(JSON_PROPERTY_ENABLED)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Cloud connected(Boolean connected) {

        this.connected = connected;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_CONNECTED)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getConnected() {
        return connected;
    }

    @JsonProperty(JSON_PROPERTY_CONNECTED)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cloud cloud = (Cloud) o;
        return Objects.equals(this.enabled, cloud.enabled) && Objects.equals(this.connected, cloud.connected);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enabled, connected);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Cloud {\n");
        sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
        sb.append("    connected: ").append(toIndentedString(connected)).append("\n");
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
