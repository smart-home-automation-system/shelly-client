# shelly-client

Shared library (`cloud.cholewa:shelly-client`) in the **smart-home-automation-system**
workspace. Java 21, Maven, published to GitHub Packages
(`maven.pkg.github.com/smart-home-automation-system/shelly-client`, pom server id
`github`). Consumed by `blinds-service`, `weather-service` (2.x) and `boiler-service`, `heating-service`,
`shelly-cloud-service`, `water-service` (1.0.0); `appliance-service` reads the Gen1 meter (2.2.0, HAS-278);
`energy-service` the three-phase meter (2.3.0, HAS-327).

Org-wide conventions and working rules (PR flow, branch naming `feature/HAS-<n>`,
"user writes library code, Claude reviews", public-repo hygiene) live in the workspace
`organization.md` — this file only covers what is specific to this repo. When opened as
part of the workspace, those rules apply here too.

## What this library is

Three parts, three packages (the README has the API and the usage):

- `model` - **generated** POJOs of what a Shelly device answers and publishes: the Gen1
  `/status`, the status of the Gen2+ components (also the body of the MQTT `status` topics)
  and the frames of the RPC channel. Only this package is generated: the OpenAPI generator
  runs with `generateApis=false`.
- `http` - hand-written reactive clients on `WebClient`: `ShellyRpcClient` (Gen2+) and
  `ShellyGen1Client`, with `ShellyHttp` (package-private) holding what they share.
- `codec` - hand-written: `ShellyTopic`, `ShellyMqttDecoder`, `ShellyEvent`,
  `ShellyGen1Topic`.
- `autoconfigure` - the Spring Boot auto-configuration that registers the clients and the
  decoder (`META-INF/spring/...AutoConfiguration.imports`).

The library was models only up to 1.1.0; 2.0.0 (HAS-227) added the rest, because four
services were about to write the same clients (the organization's rule for moving code into
a library).

## Code generation — read before touching `model/`

The entire `src/main/java/cloud/cholewa/shelly/model/` package is **generated**, not
hand-written, from `swagger/shelly.yaml` by `openapi-generator-maven-plugin` (feign
library) during the build. On every build the pipeline is:

1. `maven-clean-plugin` deletes the `model` directory,
2. `openapi-generator` regenerates it from the spec using the custom Mustache templates
   in `swagger/templates/`,
3. `formatter-maven-plugin` formats it (config in `formatter.xml`),
4. `impsort-maven-plugin` sorts/removes imports.

Consequences:

- **Step 1 runs only with `mvn clean`.** A plain `mvn verify` regenerates over what is there,
  so the class of a schema that was renamed or removed stays in the folder - and in the jar
  (HAS-226: `LightStatus` survived its renaming until a `mvn clean verify`). After removing or
  renaming a schema build with `clean` and check `git status` for the deletion.
- **Never hand-edit files under `model/`** — they are overwritten on the next build.
  To change a model, edit `swagger/shelly.yaml` (schemas) or the templates in
  `swagger/templates/` (code shape), then rebuild.
- The generated sources **are committed** to git (they live under `src/main`), so a diff
  after `mvn package` should be reviewed as generated output, not authored code.
- `sonar.exclusions=**/model/**/*` keeps generated code out of SonarCloud analysis — do
  not flag coverage/quality on model classes in reviews.

## Models - the rules (HAS-226)

- **No enums for what a device reports.** `state`, `source`, `last_direction`, `stop_reason`,
  `illumination` are strings with the known values in the description: a generated enum
  throws on a value it does not know, and that fails the decoding of the whole message for
  one word a firmware added.
- **No defaults and nothing `required`.** A device leaves fields out (`target_pos` at rest,
  `brightness` in the mode color, the measurements on a relay without a meter); `null` has to
  stay apart from off and zero. The generator starts a list or a map empty all the same, and
  the Lombok builder does not (no `@Builder.Default`) - both pinned in
  `Gen2StatusDecodeTest`. The same holds for an open map (`status` and `config` of
  `ShellyComponent`, `params` of `NotifyStatus`): a map the device did not send reads as empty.
