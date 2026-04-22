# Phase 1: Core CLI — Implementation Plan

## Overview

Deliver a minimal Clojure CLI application that prints a greeting using the Component structural pattern. This phase establishes the project's foundation: `deps.edn`, the Lifecycle protocol, a greeter component, a system map, and a `-main` entry point.

## Task Groups

### 1. Project bootstrap

- Create `deps.edn` with `org.clojure/clojure` 1.12.0 dependency and `src` as a source path

### 2. Lifecycle protocol

- Create `src/hello/system.clj`
- Define a `Lifecycle` protocol with `start` and `stop` functions
- Implement `create-system` to build a system map from a component list, and `start-system` / `stop-system` to walk the map in order

### 3. Greeter component

- Create `src/hello/component/greeter.clj`
- Define a `greeter` component (defrecord) implementing `Lifecycle`
- Implement a `greet` function: no argument returns `"Hello, World!"`, with a name argument returns `"Hello, {name}!"`

### 4. Entry point

- Create `src/hello/core.clj`
- Implement `-main` that:
  1. Builds the system (greeter component)
  2. Starts the system
  3. Calls `greet` (passing an optional first CLI arg)
  4. Prints the result
  5. Stops the system

### 5. Manual verification

- Run `clj -M -m hello.core` → prints `Hello, World!`
- Run `clj -M -m hello.core "Clojure"` → prints `Hello, Clojure!`

## Dependencies and Risks

- **Java 21+ and Clojure CLI must be installed** on the dev machine. If missing, the run step will fail — install via Homebrew (`brew install clojure/tools/clojure`).
- No external dependencies beyond `org.clojure/clojure`, so no supply-chain risk.
- The Component pattern is implemented in plain Clojure; the risk of over-engineering is mitigated by keeping the system map to a single component for now.
