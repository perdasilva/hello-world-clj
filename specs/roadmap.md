# Roadmap

## Phase 1: Core CLI

Build the minimal Clojure CLI application using the Component structural pattern.

- Create `deps.edn` with Clojure dependency
- Define a `Lifecycle` protocol in `hello.system` with `start` and `stop` functions
- Create a `greeter` component in `src/hello/component/greeter.clj` that implements `Lifecycle` and provides a `greet` function
- `greet` accepts an optional name and returns "Hello, World!" or "Hello, {name}!"
- Create `src/hello/system.clj` with a system map that wires components and starts/stops them in order
- Create `src/hello/core.clj` with `-main` that builds the system, starts it, runs the greeter, and stops it
- `-main` reads an optional CLI argument and prints the greeting
- Verify the app runs with `clj -M -m hello.core`
- Verify the app accepts a name argument: `clj -M -m hello.core "Clojure"`

## Phase 2: Tests + CI

Add automated testing and continuous integration.

- Create `:test` alias in `deps.edn` with test paths and runner
- Create `test/hello/component/greeter_test.clj` with unit tests for the greeter component
- Create `test/hello/core_test.clj` with integration tests for the full system (no-arg and with-name cases)
- Verify tests pass with `clj -M:test`
- Create `.github/workflows/ci.yml` with GitHub Actions workflow
- CI installs Java 21 + Clojure CLI, runs tests on push and PR

## Phase 3: Docker

Containerize the application.

- Create a multi-stage `Dockerfile` (JDK build stage, JRE runtime stage)
- Build an uberjar or use `clj` to run in the container
- Verify `docker build -t hello-world .` succeeds
- Verify `docker run --rm hello-world` prints "Hello, World!"
- Verify `docker run --rm hello-world "Docker"` prints "Hello, Docker!"
- Add Docker build step to CI workflow
