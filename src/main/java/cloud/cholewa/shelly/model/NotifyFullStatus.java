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

@JsonPropertyOrder({NotifyFullStatus.JSON_PROPERTY_SRC, NotifyFullStatus.JSON_PROPERTY_DST,
        NotifyFullStatus.JSON_PROPERTY_METHOD, NotifyFullStatus.JSON_PROPERTY_PARAMS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class NotifyFullStatus {

    public static final String JSON_PROPERTY_SRC = "src";
    private String src;
    public static final String JSON_PROPERTY_DST = "dst";
    private String dst;
    public static final String JSON_PROPERTY_METHOD = "method";
    private String method;
    public static final String JSON_PROPERTY_PARAMS = "params";
    private Map<String, Object> params = new HashMap<>();

    public NotifyFullStatus src(String src) {
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

    public NotifyFullStatus dst(String dst) {
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

    public NotifyFullStatus method(String method) {
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

    public NotifyFullStatus params(Map<String, Object> params) {
        this.params = params;
        return this;
    }

    public NotifyFullStatus putParamsItem(String key, Object paramsItem) {
        if (this.params == null) {
            this.params = new HashMap<>();
        }
        this.params.put(key, paramsItem);
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_PARAMS)
    @JsonInclude(content = JsonInclude.Include.ALWAYS, value = JsonInclude.Include.USE_DEFAULTS)
    public Map<String, Object> getParams() {
        return params;
    }

    @JsonProperty(JSON_PROPERTY_PARAMS)
    @JsonInclude(content = JsonInclude.Include.ALWAYS, value = JsonInclude.Include.USE_DEFAULTS)
    public void setParams(Map<String, Object> params) {
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
        NotifyFullStatus notifyFullStatus = (NotifyFullStatus) o;
        return Objects.equals(this.src, notifyFullStatus.src) && Objects.equals(this.dst, notifyFullStatus.dst)
                && Objects.equals(this.method, notifyFullStatus.method)
                && Objects.equals(this.params, notifyFullStatus.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, dst, method, params);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class NotifyFullStatus {\n");
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
