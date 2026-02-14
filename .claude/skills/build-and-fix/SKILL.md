---
name: build-and-fix
description: Run Gradle build and systematically fix all Kotlin/Android compilation errors
allowed-tools:
  - Read
  - Edit
  - Bash
  - Glob
  - Grep
---

# Build and Fix - Android Error Resolution

Run the Gradle build and systematically fix all compilation errors.

## Instructions

### Step 1: Run Build

```bash
./gradlew assembleDebug 2>&1
```

### Step 2: Analyze Errors
- Capture all error output
- Group by type: Gradle config > Kotlin syntax > imports > Compose > Room > lint
- Identify root causes vs symptoms

### Step 3: Fix Systematically

1. **Gradle Config** - Fix build.gradle.kts, version catalogs, plugin issues
2. **Import Errors** - Fix missing or incorrect imports
3. **Kotlin Errors** - Null safety, type mismatches, missing overrides
4. **Compose Errors** - Missing @Composable, state issues, modifier problems
5. **Room Errors** - Entity annotations, DAO return types, migration issues
6. **Lint/Warnings** - Fix if quick, otherwise document

### Step 4: Verify
After each fix, re-run `./gradlew assembleDebug 2>&1`

### Step 5: Final Verification
```bash
./gradlew assembleDebug 2>&1
./gradlew test 2>&1
./gradlew lint 2>&1
```

## Guidelines

- Fix with simplest solution
- Don't refactor during error fixes
- Document any shortcuts taken
- Fix errors, don't suppress them

Remember: Working code beats perfect types. Ship it.
