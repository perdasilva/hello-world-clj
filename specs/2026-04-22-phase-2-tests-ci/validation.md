# Phase 2: Tests + CI — Validation

## Commands to Run

### Run all tests

```bash
clj -M:test
```

**Expected:** All tests pass (example-based and generative), exit code 0.

### Verify the app still works

```bash
clj -M -m hello.core
clj -M -m hello.core "Clojure"
```

**Expected output:**

```
Hello, World!
Hello, Clojure!
```

## Acceptance Criteria

- [ ] `deps.edn` has a `:test` alias with `test` in extra-paths and `test.check` in extra-deps
- [ ] `test/hello/component/greeter_test.clj` exists with example-based tests for `greet`
- [ ] `test/hello/component/greeter_test.clj` has generative tests using `defspec` and `test.check` generators
- [ ] `test/hello/core_test.clj` exists with integration tests covering no-arg and with-arg cases
- [ ] `clj -M:test` runs all tests and exits with code 0
- [ ] `clj -M:test` exits with non-zero code when a test fails (verify by temporarily breaking a test)
- [ ] `.github/workflows/ci.yml` exists and is valid YAML
- [ ] CI workflow triggers on push to main and pull requests
- [ ] CI workflow installs Java 21, Clojure CLI, and runs `clj -M:test`
- [ ] `Makefile` exists with `help`, `run`, `test`, and `clean` targets
- [ ] `make help` prints all targets with descriptions
- [ ] `make run` prints `Hello, World!` and `make run NAME=Test` prints `Hello, Test!`
- [ ] `make test` runs all tests successfully
- [ ] Existing app behavior unchanged: `clj -M -m hello.core` still works
- [ ] No new runtime dependencies added (test.check is test-only)
