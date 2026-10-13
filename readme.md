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
time from the OpenAPI schema in [`swagger/`](swagger/) (`shelly.yaml`) and cover what a
Shelly device answers and publishes - see [Models](#models).

Current consumers: `boiler-service`, `heating-service`, `shelly-cloud-service`,
`water-service`.

## Installation

The artifact is published to GitHub Packages by the [`package.yml`](.github/workflows/package.yml)
workflow on release.

```xml
<dependency>
    <groupId>cloud.cholewa</groupId>
    <artifactId>shelly-client</artifactId>
    <version>1.1.0</version>
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

## Models

### Gen1 - `GET /status`

| Model | What it is |
|---|---|
| `Gen1DeviceStatus` | The envelope: `rollers`, `lights`, `relays`, and `mode`, `unixtime`, `wifi_sta`, `cloud`. Which list is filled says what the device is - a Shelly 2.5 does not send `mode`. |
| `RollerStatus` | One roller of a Shelly 2.5: `state` (`stop` / `open` / `close` - what the motor does), `current_pos` (0 closed to 100 open, meaningful only while `positioning` is true), `last_direction`, `stop_reason`, `calibrating`. |
| `Gen1LightStatus` | One light, for every Gen1 light (RGBW2, bulb, Duo): `ison`, `mode`, `red` / `green` / `blue` / `white`, `gain`, `brightness`, `temp`. **In the mode `color` the level is `gain`, in the mode `white` it is `brightness`**; a field the device does not have is missing. |
| `Relay` | One relay (since 0.0.x). |
| `MotionStatus` | A Shelly Motion: `sensor` (`motion`, `vibration`, `timestamp`, `active`), `lux` (`value`, `illumination`), `bat` (`value`, `voltage`). |
| `ShellyUniStatusResponse`, `ShellyProRelayResponse` | The Uni of the hot water and the answer of a relay command (since 0.0.x). |

### Gen2 and later - the status of one component

The body of the MQTT topic `<device id>/status/<component>:<id>`, and the answer of
`<Component>.GetStatus`.

| Model | Component | What it is |
|---|---|---|
| `SwitchStatus` | `switch` | A relay, a plug, one channel of a Pro 4PM: `output`, `source`, and the measurements (`apower`, `voltage`, `current`, `pf`, `freq`, `aenergy`, `temperature`) as decimals. |
| `CoverStatus` | `cover` | A roller: `state` (`open`, `closed`, `opening`, `closing`, `stopped`, `calibrating`), `current_pos` (0 closed to 100 open; missing while `pos_control` is false), `target_pos` (only on the way to a position), `last_direction`, `source`. |
| `Gen2LightStatus` | `light` | A dimmer: `output`, `brightness`, `source`, `transition` (only while a change lasts). |
| `RgbwStatus` | `rgb`, `rgbw` | `output`, `rgb` (three numbers), `brightness`, and `white` in the profile `rgbw` only. |
| `PresenceStatus` | `presencezone` | One zone of a Presence Gen4: `value` (somebody is there), `num_objects`. |
| `IlluminanceStatus` | `illuminance` | `illumination` (`dark` / `twilight` / `bright`) and, where the device measures it, `lux`. |
| `TemperatureStatus`, `HumidityStatus`, `DevicePowerStatus` | `temperature`, `humidity`, `devicepower` | What a battery thermometer (H&T) publishes when it wakes. |
| `BthomeDeviceStatus`, `BthomeSensorStatus`, `BthomeSensorConfig` | `bthomedevice`, `bthomesensor` | A Bluetooth device paired with a gateway, one value it reports, and which value that is. **Written from the documentation of Shelly, not checked on a device.** |
| `ShellyPro4StatusResponse` | `switch` | The older model of a switch (since 0.0.x): its measurements are integers, so a decimal is cut off. New code reads `SwitchStatus`. |

`source` says what caused the last change: `init` is a restart of the device, not somebody
switching it.

### Gen2 and later - the RPC channel

| Model | What it is |
|---|---|
| `NotifyStatus` | A change. `params` is a map: `ts`, and under the key of each component that changed (`cover:0`) only the fields that changed. |
| `NotifyFullStatus` | The whole status, sent after a connect. The same shape, every component whole. |
| `NotifyEvent` | Events (`params.events[]` with `component`, `id`, `event`, `ts`) - a press of a button. |
| `RpcError` | `code` and `message` of a refused call. |

A device publishes the three notifications on `<device id>/events/rpc` **only while the RPC
over MQTT is enabled on it** (`enable_rpc`). With it off - the way the devices of this house
are set up - only the `status` topics arrive, and the models to read are those of the
components above.

### What a reader has to know

- **A model is a part of the payload.** A device sends more fields than a model has, and a
  firmware adds some: read with a mapper that ignores unknown properties (the default of
  Jackson 3 and of Spring Boot).
- **Nothing has a default: a field that was not sent is `null`**, never "off" or zero. Check
  before unboxing.
- **A list or a map is the exception**: decoded, or made with `new`, it is empty when the
  device sent none - "not sent" and "sent empty" read the same, so ask `isEmpty()`. **Built
  with the builder it is `null`** unless given - a stub built for a test has to set every list
  the code reads.
- **States are text, not enums** (`state`, `source`, `last_direction`, `illumination`): the
  values known today are in the descriptions of `swagger/shelly.yaml`, and a new firmware may
  add one. Compare with the values you handle and have an answer for the rest.
- **A number without a type is a `Number`**: `BthomeSensorStatus.value` and the `ts` of a
  `params` map arrive as an `Integer` or a `Double`, by how the device wrote them.

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
