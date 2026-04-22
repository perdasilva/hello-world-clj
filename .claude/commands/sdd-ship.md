Ship the current phase: verify, commit, and publish.

## Phase 1 — Verify

1. Run the project check command:
   ```
   clj -M:test
   ```

2. If a Dockerfile exists, verify it builds:
   ```
   docker build -t hello-world .
   ```

3. If a phase spec exists (under `specs/`), read `validation.md` and verify each acceptance criterion passes.

4. Check that `CLAUDE.md` is up to date with any new commands, structure changes, or workflow updates.

## Phase 2 — Commit

1. Read `specs/conventions.md` for commit message and PR format.

2. Stage all relevant changes:
   ```
   git add -A
   ```

3. Review what will be committed:
   ```
   git diff --cached --stat
   ```

4. Use AskUserQuestion to show the user the proposed commit message and ask for confirmation before committing. The commit message must follow conventional commits format from `specs/conventions.md`.

5. If there are multiple commits from review fixes, squash them into a single clean commit per logical change.

## Phase 3 — Merge

1. Use AskUserQuestion to confirm before merging. Show the user:
   - The current branch name
   - The commit(s) that will be merged into main
   - Ask: "Merge this branch into main?"

2. If confirmed, merge into main:
   ```
   git checkout main
   git merge <branch-name>
   ```

3. Delete the feature branch:
   ```
   git branch -d <branch-name>
   ```

4. Report what was merged and what to do next.