- **A schema without a type does not work here.** OpenAPI spells "any value" that way, and the
  generator then writes `JsonNullable`, a class of a library this one does not have - the
  build fails. `type: object` without properties is what gives `java.lang.Object`
  (`BthomeSensorStatus.value`).
- **An open object cannot have typed fields beside it.** `properties` together with
  `additionalProperties` makes the class extend `HashMap` with these templates, and Jackson
  then reads every field into the map. So `params` of `NotifyStatus` is a plain
  `Map<String, Object>` with `ts` as one of its keys.
- **Decimals are `number` / `double`.** A Gen2+ device reports power, voltage and current with
  fractions; `ShellyPro4StatusResponse` has them as integers and Jackson cuts the fraction
  off without a word. It is left as it is - `boiler-service` and `heating-service` read it -
  and `SwitchStatus` is the model for new code.
- **Names carry the generation where one word means two things**: `Gen1LightStatus` and
  `Gen2LightStatus` (owner, 2026-10-13 - the task had `LightStatus` and `LightStatus2`, and a
  reader who took the first for a Gen2 dimmer got a class that compiles and decodes to nulls).
  A roller is `RollerStatus` on Gen1 and `CoverStatus` on Gen2+, the words of Shelly. Public
  API since 1.1.0.
- **A model is added from a real payload when there is a device**: `GET /status` (Gen1) or
  `GET /rpc/Shelly.GetStatus` (Gen2+) of a device of the kind, read-only. The Bluetooth
  components were checked on the gateway of the weather station (Plug S Gen3, 2.1.0, HAS-236), the
  three-phase meter (`em`, `emdata`) on the Pro 3EM at the grid entry (2.1.0, HAS-327) - the energy
  counters are Wh, and that meter is also a BTHome gateway of the registry: a reader of the gateways
  gets its `em` statuses decoded since 2.3.0 (they were skipped before), and takes the components it
  reads by name.
- **The dynamic components are not in `Shelly.GetStatus`** (`bthomedevice`, `bthomesensor`,
  the virtual ones): `Shelly.GetComponents` with `dynamic_only` lists them, a page at a time
  (11 on that plug) - `dynamicComponents` reads every page.

## Clients, decoder, auto-configuration - the rules (HAS-227)

- **One chain for every call**: `ShellyHttp.read` and `ShellyHttp.command`. A new method of a
  client is one line on top of them - do not write a second chain. What they guarantee, and
  the tests pin: a 2xx whose body is not the model fails (the predicate names the one field
  the model cannot be without), a refusal carries its status and, from a Gen2+ device, its
  RPC error, every failure leaves as `ShellyCallException`, the body is always consumed or
  released, and a whole call is bounded by `ShellyTimeouts.call()`.
- **A command of the RPC returns `Mono<Void>`** and checks only that the answer is JSON. What
  a command answers was never read from a device - reading it would have moved something -
  so no model was written for it. If one is ever needed, read the answer on a device the
  owner agrees to switch.
- **`POST /rpc/<Method>` with a JSON body, always a body** (`{}` without parameters): a
  device refuses a `POST` without a `Content-Length`, and a list has no form in a query that
  every firmware reads.
- **`HttpClient.newConnection()`, no pool** - on purpose (a device closes an idle connection
  after seconds). It also means a test needs no care about pooled connections between
  `MockWebServer` instances.
- **The connector of the builder is replaced** (the timeouts live there), the builder is
  cloned. What a consumer needs on the HTTP client goes through `ShellyHttpClientCustomizer`,
  applied *before* the timeouts are set. Logbook is the case: the library does not depend on
  it, the README shows the bean.
