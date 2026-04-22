# Conventions

## Commit Messages

Use [Conventional Commits](https://www.conventionalcommits.org/) format:

```
<type>: <subject>

[optional body]

[optional footer(s)]
```

### Types

| Type | When to use |
|---|---|
| `feat` | New feature or functionality |
| `fix` | Bug fix |
| `test` | Adding or updating tests |
| `ci` | CI/CD configuration changes |
| `docs` | Documentation changes |
| `chore` | Maintenance tasks, dependency updates |
| `refactor` | Code restructuring without behavior change |

### Examples

```
feat: add greet function with optional name argument
```

```
test: add unit tests for greet function

Cover both default greeting and named greeting cases.
```

```
ci: add GitHub Actions workflow for test and Docker build
```

### Rules

- Subject line: imperative mood, lowercase, no period, max 72 chars
- Body: wrap at 72 chars, explain *why* not *what*
- Include `Co-Authored-By` trailer when commits are co-authored with Claude

## Pull Requests

### Title

Match the primary commit's conventional commit subject:

```
feat: add greet function with optional name argument
```

### Description

Use this template:

```markdown
## Summary
- <1-3 bullet points describing what changed and why>

## Test Plan
- [ ] <verification steps>
```

## Branch Naming

Format: `phase-<N>-<short-description>`

Examples:
- `phase-1-core-cli`
- `phase-2-tests-ci`
- `phase-3-docker`
