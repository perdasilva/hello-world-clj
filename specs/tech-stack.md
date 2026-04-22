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
├── src/
│   └── hello/
│       ├── core.clj      # -main entry point, system assembly
│       ├── system.clj    # System map: component wiring and lifecycle
│       └── component/
│           └── greeter.clj # Greeter component
├── test/
│   └── hello/
│       ├── core_test.clj     # Integration tests for the full system
│       └── component/
│           └── greeter_test.clj # Unit tests for greeter component
├── Dockerfile            # Container build
├── .github/
│   └── workflows/
│       └── ci.yml        # GitHub Actions CI
├── specs/                # SDD governing specs
├── .claude/
│   └── commands/         # SDD workflow commands
└── CLAUDE.md             # Project guide for Claude Code
```

## Build & Run Commands

| Action | Command |
|---|---|
| Run | `clj -M -m hello.core` |
| Run with arg | `clj -M -m hello.core "Name"` |
| Test | `clj -M:test` |
| Format check | `clj -M:fmt-check` (if configured) |
| Docker build | `docker build -t hello-world .` |
| Docker run | `docker run --rm hello-world` |

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

- **Base image:** Eclipse Temurin JDK 21 (build) / JRE 21 (runtime)
- **Multi-stage build:** compile in build stage, copy to minimal runtime image
