# Phase 3: Docker — Validation

## Commands to Run

### Build the uberjar

```bash
clj -T:build uber
java -jar target/hello-world.jar
java -jar target/hello-world.jar "Uberjar"
```

**Expected output:**

```
Hello, World!
Hello, Uberjar!
```

### Build the Docker image

```bash
docker build -t hello-world .
```

**Expected:** Build completes successfully.

### Run the Docker container

```bash
docker run --rm hello-world
docker run --rm hello-world "Docker"
```

**Expected output:**

```
Hello, World!
Hello, Docker!
```

### Run tests (regression check)

```bash
clj -M:test
```

**Expected:** All tests pass.

### Makefile targets

```bash
make docker-build
make docker-run
```

**Expected:** Image builds and prints `Hello, World!`.

## Acceptance Criteria

- [ ] `deps.edn` has a `:build` alias with `org.clojure/tools.build`
- [ ] `build.clj` exists and `clj -T:build uber` produces `target/hello-world.jar`
- [ ] `java -jar target/hello-world.jar` prints `Hello, World!`
- [ ] `java -jar target/hello-world.jar "Test"` prints `Hello, Test!`
- [ ] `src/hello/core.clj` includes `(:gen-class)` in the `ns` form
- [ ] `Dockerfile` exists with a multi-stage build (JDK build, JRE runtime)
- [ ] `.dockerignore` exists and excludes build artifacts and non-essential files
- [ ] `docker build -t hello-world .` succeeds
- [ ] `docker run --rm hello-world` prints `Hello, World!`
- [ ] `docker run --rm hello-world "Docker"` prints `Hello, Docker!`
- [ ] `Makefile` has `docker-build` and `docker-run` targets
- [ ] `.github/workflows/ci.yml` includes a Docker build step
- [ ] `clj -M:test` still passes (no regressions)
- [ ] No new runtime dependencies added
