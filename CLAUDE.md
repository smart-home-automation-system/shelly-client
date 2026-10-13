# shelly-client

Shared library (`cloud.cholewa:shelly-client`) in the **smart-home-automation-system**
workspace. Java 21, Maven, published to GitHub Packages
(`maven.pkg.github.com/smart-home-automation-system/shelly-client`, pom server id
`github`). Consumed by `boiler-service`, `heating-service`, `shelly-cloud-service`,
`water-service`.

Org-wide conventions and working rules (PR flow, branch naming `feature/HAS-<n>`,
"user writes library code, Claude reviews", public-repo hygiene) live in the workspace
`organization.md` — this file only covers what is specific to this repo. When opened as
part of the workspace, those rules apply here too.

## What this library actually is

Despite the name, it currently ships **only generated model POJOs** — the Java
representations of what a Shelly device answers and publishes: the Gen1 `/status`, the status
of the Gen2+ components (also the body of the MQTT `status` topics) and the frames of the RPC
channel. The list is in the README. There is no Feign/HTTP
client class today: the OpenAPI generator is configured with `generateApis=false` and
`generateSupportingFiles=false`, so only the `model` package is produced.

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
  `Gen2StatusDecodeTest`.
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
  components had none (no gateway of the house has one paired) and are marked as unverified
  in the README - check them against the first device that reports.

## Tests

`src/test` decodes sample payloads (`src/test/resources/samples`: `gen1`, `gen2`, `rpc`) into
the models, with Jackson 3 - `tools.jackson.core:jackson-databind`, **test scope only**. The
jar still ships `jackson-annotations` and no databind: the consumers run Jackson 3, and both
major versions read the `com.fasterxml.jackson.annotation` annotations. Do not move it to the
compile scope.

- The samples keep the fields a device sends and a model leaves out, on purpose: that a
  reader ignores them is what the models rely on.
- **Public repository**: a sample has the shape of a real payload and invented values - no
  address of the house, no device id, no SSID, no name of a room or of a person.
- A new model gets a sample and a decode test, and a line in `shouldLeaveWhatADeviceDoesNotSendUnset`.

## Dependencies

Leaf library: depends only on public third-party artifacts (Jackson annotations, Swagger
annotations, Jakarta annotation/validation, Lombok, JUnit, and Jackson 3 databind for the
tests). It consumes **no** GitHub
Packages libraries — that is why `sonar.yml` has no `maven-settings-xml-action` step and
needs no `github-prv`/`github-org-smart-home` server credentials.

## Build & release

- Build: `mvn -B package`.
- CI/CD (GitHub Actions): `CI.yml` (build + tests on push to `main`/`feature/**`),
  `sonar.yml` (SonarCloud), `package.yml` (publish to GitHub Packages on GitHub release —
  version is taken from the git tag via `versions:set`).
