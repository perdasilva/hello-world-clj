Review all changes on the current branch for quality and consistency.

## Steps

1. Get the list of changed files:
   ```
   git diff --name-only main...HEAD
   git diff --name-only
   git diff --name-only --cached
   ```

2. Read each changed file and review for:
   - **Correctness:** Does the code do what the spec requires?
   - **Simplicity:** Is this the simplest solution? Any unnecessary complexity?
   - **Consistency with specs:**
     - `specs/mission.md` — respects design principles and non-goals?
     - `specs/tech-stack.md` — follows project structure and conventions?
     - `specs/conventions.md` — commit messages and code style?
   - **Tests:** Are all new functions covered by tests?
   - **No external deps:** Verify no third-party libraries were introduced

3. Check whether `CLAUDE.md` needs updating:
   - New commands or aliases added?
   - New files or directories that change the project structure?

4. Check whether governing specs need updating:
   - Does `specs/tech-stack.md` project structure still match reality?
   - Any roadmap adjustments needed?

5. For issues with multiple valid solutions, use AskUserQuestion to let the user choose. For straightforward fixes (typos, formatting, missing test cases), apply them directly.

6. Run `clj -M:test` to confirm everything still passes after any fixes.

7. Report findings and what to do next (run `/sdd-ship`).
