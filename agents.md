# Agent Registry

All agents available in this project via `.claude/agents/`.

## Template Agents

| Agent | Model | Mode | Purpose |
|-------|-------|------|---------|
| architect | opus | plan | System design, tech stack decisions, file structure, architectural review |
| reviewer | sonnet | plan | Code review: correctness, naming, DRY, error handling, standards |
| security | opus | plan | Android security: permissions, data storage, secrets, OWASP |
| performance | sonnet | plan | Room queries, Compose recomposition, memory leaks, lifecycle |

## Project-Specific Agents

| Agent | Model | Purpose |
|-------|-------|---------|
| build-error-resolver | inherit | Fix Kotlin/Android compilation errors with MVP-aligned solutions |
| code-architecture-reviewer | inherit | Review for MVP principles, TDD compliance, simplification |
| production-principles-enforcer | inherit | Prevent over-engineering while ensuring production quality |
| strategic-plan-architect | inherit | Create MVP implementation plans with phases and risks |
