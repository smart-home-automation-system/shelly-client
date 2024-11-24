
package cloud.cholewa.shelly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
import java.util.Objects;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonPropertyOrder({ ShellyPro4StatusResponse.JSON_PROPERTY_ID, ShellyPro4StatusResponse.JSON_PROPERTY_SOURCE,
        ShellyPro4StatusResponse.JSON_PROPERTY_OUTPUT, ShellyPro4StatusResponse.JSON_PROPERTY_APOWER,
        ShellyPro4StatusResponse.JSON_PROPERTY_VOLTAGE, ShellyPro4StatusResponse.JSON_PROPERTY_CURRENT })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
@SuperBuilder
public class ShellyPro4StatusResponse {
    public static final String JSON_PROPERTY_ID = "id";
    private Integer id;
    public static final String JSON_PROPERTY_SOURCE = "source";
    private String source;
    public static final String JSON_PROPERTY_OUTPUT = "output";
    private Boolean output;
    public static final String JSON_PROPERTY_APOWER = "apower";
    private Integer apower;
    public static final String JSON_PROPERTY_VOLTAGE = "voltage";
    private Integer voltage;
    public static final String JSON_PROPERTY_CURRENT = "current";
    private Integer current;

    public ShellyPro4StatusResponse id(Integer id) {

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

    public ShellyPro4StatusResponse source(String source) {

        this.source = source;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_SOURCE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getSource() {
        return source;
    }

    @JsonProperty(JSON_PROPERTY_SOURCE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setSource(String source) {
        this.source = source;
    }

    public ShellyPro4StatusResponse output(Boolean output) {

        this.output = output;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_OUTPUT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getOutput() {
        return output;
    }

    @JsonProperty(JSON_PROPERTY_OUTPUT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setOutput(Boolean output) {
        this.output = output;
    }

    public ShellyPro4StatusResponse apower(Integer apower) {

        this.apower = apower;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_APOWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getApower() {
        return apower;
    }

    @JsonProperty(JSON_PROPERTY_APOWER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setApower(Integer apower) {
        this.apower = apower;
    }

    public ShellyPro4StatusResponse voltage(Integer voltage) {

        this.voltage = voltage;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_VOLTAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getVoltage() {
        return voltage;
    }

    @JsonProperty(JSON_PROPERTY_VOLTAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setVoltage(Integer voltage) {
        this.voltage = voltage;
    }

    public ShellyPro4StatusResponse current(Integer current) {

        this.current = current;
        return this;
    }

    @Nullable

    @JsonProperty(JSON_PROPERTY_CURRENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getCurrent() {
        return current;
    }

    @JsonProperty(JSON_PROPERTY_CURRENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setCurrent(Integer current) {
        this.current = current;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShellyPro4StatusResponse shellyPro4StatusResponse = (ShellyPro4StatusResponse) o;
        return Objects.equals(this.id, shellyPro4StatusResponse.id)
                && Objects.equals(this.source, shellyPro4StatusResponse.source)
                && Objects.equals(this.output, shellyPro4StatusResponse.output)
                && Objects.equals(this.apower, shellyPro4StatusResponse.apower)
                && Objects.equals(this.voltage, shellyPro4StatusResponse.voltage)
                && Objects.equals(this.current, shellyPro4StatusResponse.current);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, output, apower, voltage, current);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ShellyPro4StatusResponse {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    output: ").append(toIndentedString(output)).append("\n");
        sb.append("    apower: ").append(toIndentedString(apower)).append("\n");
        sb.append("    voltage: ").append(toIndentedString(voltage)).append("\n");
        sb.append("    current: ").append(toIndentedString(current)).append("\n");
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
