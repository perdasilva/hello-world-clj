# Phase 3: Docker — Requirements

## Functional Requirements

### Uberjar build

- Add `org.clojure/tools.build` as a build dependency in `deps.edn` under a `:build` alias
- Create `build.clj` in the project root with:
  - A `uber` function that compiles the project and produces a standalone uberjar at `target/hello-world.jar`
  - The uberjar must set `hello.core` as the main class
- Add AOT compilation support: the `-main` function in `hello.core` must be AOT-compiled so `java -jar` works
- Verify `clj -T:build uber` produces a working JAR
- Verify `java -jar target/hello-world.jar` prints `Hello, World!`

### Dockerfile

- Multi-stage build with two stages:
  - **Stage 1 — build:**
    - Base image: `eclipse-temurin:21-jdk`
    - Install Clojure CLI tools
    - Copy `deps.edn` and `build.clj` first, download dependencies (layer caching)
    - Copy `src/` and build the uberjar
  - **Stage 2 — runtime:**
    - Base image: `eclipse-temurin:21-jre`
    - Copy the uberjar from the build stage
    - Set `ENTRYPOINT ["java", "-jar", "hello-world.jar"]` so arguments are passed through
- Use `.dockerignore` to exclude unnecessary files from the build context

### Docker run behavior

- `docker run --rm hello-world` prints `Hello, World!`
- `docker run --rm hello-world "Docker"` prints `Hello, Docker!`

### Makefile updates

- Add `docker-build` target: runs `docker build -t hello-world .`
- Add `docker-run` target: runs `docker run --rm hello-world`
- Add both to `.PHONY`

### CI update (`.github/workflows/ci.yml`)

- Add a `docker-build` step after the test step
- Step runs `docker build -t hello-world .`
- Build-only — no push to a registry

## Files to Create or Modify

| File | Action | Purpose |
|---|---|---|
| `deps.edn` | Modify | Add `:build` alias with `tools.build` |
| `build.clj` | Create | Uberjar build script |
| `src/hello/core.clj` | Modify | Add `(:gen-class)` for AOT compilation |
| `Dockerfile` | Create | Multi-stage container build |
| `.dockerignore` | Create | Exclude files from Docker context |
| `Makefile` | Modify | Add `docker-build` and `docker-run` targets |
| `.github/workflows/ci.yml` | Modify | Add Docker build step |

## Design Principles (from mission.md)

- **Simplicity first:** one Dockerfile, one build command, no registry push
- **Zero external deps (runtime):** `tools.build` is build-time only, not bundled in the uberjar beyond Clojure itself
- **Reproducible builds:** `docker build` works on any machine with Docker installed

## Technical Decisions (from tech-stack.md)

- Build image: Eclipse Temurin JDK 21
- Runtime image: Eclipse Temurin JRE 21
- `tools.build` is the official Clojure build library for producing uberjars
- `ENTRYPOINT` (not `CMD`) so `docker run` arguments pass through to `-main`
