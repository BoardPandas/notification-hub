---
name: code-review
description: Perform architecture review focused on Android/Kotlin production principles
allowed-tools:
  - Read
  - Glob
  - Grep
---

# Code Review - Android Architecture & Best Practices

Review code for adherence to Android/Kotlin project standards.

## Review Checklist

### 1. Android/Kotlin Quality
- [ ] Kotlin idioms (null safety, data classes, when, scope functions)
- [ ] Compose state management correct (remember, collectAsState)
- [ ] LazyColumn for lists (not Column + forEach)
- [ ] Background work on IO dispatcher
- [ ] Services clean up coroutine scopes

### 2. MVP Principles
- [ ] No unjustified patterns (DI, repositories, use cases)
- [ ] Direct DAO access from ViewModels
- [ ] Functions under 200 lines
- [ ] Abstraction only after 3rd duplicate

### 3. Production Quality
- [ ] Error handling on database and service operations
- [ ] Logging with context
- [ ] Null safety handled properly

### 4. Security
- [ ] No hardcoded secrets
- [ ] Permission checks at runtime
- [ ] No sensitive data in release logs

### 5. Compose UI
- [ ] MaterialTheme for colors and typography
- [ ] Loading, error, empty states handled
- [ ] Light and dark theme both correct

Remember: Simple, reliable code. Ship it.
