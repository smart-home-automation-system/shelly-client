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

@JsonPropertyOrder({ShellyComponentsPage.JSON_PROPERTY_COMPONENTS, ShellyComponentsPage.JSON_PROPERTY_CFG_REV,
        ShellyComponentsPage.JSON_PROPERTY_OFFSET, ShellyComponentsPage.JSON_PROPERTY_TOTAL})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class ShellyComponentsPage {

    public static final String JSON_PROPERTY_COMPONENTS = "components";
    private List<@Valid ShellyComponent> components = new ArrayList<>();
    public static final String JSON_PROPERTY_CFG_REV = "cfg_rev";
    private Integer cfgRev;
    public static final String JSON_PROPERTY_OFFSET = "offset";
    private Integer offset;
    public static final String JSON_PROPERTY_TOTAL = "total";
    private Integer total;

    public ShellyComponentsPage components(List<@Valid ShellyComponent> components) {
        this.components = components;
        return this;
    }

    public ShellyComponentsPage addComponentsItem(ShellyComponent componentsItem) {
        if (this.components == null) {
            this.components = new ArrayList<>();
        }
        this.components.add(componentsItem);
        return this;
    }

    @Nullable
    @Valid
    @JsonProperty(JSON_PROPERTY_COMPONENTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<@Valid ShellyComponent> getComponents() {
        return components;
    }

    @JsonProperty(JSON_PROPERTY_COMPONENTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setComponents(List<@Valid ShellyComponent> components) {
        this.components = components;
    }

    public ShellyComponentsPage cfgRev(Integer cfgRev) {
        this.cfgRev = cfgRev;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_CFG_REV)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getCfgRev() {
        return cfgRev;
    }

    @JsonProperty(JSON_PROPERTY_CFG_REV)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setCfgRev(Integer cfgRev) {
        this.cfgRev = cfgRev;
    }

    public ShellyComponentsPage offset(Integer offset) {
        this.offset = offset;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_OFFSET)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty(JSON_PROPERTY_OFFSET)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public ShellyComponentsPage total(Integer total) {
        this.total = total;
        return this;
    }

    @Nullable
    @JsonProperty(JSON_PROPERTY_TOTAL)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getTotal() {
        return total;
    }

    @JsonProperty(JSON_PROPERTY_TOTAL)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShellyComponentsPage shellyComponentsPage = (ShellyComponentsPage) o;
        return Objects.equals(this.components, shellyComponentsPage.components)
                && Objects.equals(this.cfgRev, shellyComponentsPage.cfgRev)
                && Objects.equals(this.offset, shellyComponentsPage.offset)
                && Objects.equals(this.total, shellyComponentsPage.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components, cfgRev, offset, total);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ShellyComponentsPage {\n");
        sb.append("    components: ").append(toIndentedString(components)).append("\n");
        sb.append("    cfgRev: ").append(toIndentedString(cfgRev)).append("\n");
        sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
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
