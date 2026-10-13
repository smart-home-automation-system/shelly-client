package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({NotifyEvent.JSON_PROPERTY_SRC, NotifyEvent.JSON_PROPERTY_DST, NotifyEvent.JSON_PROPERTY_METHOD,
        NotifyEvent.JSON_PROPERTY_PARAMS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class NotifyEvent {

    public static final String JSON_PROPERTY_SRC = "src";
    private String src;
    public static final String JSON_PROPERTY_DST = "dst";
    private String dst;
    public static final String JSON_PROPERTY_METHOD = "method";
    private String method;
    public static final String JSON_PROPERTY_PARAMS = "params";
    private NotifyEventParams params;

    public NotifyEvent src(String src) {
        this.src = src;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_SRC)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getSrc() {
        return src;
    }

    @JsonProperty(JSON_PROPERTY_SRC)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setSrc(String src) {
        this.src = src;
    }

    public NotifyEvent dst(String dst) {
        this.dst = dst;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_DST)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getDst() {
        return dst;
    }

    @JsonProperty(JSON_PROPERTY_DST)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDst(String dst) {
        this.dst = dst;
    }

    public NotifyEvent method(String method) {
        this.method = method;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_METHOD)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getMethod() {
        return method;
    }

    @JsonProperty(JSON_PROPERTY_METHOD)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setMethod(String method) {
        this.method = method;
    }

    public NotifyEvent params(NotifyEventParams params) {
        this.params = params;
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_PARAMS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public NotifyEventParams getParams() {
        return params;
    }

    @JsonProperty(JSON_PROPERTY_PARAMS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setParams(NotifyEventParams params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotifyEvent notifyEvent = (NotifyEvent) o;
        return Objects.equals(this.src, notifyEvent.src) && Objects.equals(this.dst, notifyEvent.dst)
                && Objects.equals(this.method, notifyEvent.method) && Objects.equals(this.params, notifyEvent.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, dst, method, params);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NotifyEvent {\n");
        sb.append("    src: ").append(toIndentedString(src)).append("\n");
        sb.append("    dst: ").append(toIndentedString(dst)).append("\n");
        sb.append("    method: ").append(toIndentedString(method)).append("\n");
        sb.append("    params: ").append(toIndentedString(params)).append("\n");
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
