# Mission

A minimal Clojure CLI application that prints "Hello, World!" — built as a learning exercise to explore Clojure fundamentals, testing, CI/CD, and containerization using the spec-driven development workflow.

## Goals

- Build a working Clojure CLI that greets the user
- Learn Clojure basics: namespaces, functions, entry points, deps.edn
- Establish a test suite with clojure.test
- Set up GitHub Actions for automated checks
- Containerize the application with Docker

## Non-Goals

- No web server, REST API, or HTTP endpoints
- No database or persistence layer
- No third-party runtime dependencies beyond Clojure core
- No ClojureScript or browser/Node.js targets
- No complex CLI argument parsing libraries

## Design Principles

- **Simplicity first:** prefer the simplest solution that works
- **Zero external deps:** rely only on Clojure core and standard JVM tooling
- **Component pattern:** follow Stuart Sierra's Component structural pattern — organize code into components with explicit lifecycle (start/stop) and dependency declarations, but implement with plain Clojure maps and protocols instead of the external library
- **Learn by doing:** each phase introduces one new concept or tool
- **Reproducible builds:** anyone can clone, build, test, and run with standard tools

## Development Practices

- Follow spec-driven development: plan phases, implement, review, ship
- All code must pass tests before merging
- Use conventional commits for clear history
- Keep the codebase small enough to read in one sitting
