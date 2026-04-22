# Phase 3: Docker — Implementation Plan

## Overview

Containerize the application with a multi-stage Dockerfile. The build stage uses JDK 21 to compile an uberjar, and the runtime stage uses JRE 21 for a minimal image. The CI workflow is updated to verify the Docker build on every push and PR.

## Task Groups

### 1. Dockerfile

- Create a multi-stage `Dockerfile`:
  - **Build stage:** Eclipse Temurin JDK 21, install Clojure CLI, download deps, compile an uberjar
  - **Runtime stage:** Eclipse Temurin JRE 21, copy the uberjar, set entrypoint
- Support passing a name argument via `docker run`

### 2. Uberjar build support

- Add a `:uberjar` alias to `deps.edn` (or use `clj -M -e` with compile/classpath) to produce a standalone JAR
- Alternatively, use `tools.build` or a simple classpath-based approach to assemble the uberjar
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
- **Uberjar approach:** Clojure CLI does not have a built-in uberjar command. Options: (a) use `tools.build` (official Clojure build library), (b) use `depstar`, (c) manual classpath copy. `tools.build` is the recommended approach and is an official Clojure library, consistent with the "standard JVM tooling" principle.
- **Image size:** Using JRE instead of JDK for runtime keeps the image small (~200MB vs ~400MB+).
