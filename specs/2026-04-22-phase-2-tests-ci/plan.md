# Phase 2: Tests + CI — Implementation Plan

## Overview

Add automated testing (example-based and generative) and a GitHub Actions CI workflow. This phase introduces `clojure.test` for example-based tests and `test.check` for property-based generative tests, plus a `:test` alias in `deps.edn` and a CI pipeline that runs on push and PR.

## Task Groups

### 1. Test alias and dependencies

- Add a `:test` alias to `deps.edn` with:
  - `test` added to extra paths
  - `org.clojure/test.check` as an extra dependency (test-only)
  - A test runner main opts configuration using `cognitect-labs/test-runner` or Clojure's built-in test invocation

### 2. Greeter unit tests

- Create `test/hello/component/greeter_test.clj`
- Example-based tests:
  - `greet` with no name returns `"Hello, World!"`
  - `greet` with `"Clojure"` returns `"Hello, Clojure!"`
- Generative tests:
  - Property: `greet` with any non-empty string returns `"Hello, <that-string>!"`
  - Property: `greet` with any string returns a result that starts with `"Hello, "` and ends with `"!"`

### 3. System integration tests

- Create `test/hello/core_test.clj`
- Test that the full system starts, produces the correct greeting, and stops cleanly
- Cover both no-arg and with-arg cases
- Verify the system can be started and stopped without errors

### 4. Verify tests pass

- Run `clj -M:test` and confirm all tests pass (example-based and generative)

### 5. Makefile

- Create `Makefile` with standard developer targets: `help`, `run`, `test`, `clean`
- `help` is the default target, self-documenting via comment parsing
- `run` supports an optional `NAME=` argument for custom greetings

### 6. GitHub Actions CI

- Create `.github/workflows/ci.yml`
- Workflow triggers on push to `main` and on pull requests
- Steps: checkout, install Java 21, install Clojure CLI, run tests

## Dependencies and Risks

- **`org.clojure/test.check`** is an official Clojure contrib library, used only in the `:test` alias — not a runtime dependency. This is consistent with the "zero external deps" principle for production code.
- **Test runner choice:** need a way to discover and run tests from the CLI. Options include `cognitect-labs/test-runner` (extra dep) or a simple custom `-main` that invokes `clojure.test`. Prefer the simpler approach to avoid another dependency.
- **CI requires a public repo or GitHub account** to actually execute, but the workflow file can be validated locally.
