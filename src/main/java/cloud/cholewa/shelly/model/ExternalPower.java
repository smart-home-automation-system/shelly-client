package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ExternalPower.JSON_PROPERTY_PRESENT})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class ExternalPower {

    public static final String JSON_PROPERTY_PRESENT = "present";
    private Boolean present;

    public ExternalPower present(Boolean present) {
        this.present = present;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_PRESENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getPresent() {
        return present;
    }

    @JsonProperty(JSON_PROPERTY_PRESENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPresent(Boolean present) {
        this.present = present;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExternalPower externalPower = (ExternalPower) o;
        return Objects.equals(this.present, externalPower.present);
    }

    @Override
    public int hashCode() {
        return Objects.hash(present);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExternalPower {\n");
        sb.append("    present: ").append(toIndentedString(present)).append("\n");
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
