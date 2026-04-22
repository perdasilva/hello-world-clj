# Tech Stack

## Language & Runtime

- **Language:** Clojure 1.12+
- **Runtime:** JVM (Java 21+)
- **Build tool:** Clojure CLI (`deps.edn`)

## Dependencies

### Core

None beyond `org.clojure/clojure`. This project uses only Clojure core.

### Dev / Test

| Dependency | Purpose |
|---|---|
| `clojure.test` | Unit testing (built into Clojure core) |
| `org.clojure/test.check` | Generative (property-based) testing |

### Build

| Dependency | Purpose |
|---|---|
| `io.github.clojure/tools.build` | Uberjar compilation (build-time only) |

## Architecture Pattern

This project follows Stuart Sierra's Component structural pattern without the external library:

- **Components** are plain Clojure maps (or defrecords) with explicit `start` and `stop` lifecycle functions
- **Dependencies** between components are declared explicitly and injected via the system map
- **System map** assembles components and their dependencies, started/stopped in dependency order
- Each component lives in its own namespace under `hello.component.*`

This is implemented with plain Clojure (protocols, maps, functions) — no `com.stuartsierra/component` library.

## Project Structure

```
hello-world/
├── deps.edn              # Dependencies and aliases
├── build.clj             # Uberjar build script (tools.build)
├── src/
│   └── hello/
│       ├── core.clj      # -main entry point, system assembly
│       ├── system.clj    # System map: component wiring and lifecycle
│       └── component/
│           └── greeter.clj # Greeter component
├── test/
│   └── hello/
│       ├── test_runner.clj   # Test runner entry point
│       ├── core_test.clj     # Integration tests for the full system
│       └── component/
│           └── greeter_test.clj # Unit + generative tests for greeter
├── Makefile              # Developer convenience targets
├── Dockerfile            # GraalVM native-image container build
├── .dockerignore         # Docker build context exclusions
├── .github/
│   └── workflows/
│       └── ci.yml        # GitHub Actions CI
├── specs/                # SDD governing specs
├── .claude/
│   └── commands/         # SDD workflow commands
└── CLAUDE.md             # Project guide for Claude Code
```

## Build & Run Commands

| Action | Command | Make shortcut |
|---|---|---|
| Run | `clj -M -m hello.core` | `make run` |
| Run with arg | `clj -M -m hello.core "Name"` | `make run NAME=Name` |
| Test | `clj -M:test` | `make test` |
| Clean caches | `rm -rf .cpcache target` | `make clean` |
| Uberjar build | `clj -T:build uber` | — |
| Docker build | `docker build -t hello-world .` | `make docker-build` |
| Docker run | `docker run --rm hello-world` | `make docker-run` |

## Check Command

The canonical check command for this project is:

```
clj -M:test
```

## CI/CD

- **Platform:** GitHub Actions
- **Triggers:** push to main, pull requests
- **Steps:** checkout, install Java + Clojure CLI, run tests, build Docker image

## Containerization

- **Three-stage build:**
  1. Eclipse Temurin JDK 21 (Alpine) — Clojure uberjar compilation
  2. GraalVM native-image-community 21 (muslib) — native binary compilation
  3. `scratch` — static binary only, no OS layer
- **GraalVM native-image** produces a statically-linked binary (~5MB image)
- **`--initialize-at-build-time`** required for Clojure AOT compatibility
