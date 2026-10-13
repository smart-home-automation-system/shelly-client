package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({RpcEvent.JSON_PROPERTY_COMPONENT, RpcEvent.JSON_PROPERTY_ID, RpcEvent.JSON_PROPERTY_EVENT,
        RpcEvent.JSON_PROPERTY_TS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class RpcEvent {

    public static final String JSON_PROPERTY_COMPONENT = "component";
    private String component;
    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_EVENT = "event";
    private String event;
    public static final String JSON_PROPERTY_TS = "ts";
    private Double ts;

    public RpcEvent component(String component) {
        this.component = component;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_COMPONENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getComponent() {
        return component;
    }

    @JsonProperty(JSON_PROPERTY_COMPONENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setComponent(String component) {
        this.component = component;
    }

    public RpcEvent id(Integer id) {
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

    public RpcEvent event(String event) {
        this.event = event;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_EVENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getEvent() {
        return event;
    }

    @JsonProperty(JSON_PROPERTY_EVENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setEvent(String event) {
        this.event = event;
    }

    public RpcEvent ts(Double ts) {
        this.ts = ts;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getTs() {
        return ts;
    }

    @JsonProperty(JSON_PROPERTY_TS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTs(Double ts) {
        this.ts = ts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RpcEvent rpcEvent = (RpcEvent) o;
        return Objects.equals(this.component, rpcEvent.component) && Objects.equals(this.id, rpcEvent.id)
                && Objects.equals(this.event, rpcEvent.event) && Objects.equals(this.ts, rpcEvent.ts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(component, id, event, ts);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RpcEvent {\n");
        sb.append("    component: ").append(toIndentedString(component)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    event: ").append(toIndentedString(event)).append("\n");
        sb.append("    ts: ").append(toIndentedString(ts)).append("\n");
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
