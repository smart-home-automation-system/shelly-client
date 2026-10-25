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

Everything a service of the smart-home-automation-system needs to talk to its Shelly Wi-Fi
devices:

- **models** of what a device answers and publishes (`cloud.cholewa.shelly.model`, generated
  from the OpenAPI schema in [`swagger/`](swagger/)) - see [Models](#models);
- **reactive HTTP clients** for the Gen2+ RPC and the Gen1 API (`cloud.cholewa.shelly.http`);
- **a decoder** of what the Gen2+ devices publish over MQTT (`cloud.cholewa.shelly.codec`).

See [Usage](#usage) for what a service has to bring itself.

Current consumers: `blinds-service`, `weather-service` (2.x); `boiler-service`, `heating-service`,
`shelly-cloud-service`, `water-service` (1.0.0); `appliance-service` (2.2.0 - the Gen1 meter).

## Installation

The artifact is published to GitHub Packages by the [`package.yml`](.github/workflows/package.yml)
workflow on release.

```xml
<dependency>
    <groupId>cloud.cholewa</groupId>
    <artifactId>shelly-client</artifactId>
    <version>2.0.0</version>
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
| `BthomeDeviceStatus`, `BthomeDeviceConfig`, `BthomeSensorStatus`, `BthomeSensorConfig` | `bthomedevice`, `bthomesensor` | A Bluetooth device paired with a gateway and its address, one value it reports, and which value that is. Checked on a Plug S Gen3 (2.1.0) with a WS90 paired (HAS-236): a sensor is published only when its value changes; `last_updated_ts` and `value` are `0` and `null` until the gateway has heard the object once. |
| `ShellyComponentsPage`, `ShellyComponent` | - (an RPC answer) | One page of `Shelly.GetComponents`: each component with its `key` and its `status` and `config` as raw maps. |
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

### What the library brings, and what a service brings

Three parts, in three packages:

| Part | Package | Needs, from the service |
|---|---|---|
| The models | `cloud.cholewa.shelly.model` | nothing - `jackson-annotations`, `swagger-annotations`, `jakarta.annotation-api` and `jakarta.validation-api` come with the library |
| The HTTP clients | `cloud.cholewa.shelly.http` | Spring WebFlux and Reactor Netty (`spring-boot-starter-webflux`), and a `WebClient.Builder` bean (`spring-boot-starter-webclient`) |
| The MQTT decoder | `cloud.cholewa.shelly.codec` | Jackson 3 (`tools.jackson.core:jackson-databind`) and a `JsonMapper` bean - both there in any Spring Boot 4 web service |

**Spring WebFlux, Reactor Netty, Jackson 3 databind and Spring Boot's auto-configure are
`provided` dependencies of this library**: it is compiled against them and does not hand them
on. A service gets them from its own starters, in the versions of its own Spring Boot; the
library is built and tested against the BOM of Spring Boot 4.1.2. A consumer without them
still gets the models, and nothing fails.

**The library registers its beans by itself** (a Spring Boot auto-configuration), each only
where the service has what it is built on and no bean of that type of its own:

- `ShellyRpcClient` and `ShellyGen1Client` - with WebFlux, Reactor Netty and one
  `WebClient.Builder` to take (the only one, or the primary);
- `ShellyMqttDecoder` - with Jackson 3 and one `JsonMapper` to take.

**Every WebFlux service of this organization has all of that**, so it gets all three beans by
taking the library, used or not. They call nobody by themselves. A service that wants the
models alone says `shelly.client.enabled: false`.

| Property | Default | |
|---|---|---|
| `shelly.client.enabled` | `true` | `false` registers nothing |
| `shelly.client.connect-timeout` | `3s` | how long a device may take to accept the connection |
| `shelly.client.response-timeout` | `5s` | the longest silence while its answer is awaited and read |

A bare number is seconds. Both timeouts lie between one second and a minute, and the
application does not start with one outside - `8000`, meant as milliseconds, is refused
instead of waiting two hours. A whole call never lasts longer than the two together.

### Commanding and reading a device

Inject a client and call it with **the address of the device as the device registry holds
it** - a host, or `host:port` - and the id of the component (the channel):

```java
@Service
@RequiredArgsConstructor
class BlindsActuator {

    private final ShellyRpcClient shelly;      // Gen2 and later
    private final ShellyGen1Client shellyGen1; // Gen1

    Mono<Void> moveTo(SmartDevice blinds, int position) {
        ShellyBinding device = blinds.getShelly();
        return device.getGeneration() >= 2
            ? shelly.coverGoToPosition(device.getAddress(), device.getChannel(), position)
            : shellyGen1.rollerToPosition(device.getAddress(), device.getChannel(), position).then();
    }
}
```

| `ShellyRpcClient` (Gen2+, `POST /rpc/<Method>`) | |
|---|---|
| `coverOpen`, `coverClose`, `coverStop`, `coverGoToPosition` | `Mono<Void>` |
| `switchSet`, `lightSet`, `lightToggle`, `rgbSet`, `rgbwSet` | `Mono<Void>`; a parameter left `null` is not sent, and the device leaves that part as it is |
| `coverStatus`, `switchStatus`, `lightStatus`, `rgbStatus`, `rgbwStatus` | the model of the component |
| `status` | `Shelly.GetStatus`: every component under its key (`switch:0`, `sys`), each a raw map |
| `bthomeSensorConfig`, `bthomeSensorStatus`, `bthomeDeviceConfig`, `webhookListSupported` | the model, and a raw map |
| `dynamicComponents` | `Shelly.GetComponents` with `dynamic_only`: every dynamic component (`bthomedevice`, `bthomesensor`, the virtual ones - `Shelly.GetStatus` leaves them out), status and config asked for by name, all pages read one after the other, at most 256 components; a failed page, a page that starts elsewhere than asked, or a change of `cfg_rev` or of the total between two pages fails the whole list |

| `ShellyGen1Client` (Gen1, `GET` with the command in the query) | |
|---|---|
| `roller` (`OPEN` / `CLOSE` / `STOP`), `rollerToPosition`, `rollerStatus` | `RollerStatus` |
| `light`, `lightToggle`, `lightStatus` (`/light`: a bulb, a Duo) | `Gen1LightStatus` |
| `color`, `colorStatus` (`/color`: an RGBW2 in the mode color) | `Gen1LightStatus` |
| `relay`, `relayStatus` | `Relay` |
| `meter` (`/meter/0`: the power meter of a plug) | `Gen1Meter` - an answer with `is_valid: false` is one, judged by the caller |
| `status`, `motionStatus` (`/status`) | `Gen1DeviceStatus`, `MotionStatus` |

What a caller has to know:

- **A call ends with the answer or with a `ShellyCallException`** - never with an empty
  `Mono` for a read, and never with another exception. Its message names the call and the
  address and is written for a log, not for a response: map it to an error of your own.
  - `ShellyRpcException`: a Gen2+ device refused and said why - `code()` and `rpcMessage()`
    are its own (`-105` for a component it does not have, `404` for a method its profile
    does not know).
  - `ShellyAddressException`: what was given is not an address, and nothing was called. A
    mistake of the registry, not a device that is down - a monitor that counts failed calls
    should not count it. The message does not repeat what was given.
  - `httpStatus()` is the status of a refusal; `null` when the device was not reached, did
    not answer in time, or answered a 2xx that is not the answer.
- **An answer that is not the model is a failed call.** No body, or JSON of another shape -
  `{}` decodes into a model of nulls, which would read as a device that is off.
- **A Gen2+ command answers nothing.** It is done when the device said 2xx in JSON. Read the
  status, or wait for it on MQTT, to learn what happened. **A Gen1 command answers the status
  right after it** - for a roller the start of the movement, not its end.
- **Nothing is retried, and nothing happens before somebody subscribes.** A command sent
  twice is the caller's decision.
- **A connection per call.** A device closes an idle connection after seconds; a pool would
  mostly hold dead ones.
- **`rgbSet` is for the profile `rgb`, `rgbwSet` for the profile `rgbw`** of the same device: a
  device in one profile does not know the methods of the other (404).
- **A cover goes to a position only when it was calibrated**: `pos_control` (Gen2+) or
  `positioning` (Gen1) of its status.
- `Webhook.ListSupported` is refused by some devices themselves (a Dimmer Gen3: `-113`, the
  answer is bigger than the device can send).

### The device calls in the request log

The clients put their own connector on the builder they are given - the timeouts live
there - so what a service set on its own connector, the Logbook handler first of all, does
not reach them. A bean of `ShellyHttpClientCustomizer` does:

```java
@Bean
ShellyHttpClientCustomizer shellyCallsInTheLog(Logbook logbook) {
    return http -> http.doOnConnected(connection -> connection.addHandlerLast(new LogbookClientHandler(logbook)));
}
```

What the builder itself carries - the codecs, the observation that carries the trace - comes
along without it: build the service's `WebClient` beans from the injected `WebClient.Builder`,
as everywhere.

### Reading what the devices publish

A Gen2+ device publishes on `<device id>/status/<component>:<id>`; on the broker of the
house the topic is the routing key `<device id>.status.<component>:<id>`. `ShellyTopic` reads
the key alone, `ShellyMqttDecoder` the body:

```java
@RabbitListener(queues = "${device.queue}")
Mono<Void> onDeviceMessage(Message message) {
    String key = message.getMessageProperties().getReceivedRoutingKey();

    return Mono.justOrEmpty(ShellyTopic.parse(key))
        .filterWhen(topic -> registry.knows(topic.deviceId()))   // most messages are not ours
        .flatMap(topic -> Mono.justOrEmpty(decoder.decode(topic, message.getBody())))
        .flatMap(this::handle)
        .onErrorResume(ShellyDecodeException.class, notAStatus -> {
            log.warn("Skipped: {}", notAStatus.getMessage());
            return Mono.empty();
        })
        .contextCapture();
}

private Mono<Void> handle(ShellyEvent event) {
    return switch (event) {
        case ShellyEvent.Status status when status.status() instanceof CoverStatus cover ->
            blinds.moved(status.deviceId(), status.index(), cover);
        case ShellyEvent.Online online -> devices.seen(online.deviceId(), online.online());
        default -> Mono.empty();
    };
}
```

- **Ask the registry before decoding.** Every queue gets every message of every device;
  `ShellyTopic.parse` costs nothing and gives the device id.
- **`decode` answers nothing for what has no model** - `sys`, `wifi`, `cloud`, the energy
  meters, any other topic - and does not read the body then.
  `ShellyMqttDecoder.decodedComponents()` lists what is decoded: `switch`, `cover`, `light`,
  `rgb`, `rgbw`, `presencezone`, `illuminance`, `temperature`, `humidity`, `devicepower`,
  `bthomedevice`, `bthomesensor`.
- **A `Status` is the whole component, not a change.** A device repeats it about once a
  minute, changed or not; compare with what you hold.
- **`ShellyDecodeException` is thrown** for a body that is not what its topic carries - not
  JSON, JSON without the id of the component, the status of another channel than the topic
  names. Catch it inside the chain, as above: a listener that lets an error out has the
  message handed back and delivered again at once.
- **`Online(false)` is the last will of a device**, published by the broker when a connection
  ends without a goodbye - for a battery sensor that is "asleep".
- **Unknown fields are ignored whatever the mapper of the service says.**
- The notifications (`ShellyEvent.Notification` with `NotifyStatus`, `NotifyFullStatus`,
  `NotifyEvent`) are decoded from `<id>/events/rpc`, which only a device with the RPC over
  MQTT enabled publishes - no device of this house.
- `ShellyGen1Topic.parse` takes a Gen1 topic apart (`shellies/<id>/roller/0/pos`). No Gen1
  device of the house is on MQTT; the body of such a topic is left to the caller.

### Coming from 1.x

2.0.0 removes and changes nothing of 1.1.0. It is a major because the library stopped being
models only: a WebFlux service that takes it gets the three beans above. A service that
keeps its own Shelly client keeps working - no bean name clashes - and either ignores them
or switches them off.

### The models

Plain Jackson-annotated classes with Lombok builders and fluent setters:

```java
Relay relay = Relay.builder()
    .ison(true)
    .hasTimer(false)
    .source("http")
    .build();

boolean on = Boolean.TRUE.equals(relay.getIson());
```

The whole `model` package is **generated**, not hand-written. To add or change a model,
edit `swagger/shelly.yaml` (or the templates in `swagger/templates/`); `mvn clean verify`
regenerates the sources in `src/main/java/cloud/cholewa/shelly/model`.
