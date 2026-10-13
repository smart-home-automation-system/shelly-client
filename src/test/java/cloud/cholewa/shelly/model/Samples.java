package cloud.cholewa.shelly.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import tools.jackson.databind.json.JsonMapper;

/**
 * The sample payloads of {@code src/test/resources/samples}, decoded the way the consumers
 * decode them: by Jackson 3 with its defaults, which ignore a property the model does not
 * have. The samples keep the fields a device really sends and the models leave out - that the
 * models are a part of the payload is what these tests rely on, as every consumer does.
 */
final class Samples {

    private static final JsonMapper MAPPER = JsonMapper.builder().build();

    private Samples() {
    }

    static <T> T decode(String sample, Class<T> model) {
        try (InputStream json = Samples.class.getResourceAsStream("/samples/" + sample)) {
            if (json == null) {
                throw new IllegalArgumentException("No sample: " + sample);
            }
            return MAPPER.readValue(json, model);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static <T> T decodeText(String json, Class<T> model) {
        return MAPPER.readValue(json, model);
    }

    static String encode(Object model) {
        return MAPPER.writeValueAsString(model);
    }
}
