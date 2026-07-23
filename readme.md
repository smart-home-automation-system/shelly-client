# shelly-client

[![CI](https://github.com/smart-home-automation-system/shelly-client/actions/workflows/CI.yml/badge.svg)](https://github.com/smart-home-automation-system/shelly-client/actions/workflows/CI.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=smart-home-automation-system_shelly-client&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=smart-home-automation-system_shelly-client)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=smart-home-automation-system_shelly-client&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=smart-home-automation-system_shelly-client)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=smart-home-automation-system_shelly-client&metric=coverage)](https://sonarcloud.io/summary/new_code?id=smart-home-automation-system_shelly-client)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=smart-home-automation-system_shelly-client&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=smart-home-automation-system_shelly-client)

![GitHub top language](https://img.shields.io/github/languages/top/smart-home-automation-system/shelly-client?style=plastic)
![Java](https://img.shields.io/badge/java-21-yellow?style=plastic)

![GitHub Release Date - Published_At](https://img.shields.io/github/release-date/smart-home-automation-system/shelly-client?style=plastic)
![GitHub Release](https://img.shields.io/github/v/release/smart-home-automation-system/shelly-client?style=plastic)

![GitHub issues](https://img.shields.io/github/issues/smart-home-automation-system/shelly-client?style=plastic)
![GitHub contributors](https://img.shields.io/github/contributors/smart-home-automation-system/shelly-client?style=plastic)
![GitHub pull requests](https://img.shields.io/github/issues-pr-raw/smart-home-automation-system/shelly-client?style=plastic)

![GitHub last commit](https://img.shields.io/github/last-commit/smart-home-automation-system/shelly-client?style=plastic)
![GitHub commit activity](https://img.shields.io/github/commit-activity/m/smart-home-automation-system/shelly-client?style=plastic)

Shared domain models for Shelly Wi-Fi devices in the smart-home-automation-system.
Despite the name, the library currently ships **only generated model POJOs** — no HTTP
client class yet. The model classes (`cloud.cholewa.shelly.model`) are generated at build
time from the OpenAPI schema in [`swagger/`](swagger/) (`shelly.yaml`) and cover Shelly
device response payloads: relay state (`Relay`), Wi-Fi status (`WifiStatus`), cloud
connectivity (`Cloud`), external temperature values (`ExternalTemperatureValue`) and the
Shelly Pro/Uni status responses (`ShellyPro4StatusResponse`, `ShellyProRelayResponse`,
`ShellyUniStatusResponse`).

Current consumers: `boiler-service`, `heating-service`, `shelly-cloud-service`,
`water-service`.

## Installation

The artifact is published to GitHub Packages by the [`package.yml`](.github/workflows/package.yml)
workflow on release.

```xml
<dependency>
    <groupId>cloud.cholewa</groupId>
    <artifactId>shelly-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

Add the organization's GitHub Packages repository (requires a GitHub token with
`read:packages` in your Maven `settings.xml`):

```xml
<repositories>
    <repository>
        <id>github-org-smart-home</id>
        <url>https://maven.pkg.github.com/smart-home-automation-system/*</url>
    </repository>
</repositories>
```

## Usage

The models are plain Jackson-annotated classes with Lombok builders and fluent setters:

```java
Relay relay = Relay.builder()
    .ison(true)
    .hasTimer(false)
    .source("http")
    .build();

boolean on = Boolean.TRUE.equals(relay.getIson());
```

The whole `model` package is **generated**, not hand-written. To add or change a model,
edit `swagger/shelly.yaml` (or the templates in `swagger/templates/`); `mvn verify`
regenerates the sources in `src/main/java/cloud/cholewa/shelly/model`.
