package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({BthomeDeviceConfig.JSON_PROPERTY_ID, BthomeDeviceConfig.JSON_PROPERTY_ADDR,
        BthomeDeviceConfig.JSON_PROPERTY_NAME})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class BthomeDeviceConfig {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_ADDR = "addr";
    private String addr;
    public static final String JSON_PROPERTY_NAME = "name";
    private String name;

    public BthomeDeviceConfig id(Integer id) {
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

    public BthomeDeviceConfig addr(String addr) {
        this.addr = addr;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_ADDR)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getAddr() {
        return addr;
    }

    @JsonProperty(JSON_PROPERTY_ADDR)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setAddr(String addr) {
        this.addr = addr;
    }

    public BthomeDeviceConfig name(String name) {
        this.name = name;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_NAME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getName() {
        return name;
    }

    @JsonProperty(JSON_PROPERTY_NAME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BthomeDeviceConfig bthomeDeviceConfig = (BthomeDeviceConfig) o;
        return Objects.equals(this.id, bthomeDeviceConfig.id) && Objects.equals(this.addr, bthomeDeviceConfig.addr)
                && Objects.equals(this.name, bthomeDeviceConfig.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addr, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BthomeDeviceConfig {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    addr: ").append(toIndentedString(addr)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
