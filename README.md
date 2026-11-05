# Lab 4 WebSocket

Individual starter for Web Engineering 2026–27. Stack matches the group project: **Java 25 LTS**, **Kotlin 2.4.0**, **Spring Boot 4.1.0**, **Gradle 9.6.0**.

The assignment, AI rules, and deadline are in [`docs/GUIDE.md`](docs/GUIDE.md). You may add an `AGENTS.md` or a project skill so an agent follows that guide (see AI use there). Fill [`REPORT.md`](REPORT.md) before you submit. Delivery is the Moodle zip only (`docs/GUIDE.md`).

## Run

Java 25 is required (`./gradlew` uses the wrapper). GitHub Codespaces is optional (`docs/GUIDE.md`). Clone this course repository; you do not fork it to submit.

```bash
git clone https://github.com/UNIZAR-30246-WebEngineering/lab4-websocket.git
cd lab4-websocket
./gradlew check
./gradlew bootRun
```

`./gradlew check` passes on this starter: `onChat` is `@Disabled`. The assignment is to remove that annotation and finish the test. The endpoint is `ws://localhost:8080/eliza`.

```bash
./gradlew test
./gradlew ktlintCheck
```

## Layout

```
src/main/kotlin/websockets/Eliza.kt
src/main/kotlin/websockets/ElizaServer.kt
src/test/kotlin/websockets/ElizaServerTest.kt
docs/GUIDE.md
```

The endpoint is already implemented. You complete `onChat` and `ComplexClient.onMessage`.
