# Notification Hub - Claude Code Instructions

## What the `.claude/` folder contains

This project uses Claude Code with a customized set of skills, agents, and settings for Android/Kotlin development.

```
.claude/
  agents/                            # Agent definitions
    architect.md                     # System design and planning
    reviewer.md                      # Code review
    security.md                      # Android security analysis
    performance.md                   # Performance analysis
    build-error-resolver.md          # Fix compilation errors
    code-architecture-reviewer.md    # MVP compliance review
    production-principles-enforcer.md # Anti-over-engineering
    strategic-plan-architect.md      # Implementation planning
  commands/                          # Slash command entry points
    build-and-fix.md
    code-review.md
    dev-docs.md
    dev-docs-update.md
  skills/                            # Skill definitions
    android-dev-guidelines/          # Kotlin/Room/coroutines patterns
    compose-ui-guidelines/           # Jetpack Compose + M3 patterns
    production-principles/           # Simple > Complex philosophy
    security-practices/              # Auth, validation, secrets
    tdd-workflow/                    # Red-Green-Refactor cycle
    build-and-fix/                   # Gradle error resolution
    code-review/                     # Full codebase review
    security-scan/                   # OWASP + secrets detection
    performance-review/              # Bottleneck analysis
    dependency-audit/                # Package vulnerability check
    test-scaffold/                   # Test file generation
    doc-sync/                        # Documentation sync
    dev-docs/                        # Strategic planning
    dev-docs-update/                 # Plan progress updates
    init-repo/                       # Repo config setup
    update-practices/                # Best practice refresh
    skill-developer/                 # Meta-skill for creating skills
  references/
    source-urls.md                   # URL registry for best practices
  settings.json                      # Project-level settings
```

---

## Skills Reference

### Slash Commands (user-invocable)

| Command | Description |
|---------|-------------|
| `/build-and-fix` | Run Gradle build and systematically fix all compilation errors |
| `/code-review [path]` | Full code review with CRITICAL/WARNING/SUGGESTION findings |
| `/security-scan [path]` | OWASP Top 10, secrets detection, dependency vulnerabilities |
| `/performance-review [path]` | Room query optimization, Compose recomposition, memory leaks |
| `/dependency-audit` | Check gradle/libs.versions.toml for outdated or vulnerable packages |
| `/test-scaffold [path]` | Generate test stubs for untested Kotlin modules |
| `/doc-sync` | Sync README.md, CLAUDE.md, and other docs with current code |
| `/dev-docs [task]` | Create strategic MVP implementation plan |
| `/dev-docs-update` | Update existing plan with progress |
| `/init-repo` | Build or rebuild the `.claude/` folder configuration |
| `/update-practices` | Fetch latest Claude Code best practices and update config |

### Auto-Activating Skills (background knowledge)

These load automatically when Claude detects relevant context:

| Skill | Activates When |
|-------|---------------|
| `android-dev-guidelines` | Writing Kotlin, Room, services, permissions |
| `compose-ui-guidelines` | Building Compose UI, theming, state management |
| `production-principles` | Architecture decisions, complexity trade-offs |
| `security-practices` | Auth, validation, secrets, data storage |
| `tdd-workflow` | Writing or discussing tests |
| `skill-developer` | Creating or modifying skills |

---

## Agents Reference

### Template Agents

| Agent | Model | Mode | Purpose |
|-------|-------|------|---------|
| `architect` | opus | plan | System design, tech stack decisions, file structure |
| `reviewer` | sonnet | plan | Code review: correctness, naming, DRY, standards |
| `security` | opus | plan | Android security: permissions, OWASP, secrets |
| `performance` | sonnet | plan | Room queries, Compose recomposition, memory |

### Project-Specific Agents

| Agent | Purpose |
|-------|---------|
| `build-error-resolver` | Fix Kotlin/Android compilation errors with simplest solutions |
| `code-architecture-reviewer` | Check MVP principles, TDD compliance, simplification |
| `production-principles-enforcer` | Prevent over-engineering, enforce production quality |
| `strategic-plan-architect` | Create MVP implementation plans with phases and risks |

---

## Hooks

Configured in `.claude/settings.json`:

- **PreToolUse (git commit):** Logs notification when commits are made

### Supported Hook Events

`PreToolUse`, `PostToolUse`, `SessionStart`, `SessionEnd`, `Stop`, `SubagentStop`, `Notification`, `PreCompact`, `UserPromptSubmit`, `ConfigChange`, `SubagentStart`, `TeammateIdle`, `TaskCompleted`, `PermissionRequest`

---

## Customizing for This Project

### Adding New Skills

1. Create a folder in `.claude/skills/` with your skill name
2. Create `SKILL.md` inside with YAML frontmatter (`name`, `description`)
3. Add `user-invocable: true` for slash commands, omit for auto-activating skills
4. Write step-by-step instructions in the markdown body
5. Update the skill tables in `CLAUDE.md` and this file

### Adding New Agents

1. Create a markdown file in `.claude/agents/`
2. Add YAML frontmatter: `name`, `description`, `model`, `tools`, `permissionMode`
3. Write the agent's role and behavior
4. Register in `agents.md` and update `CLAUDE.md`

### Updating Best Practices

Run `/update-practices` periodically. It fetches URLs from `.claude/references/source-urls.md` and updates configuration to match current best practices. Safe to run repeatedly.

### Personal Settings

Create `.claude/settings.local.json` for personal overrides (git-ignored). This takes precedence over `.claude/settings.json`.

---

## Troubleshooting

- **Skill not triggering:** Check `user-invocable: true` in SKILL.md frontmatter
- **Agent not found:** Verify the file is in `.claude/agents/` with valid YAML frontmatter
- **Settings not applied:** Priority: CLI flags > settings.local.json > settings.json > global settings
- **Hooks not running:** Check hook event name and matcher in settings.json
