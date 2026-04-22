# Phase 1: Core CLI — Requirements

## Functional Requirements

### deps.edn

- Declare `org.clojure/clojure` version `1.12.0` as the sole dependency
- Set `src` as the source path

### Lifecycle protocol (`src/hello/system.clj`)

- Define a `Lifecycle` protocol with two methods:
  - `(start [component])` — returns the started component
  - `(stop [component])` — returns the stopped component
- Implement `create-system`: accepts a vector of `[key component]` pairs, returns an ordered system map
- Implement `start-system`: walks the system map in order, calling `start` on each component, returns the updated map
- Implement `stop-system`: walks the system map in reverse order, calling `stop` on each component, returns the updated map

### Greeter component (`src/hello/component/greeter.clj`)

- Define a `Greeter` defrecord implementing `Lifecycle`
  - `start` returns the component unchanged (no resources to acquire)
  - `stop` returns the component unchanged (no resources to release)
- Define a `greet` function:
  - Arity 1: `(greet component)` → returns `"Hello, World!"`
  - Arity 2: `(greet component name)` → returns `"Hello, {name}!"`
- Define a `create-greeter` factory function that returns a new `Greeter` instance

### Entry point (`src/hello/core.clj`)

- Define a `-main` function accepting `[& args]`
- `-main` must:
  1. Build a system containing the greeter component
  2. Start the system
  3. Extract the greeter from the started system
  4. Call `greet` with the first CLI argument (if provided)
  5. Print the greeting with `println`
  6. Stop the system

## Files to Create

| File | Purpose |
|---|---|
| `deps.edn` | Project dependencies and paths |
| `src/hello/system.clj` | Lifecycle protocol, system map utilities |
| `src/hello/component/greeter.clj` | Greeter component |
| `src/hello/core.clj` | `-main` entry point |

## Design Principles (from mission.md)

- **Simplicity first:** each file does one thing; no abstractions beyond what's needed
- **Zero external deps:** only `org.clojure/clojure`
- **Component pattern:** protocol + defrecord, no library
- **Reproducible builds:** `clj -M -m hello.core` must work after clone

## Technical Decisions (from tech-stack.md)

- Use `defrecord` for the greeter component (not plain maps) so it can implement the protocol directly
- System map is an ordered collection (vector of pairs or array-map) to preserve start/stop order
- No logging, no config — just `println` for output
