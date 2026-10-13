package cloud.cholewa.shelly.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import mockwebserver3.MockResponse;
import mockwebserver3.MockWebServer;
import mockwebserver3.RecordedRequest;

/**
 * A device on a local port: answers what a test tells it to and remembers what it was asked.
 * A server per test, on a port of its own - the clients open a connection per call, so nothing
 * of one test is left for the next.
 */
final class DeviceStub implements AutoCloseable {

    static final ShellyTimeouts TIMEOUTS = new ShellyTimeouts(Duration.ofSeconds(1), Duration.ofSeconds(1));

    private final MockWebServer server = new MockWebServer();

    DeviceStub() {
        try {
            server.start();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    String address() {
        return "localhost:" + server.getPort();
    }

    void answersJson(String body) {
        answers(200, "application/json", body);
    }

    void answersSample(String sample) {
        answersJson(sample(sample));
    }

    void answers(int status, String contentType, String body) {
        MockResponse.Builder response = new MockResponse.Builder().code(status).body(body);
        if (contentType != null) {
            response.addHeader("Content-Type", contentType);
        }
        server.enqueue(response.build());
    }

    void answersLate(String body) {
        server.enqueue(new MockResponse.Builder().code(200).addHeader("Content-Type", "application/json").body(body)
            .headersDelay(TIMEOUTS.response().toMillis() * 4, TimeUnit.MILLISECONDS).build());
    }

    void enqueue(MockResponse response) {
        server.enqueue(response);
    }

    RecordedRequest asked() {
        try {
            RecordedRequest request = server.takeRequest(2, TimeUnit.SECONDS);
            if (request == null) {
                throw new AssertionError("The device was not called");
            }
            return request;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }

    int calls() {
        return server.getRequestCount();
    }

    static String sample(String name) {
        try (InputStream json = DeviceStub.class.getResourceAsStream("/samples/" + name)) {
            if (json == null) {
                throw new IllegalArgumentException("No sample: " + name);
            }
            return new String(json.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void close() {
        server.close();
    }
}
