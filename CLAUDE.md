## Communication Style

When speaking to the user, use Jamaican Patois. Keep code, commits, specs, and documentation in standard English.

# Hello World (Clojure)

A minimal Clojure CLI application that prints "Hello, World!" — a learning exercise exploring Clojure fundamentals, testing, CI/CD, and containerization.

## Design Principles

- Simplicity first: prefer the simplest solution that works
- Zero external deps: only Clojure core and standard JVM tooling
- Component pattern: follow Stuart Sierra's Component structure (lifecycle, explicit deps, system map) implemented with plain Clojure — no external library
- Learn by doing: each phase introduces one new concept
- Reproducible builds: clone, build, test, run with standard tools

## Build & Run

```bash
# Run the app
make run
clj -M -m hello.core

# Run with a name argument
make run NAME=Clojure
clj -M -m hello.core "Clojure"

# Run tests
make test
clj -M:test

# Clean build caches
make clean

# Docker build and run
docker build -t hello-world .
docker run --rm hello-world
```

## Workflow

This project uses spec-driven development with phased delivery:

1. `/sdd-plan-next-phase` — Plan the next phase from the roadmap, create branch and spec
2. `/sdd-implement` — Implement the current phase following its spec
3. `/sdd-review` — Review changes for quality and spec consistency
4. `/sdd-ship` — Verify, commit, and create a PR

Governing specs live in `specs/`:
- `mission.md` — Goals, non-goals, design principles
- `tech-stack.md` — Language, tools, project structure, commands
- `roadmap.md` — Phased delivery plan
- `conventions.md` — Commit and PR format

## Conventions

- Conventional commits: `type: subject` (e.g., `feat: add greet function`)
- Branch naming: `phase-<N>-<short-description>`
- PR title matches commit subject; body has Summary + Test Plan sections
- No third-party dependencies — Clojure core only
