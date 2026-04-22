Plan the next phase of development by creating a detailed spec from the roadmap.

## Steps

1. Check for uncommitted changes with `git status`. If there are uncommitted changes, use AskUserQuestion to ask whether to stash them or abort.

2. Check out main and pull latest:
   ```
   git checkout main && git pull
   ```

3. Read `specs/roadmap.md` and identify the next unstarted phase. A phase is "done" if a branch or merged PR exists for it. Check with:
   ```
   git branch -a --list '*phase-*'
   git log --oneline --all --grep="phase"
   ```

4. Create a new branch following the naming convention in `specs/conventions.md`:
   ```
   git checkout -b phase-<N>-<short-description>
   ```

5. Use AskUserQuestion to ask the user:
   - Are there any changes to the phase scope from what's in the roadmap?
   - Any specific implementation preferences or constraints?

6. Create a spec directory for the phase: `specs/YYYY-MM-DD-phase-<N>-<name>/` containing:

   **plan.md** — Implementation plan with:
   - Overview of what this phase delivers
   - Task groups (ordered steps to implement)
   - Dependencies and risks

   **requirements.md** — Detailed requirements:
   - Functional requirements (what the code must do)
   - Files to create or modify
   - Reference to `specs/mission.md` design principles and `specs/tech-stack.md` for technical decisions

   **validation.md** — How to verify the phase is complete:
   - Specific commands to run
   - Expected outputs
   - Checklist of acceptance criteria

7. After writing the spec files, review them for:
   - Consistency with `specs/mission.md` (goals, non-goals, design principles)
   - Consistency with `specs/tech-stack.md` (correct commands, structure, dependencies)
   - Completeness (no gaps in the plan)
   - Feasibility (each task is actionable)

8. Report what was created and what to do next (run `/sdd-implement`).
