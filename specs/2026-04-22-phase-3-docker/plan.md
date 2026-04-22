# Phase 3: Docker — Implementation Plan

## Overview

Containerize the application with a three-stage Dockerfile: JDK 21 for uberjar compilation, GraalVM native-image for a statically-linked binary, and `scratch` for a ~5MB runtime image. The CI workflow is updated to verify the Docker build on every push and PR.

## Task Groups

### 1. Dockerfile

- Create a three-stage `Dockerfile`:
  - **Stage 1 — uberjar:** Eclipse Temurin JDK 21 (Alpine), install Clojure CLI, download deps, compile an uberjar
  - **Stage 2 — native-image:** GraalVM native-image-community 21 (muslib), compile uberjar to a static binary
  - **Stage 3 — runtime:** `scratch` (empty base), copy only the native binary, set entrypoint
- Support passing a name argument via `docker run`

### 2. Uberjar build support

- Add a `:build` alias to `deps.edn` with `io.github.clojure/tools.build`
- Create `build.clj` with an `uber` function to produce a standalone JAR
- The uberjar must include all dependencies and run with `java -jar`

### 3. Docker verification

- Verify `docker build -t hello-world .` succeeds
- Verify `docker run --rm hello-world` prints `Hello, World!`
- Verify `docker run --rm hello-world "Docker"` prints `Hello, Docker!`

### 4. Makefile targets

- Add `docker-build` and `docker-run` targets to the Makefile

### 5. CI update

- Add a Docker build step to `.github/workflows/ci.yml` after the test step
- The build should only verify the image builds, not push it anywhere

## Dependencies and Risks

- **Docker must be installed** on the dev machine for local verification. CI uses `ubuntu-latest` which has Docker pre-installed.
- **Uberjar approach:** `io.github.clojure/tools.build` is the official Clojure build library, consistent with the "standard JVM tooling" principle.
- **GraalVM native-image** requires `--initialize-at-build-time` for Clojure AOT compatibility and `--static --libc=musl` for a fully self-contained binary.
- **Image size:** The static native binary on `scratch` produces a ~5MB image.
