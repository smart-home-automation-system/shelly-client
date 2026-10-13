package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({IlluminanceStatus.JSON_PROPERTY_ID, IlluminanceStatus.JSON_PROPERTY_LUX,
        IlluminanceStatus.JSON_PROPERTY_ILLUMINATION})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class IlluminanceStatus {

    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_LUX = "lux";
    private Double lux;
    public static final String JSON_PROPERTY_ILLUMINATION = "illumination";
    private String illumination;

    public IlluminanceStatus id(Integer id) {
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

    public IlluminanceStatus lux(Double lux) {
        this.lux = lux;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_LUX)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Double getLux() {
        return lux;
    }

    @JsonProperty(JSON_PROPERTY_LUX)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setLux(Double lux) {
        this.lux = lux;
    }

    public IlluminanceStatus illumination(String illumination) {
        this.illumination = illumination;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_ILLUMINATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getIllumination() {
        return illumination;
    }

    @JsonProperty(JSON_PROPERTY_ILLUMINATION)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setIllumination(String illumination) {
        this.illumination = illumination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IlluminanceStatus illuminanceStatus = (IlluminanceStatus) o;
        return Objects.equals(this.id, illuminanceStatus.id) && Objects.equals(this.lux, illuminanceStatus.lux)
                && Objects.equals(this.illumination, illuminanceStatus.illumination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lux, illumination);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class IlluminanceStatus {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    lux: ").append(toIndentedString(lux)).append("\n");
        sb.append("    illumination: ").append(toIndentedString(illumination)).append("\n");
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
