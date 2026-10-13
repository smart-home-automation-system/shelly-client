package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({HumidityStatus.JSON_PROPERTY_ID, HumidityStatus.JSON_PROPERTY_RH})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class HumidityStatus {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_RH = "rh";
    private Double rh;

    public HumidityStatus id(Integer id) {
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

    public HumidityStatus rh(Double rh) {
        this.rh = rh;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_RH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getRh() {
        return rh;
    }

    @JsonProperty(JSON_PROPERTY_RH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setRh(Double rh) {
        this.rh = rh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HumidityStatus humidityStatus = (HumidityStatus) o;
        return Objects.equals(this.id, humidityStatus.id) && Objects.equals(this.rh, humidityStatus.rh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rh);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class HumidityStatus {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    rh: ").append(toIndentedString(rh)).append("\n");
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
