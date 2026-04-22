Implement the current phase by following its spec.

## Steps

1. Identify the current phase by reading the branch name and finding the matching spec directory under `specs/`.

2. Read the phase spec files:
   - `plan.md` — for task groups and implementation order
   - `requirements.md` — for detailed requirements and file list
   - `validation.md` — for acceptance criteria

3. Implement each task group in order:
   - Follow the plan's task sequence
   - Create or modify files as specified in requirements.md
   - After each task group, run `clj -M:test` (if tests exist) to catch regressions early
   - Use AskUserQuestion if a decision isn't covered by the spec or governing docs

4. After all task groups are complete, run full validation:
   ```
   clj -M:test
   ```
   Verify each criterion in `validation.md` passes.

5. If any validation fails, fix the issue and re-run validation.

6. Report what was implemented and what to do next (run `/sdd-review`).
