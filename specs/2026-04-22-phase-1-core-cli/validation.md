# Phase 1: Core CLI — Validation

## Commands to Run

### Default greeting (no arguments)

```bash
clj -M -m hello.core
```

**Expected output:**

```
Hello, World!
```

### Named greeting

```bash
clj -M -m hello.core "Clojure"
```

**Expected output:**

```
Hello, Clojure!
```

## Acceptance Criteria

- [ ] `deps.edn` exists with `org.clojure/clojure` 1.12.0 and `src` path
- [ ] `src/hello/system.clj` defines `Lifecycle` protocol with `start` and `stop`
- [ ] `src/hello/system.clj` provides `create-system`, `start-system`, `stop-system`
- [ ] `src/hello/component/greeter.clj` defines `Greeter` record implementing `Lifecycle`
- [ ] `greet` with no name returns `"Hello, World!"`
- [ ] `greet` with a name returns `"Hello, {name}!"`
- [ ] `src/hello/core.clj` defines `-main` that wires the system, runs the greeter, and prints output
- [ ] `clj -M -m hello.core` prints `Hello, World!`
- [ ] `clj -M -m hello.core "Clojure"` prints `Hello, Clojure!`
- [ ] No external dependencies beyond `org.clojure/clojure`
- [ ] No compiler warnings or reflection warnings