- **The address is validated by a pattern and never echoed when it is not one**
  (`ShellyAddressException`): it comes from the registry, may be anything, and the message
  ends in a log. A valid address is in the messages - a LAN address in a log is fine.
- **The decoder throws for a body that is not its topic's** and answers nothing for a
  component without a model, without reading the body - most of the 130 messages a minute.
  A new component: an entry in `STATUSES`, a sample, a line in `statuses()` of the test (the
  test fails when the two lists differ).
- **`@ConditionalOnSingleCandidate`, not `@ConditionalOnBean`**, for the builder and the
  mapper: a service with two of them and no primary must still start.
- **The two Spring Boot auto-configurations are named by class name** (`afterName`), because
  their modules are not dependencies. The test runs with the real ones (`spring-boot-webclient`
  and `spring-boot-jackson`, test scope): if Boot moves a class, a bean goes missing there.
- **Jackson 3 API**: `tools.jackson`, unchecked `JacksonException`, `JsonNode.isString()` /
  `stringValue()`, `mapper.rebuild()` to derive a mapper. No `com.fasterxml.jackson.databind`.
- **No device is ever commanded from a test or from a session.** Reads are fine and were how
  the clients were checked (`GET /status`, `*.GetStatus`); a command is shown on
  `mockwebserver3` only.

## Tests

- `model`: sample payloads (`src/test/resources/samples`: `gen1`, `gen2`, `rpc`) decoded into
  the models with Jackson 3.
- `http`: the clients against `DeviceStub`, a `mockwebserver3` per test - the request as it
  is sent, the refusals, bodies that are not the model, a late answer, a broken body, a dead
  address. The timeouts of the stub are one second, the shortest allowed, so the two
  timeout cases take a second each.
- `codec`: the keys the broker of the house showed, on the same samples.
- `autoconfigure`: `ApplicationContextRunner` with `FilteredClassLoader` for an application
  without WebFlux, Reactor Netty or Jackson.

- The samples keep the fields a device sends and a model leaves out, on purpose: that a
  reader ignores them is what the models rely on.
- **Public repository**: a sample has the shape of a real payload and invented values - no
  address of the house, no device id, no SSID, no name of a room or of a person.
- A new model gets a sample and a decode test, and a line in `shouldLeaveWhatADeviceDoesNotSendUnset`.

## Dependencies

- **What a consumer gets** (compile scope) is what the models need and nothing more:
  `jackson-annotations`, `swagger-annotations`, `jakarta.annotation-api`,
  `jakarta.validation-api`. Keep it that way - `mvn dependency:tree -Dscope=compile`.
- **`provided`**: `spring-webflux`, `reactor-netty-http`, `netty-transport` (for
  `ChannelOption`; Reactor Netty brings Netty at runtime scope only), `tools.jackson.core:
  jackson-databind`, `spring-boot-autoconfigure`. Never `compile`: the consumer brings them,
  in the versions of its own Boot, and everything using them sits behind a condition of the
  auto-configuration. `jackson-databind` of Jackson 2 (`com.fasterxml`) stays out altogether.
- **Versions come from the BOM of Spring Boot** (`spring-boot.version`, imported in
  `dependencyManagement` - not a parent), so the library is compiled and tested against what
  a service runs. **It follows the organization's rule for Boot**: when a newer Boot exists,
  the next task on this library moves the property. An explicit version on something the BOM
  manages trips `dependencyConvergence`.
- It consumes **no** GitHub
Packages libraries — that is why `sonar.yml` has no `maven-settings-xml-action` step and
needs no `github-prv`/`github-org-smart-home` server credentials.

## Build & release

- Build: `mvn -B package`.
- CI/CD (GitHub Actions): `CI.yml` (build + tests on push to `main`/`feature/**`),
  `sonar.yml` (SonarCloud), `package.yml` (publish to GitHub Packages on GitHub release —
  version is taken from the git tag via `versions:set`).
