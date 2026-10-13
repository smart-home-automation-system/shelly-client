package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({BthomeSensorConfig.JSON_PROPERTY_ID, BthomeSensorConfig.JSON_PROPERTY_ADDR,
        BthomeSensorConfig.JSON_PROPERTY_OBJ_ID, BthomeSensorConfig.JSON_PROPERTY_IDX,
        BthomeSensorConfig.JSON_PROPERTY_NAME})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class BthomeSensorConfig {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_ADDR = "addr";
    private String addr;
    public static final String JSON_PROPERTY_OBJ_ID = "obj_id";
    private Integer objId;
    public static final String JSON_PROPERTY_IDX = "idx";
    private Integer idx;
    public static final String JSON_PROPERTY_NAME = "name";
    private String name;

    public BthomeSensorConfig id(Integer id) {
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

    public BthomeSensorConfig addr(String addr) {
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

    public BthomeSensorConfig objId(Integer objId) {
        this.objId = objId;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_OBJ_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getObjId() {
        return objId;
    }

    @JsonProperty(JSON_PROPERTY_OBJ_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    public BthomeSensorConfig idx(Integer idx) {
        this.idx = idx;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_IDX)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getIdx() {
        return idx;
    }

    @JsonProperty(JSON_PROPERTY_IDX)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public BthomeSensorConfig name(String name) {
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
        BthomeSensorConfig bthomeSensorConfig = (BthomeSensorConfig) o;
        return Objects.equals(this.id, bthomeSensorConfig.id) && Objects.equals(this.addr, bthomeSensorConfig.addr)
                && Objects.equals(this.objId, bthomeSensorConfig.objId)
                && Objects.equals(this.idx, bthomeSensorConfig.idx)
                && Objects.equals(this.name, bthomeSensorConfig.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addr, objId, idx, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BthomeSensorConfig {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    addr: ").append(toIndentedString(addr)).append("\n");
        sb.append("    objId: ").append(toIndentedString(objId)).append("\n");
        sb.append("    idx: ").append(toIndentedString(idx)).append("\n");
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
