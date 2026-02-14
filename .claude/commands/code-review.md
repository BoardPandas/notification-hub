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
- [ ] Proper lifecycle handling

### 2. MVP Principles
- [ ] No unjustified patterns (DI, repositories, use cases)
- [ ] Direct DAO access from ViewModels
- [ ] Functions under 200 lines
- [ ] Abstraction only after 3rd duplicate
- [ ] Building for current needs, not hypothetical scale

### 3. Production Quality
- [ ] Error handling on database and service operations
- [ ] Logging with context (Log.e with TAG)
- [ ] Input validation where applicable
- [ ] Null safety handled properly

### 4. Security
- [ ] No hardcoded secrets
- [ ] Permission checks at runtime
- [ ] No sensitive data in release logs
- [ ] Room database not exported

### 5. Testing
- [ ] Core logic tested
- [ ] Room DAO queries tested
- [ ] Happy path + error cases covered

### 6. Compose UI
- [ ] MaterialTheme for colors and typography
- [ ] Loading, error, empty states handled
- [ ] Light and dark theme both correct
- [ ] No hardcoded colors or dimensions

## Output Format

### Good Practices Found
- [Examples of well-written code]

### Concerns
1. **[Issue]**: [Description]
   - **Location**: [file:line]
   - **Suggestion**: [Fix]
   - **Priority**: [High/Medium/Low]

### Quick Wins
- [Easy improvements]

Remember: Simple, reliable code that works on the OnePlus 15. Ship it.
