[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.20-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/Gradle-8.13-blue?logo=gradle)](https://gradle.org)
[![Maven Central Version](https://img.shields.io/maven-central/v/com.dayanruben.maplibre-compose/maplibre-compose)](https://central.sonatype.com/namespace/com.dayanruben.maplibre-compose)
[![License](https://img.shields.io/github/license/dayanruben/maplibre-compose)](https://github.com/dayanruben/maplibre-compose/blob/main/LICENSE)
[![Documentation](https://img.shields.io/badge/Documentation-blue?logo=MaterialForMkDocs&logoColor=white)](https://sargunv.github.io/maplibre-compose/)
[![API Reference](https://img.shields.io/badge/API_Reference-blue?logo=Kotlin&logoColor=white)](https://sargunv.github.io/maplibre-compose/api/)
[![Slack](https://img.shields.io/badge/Slack-4A154B?logo=slack&logoColor=white)](https://osmus.slack.com/archives/maplibre-compose)

# MapLibre for Compose Multiplatform

> [!WARNING]
> Special thanks to the original [author](https://github.com/sargunv) for laying the foundation of this [repository](https://github.com/sargunv/maplibre-compose), your work continues to inspire.
> This library will continue to be actively maintained with updates and modifications specifically required by the projects that depend on it. Efforts will be made to keep it as up-to-date as possible with the original implementation.

---

## Introduction

MapLibre Compose is a [Compose Multiplatform][compose] wrapper around the
[MapLibre][maplibre] SDKs for rendering interactive maps. You can use it to add
maps to your Compose UIs across Android, iOS, Desktop, and Web.

<p float="left">
  <img src="https://github.com/user-attachments/assets/997cf8a4-2841-40c8-b5a1-ef98193b21b2" width="200" alt="iOS Screenshot"/>
  <img src="https://github.com/user-attachments/assets/e450f689-e254-48b7-bd91-3d3042faa290" width="200" alt="Android Screenshot"/>
</p>

## Usage

- [Getting Started](https://sargunv.github.io/maplibre-compose/getting-started/)
- [API Reference](https://sargunv.github.io/maplibre-compose/api/)
- [Demo App](./demo-app)

## Status

A large subset of MapLibre's features are already supported, but the full
breadth of the MapLibre SDKs is not yet covered. What is already supported may
have bugs. API stability is not yet guaranteed; we're still exploring how best
to express an interactive map API in Compose.

See [the status table][status] for a breakdown of supported features on each
platform. Android and iOS have the most complete support, while Desktop and Web
are still catching up.

[compose]: https://www.jetbrains.com/compose-multiplatform/
[maplibre]: https://maplibre.org/
[status]: https://sargunv.github.io/maplibre-compose/#status
