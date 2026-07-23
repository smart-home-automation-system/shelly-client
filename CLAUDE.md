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
representations of Shelly device responses (`ShellyUniStatusResponse`,
`ShellyPro4StatusResponse`, `Relay`, `WifiStatus`, `Cloud`, etc.). There is no Feign/HTTP
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

- **Never hand-edit files under `model/`** — they are overwritten on the next build.
  To change a model, edit `swagger/shelly.yaml` (schemas) or the templates in
  `swagger/templates/` (code shape), then rebuild.
- The generated sources **are committed** to git (they live under `src/main`), so a diff
  after `mvn package` should be reviewed as generated output, not authored code.
- `sonar.exclusions=**/model/**/*` keeps generated code out of SonarCloud analysis — do
  not flag coverage/quality on model classes in reviews.

## Dependencies

Leaf library: depends only on public third-party artifacts (Jackson annotations, Swagger
annotations, Jakarta annotation/validation, Lombok, JUnit). It consumes **no** GitHub
Packages libraries — that is why `sonar.yml` has no `maven-settings-xml-action` step and
needs no `github-prv`/`github-org-smart-home` server credentials.

## Build & release

- Build: `mvn -B package`.
- CI/CD (GitHub Actions): `CI.yml` (build + tests on push to `main`/`feature/**`),
  `sonar.yml` (SonarCloud), `package.yml` (publish to GitHub Packages on GitHub release —
  version is taken from the git tag via `versions:set`).
