# Phase 2: Tests + CI — Requirements

## Functional Requirements

### deps.edn `:test` alias

- Add a `:test` alias with:
  - `:extra-paths ["test"]`
  - `:extra-deps {org.clojure/test.check {:mvn/version "1.1.1"}}` 
  - `:main-opts` configured to run tests (either via a test runner or a custom test entry point)
- Keep the existing `:paths` and `:deps` unchanged

### Test runner

- Use a minimal test runner approach: a `test/hello/test_runner.clj` namespace with a `-main` that discovers and runs all test namespaces, or use `clojure.main -m` with a test runner
- Avoid adding `cognitect-labs/test-runner` if a simpler approach works
- The runner must exit with a non-zero status code on failure (critical for CI)

### Greeter unit tests (`test/hello/component/greeter_test.clj`)

- Use `clojure.test` (`deftest`, `testing`, `is`)
- Example-based tests:
  - `(greet (create-greeter))` returns `"Hello, World!"`
  - `(greet (create-greeter) "Clojure")` returns `"Hello, Clojure!"`
  - `(greet (create-greeter) "")` returns `"Hello, !"`  (edge case)
- Generative tests using `clojure.test.check`:
  - Property: for any non-empty alphanumeric string `s`, `(greet (create-greeter) s)` equals `(str "Hello, " s "!")`
  - Property: for any string `s`, the result of `(greet (create-greeter) s)` starts with `"Hello, "` and ends with `"!"`
  - Use `defspec` from `clojure.test.check.clojure-test` to integrate with `clojure.test`
  - Use `gen/string-alphanumeric` or `gen/string` for generators

### System integration tests (`test/hello/core_test.clj`)

- Test the full system lifecycle:
  - Build and start the system, extract greeter, call `greet`, verify result, stop the system
  - No-arg case: greeting is `"Hello, World!"`
  - With-arg case: greeting is `"Hello, <name>!"`
- Verify `start-system` and `stop-system` return valid system maps

### Makefile

- Create `Makefile` with the following targets:
  - `help` (default): self-documenting, prints all targets and descriptions
  - `run`: runs `clj -M -m hello.core $(NAME)`, supports optional `NAME=` variable
  - `test`: runs `clj -M:test`
  - `clean`: removes `.cpcache` and `target` directories
- All targets declared `.PHONY`

### GitHub Actions CI (`.github/workflows/ci.yml`)

- Name: `CI`
- Triggers: `push` to `main`, `pull_request` to `main`
- Single job: `test`
- Runs on: `ubuntu-latest`
- Steps:
  1. Checkout code
  2. Set up Java 21 (use `actions/setup-java` with `temurin` distribution)
  3. Install Clojure CLI (use `DeLaGuardo/setup-clojure` action)
  4. Cache Clojure dependencies (maven + gitlibs)
  5. Run tests: `clj -M:test`

## Files to Create or Modify

| File | Action | Purpose |
|---|---|---|
| `deps.edn` | Modify | Add `:test` alias |
| `test/hello/component/greeter_test.clj` | Create | Greeter unit + generative tests |
| `test/hello/core_test.clj` | Create | System integration tests |
| `test/hello/test_runner.clj` | Create | Test runner entry point |
| `Makefile` | Create | Developer convenience targets |
| `.github/workflows/ci.yml` | Create | GitHub Actions CI workflow |

## Design Principles (from mission.md)

- **Simplicity first:** minimal test runner, no unnecessary test frameworks
- **Zero external deps (runtime):** `test.check` is test-only, not bundled in production
- **Learn by doing:** introduces `clojure.test`, generative testing, and GitHub Actions
- **Reproducible builds:** `clj -M:test` must work after clone

## Technical Decisions (from tech-stack.md)

- Test framework: `clojure.test` (built-in) + `org.clojure/test.check` (generative)
- Test structure mirrors source: `test/hello/component/greeter_test.clj` tests `src/hello/component/greeter.clj`
- CI uses Java 21 (Temurin) to match the project's JVM requirement
