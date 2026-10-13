package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({NotifyEventParams.JSON_PROPERTY_TS, NotifyEventParams.JSON_PROPERTY_EVENTS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class NotifyEventParams {

    public static final String JSON_PROPERTY_TS = "ts";
    private Double ts;
    public static final String JSON_PROPERTY_EVENTS = "events";
    private List<@Valid RpcEvent> events = new ArrayList<>();

    public NotifyEventParams ts(Double ts) {
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

    public NotifyEventParams events(List<@Valid RpcEvent> events) {
        this.events = events;
        return this;
    }

    public NotifyEventParams addEventsItem(RpcEvent eventsItem) {
        if (this.events == null) {
            this.events = new ArrayList<>();
        }
        this.events.add(eventsItem);
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_EVENTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<@Valid RpcEvent> getEvents() {
        return events;
    }

    @JsonProperty(JSON_PROPERTY_EVENTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setEvents(List<@Valid RpcEvent> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotifyEventParams notifyEventParams = (NotifyEventParams) o;
        return Objects.equals(this.ts, notifyEventParams.ts) && Objects.equals(this.events, notifyEventParams.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ts, events);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NotifyEventParams {\n");
        sb.append("    ts: ").append(toIndentedString(ts)).append("\n");
        sb.append("    events: ").append(toIndentedString(events)).append("\n");
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
