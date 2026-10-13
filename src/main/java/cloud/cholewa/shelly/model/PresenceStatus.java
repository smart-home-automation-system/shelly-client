package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({PresenceStatus.JSON_PROPERTY_ID, PresenceStatus.JSON_PROPERTY_VALUE,
        PresenceStatus.JSON_PROPERTY_NUM_OBJECTS})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class PresenceStatus {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_VALUE = "value";
    private Boolean value;
    public static final String JSON_PROPERTY_NUM_OBJECTS = "num_objects";
    private Integer numObjects;

    public PresenceStatus id(Integer id) {
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

    public PresenceStatus value(Boolean value) {
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

    public PresenceStatus numObjects(Integer numObjects) {
        this.numObjects = numObjects;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_NUM_OBJECTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getNumObjects() {
        return numObjects;
    }

    @JsonProperty(JSON_PROPERTY_NUM_OBJECTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setNumObjects(Integer numObjects) {
        this.numObjects = numObjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PresenceStatus presenceStatus = (PresenceStatus) o;
        return Objects.equals(this.id, presenceStatus.id) && Objects.equals(this.value, presenceStatus.value)
                && Objects.equals(this.numObjects, presenceStatus.numObjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, numObjects);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PresenceStatus {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    numObjects: ").append(toIndentedString(numObjects)).append("\n");
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
