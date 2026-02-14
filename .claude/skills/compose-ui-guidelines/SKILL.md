---
name: compose-ui-guidelines
description: Jetpack Compose UI patterns with Material Design 3, theming, and state management
---

# Compose UI Guidelines

Principles for building UI with Jetpack Compose and Material Design 3.

## When to Use This Skill

- Building Compose screens and components
- Working with Material Design 3 theming
- Managing UI state
- Creating lists with LazyColumn
- Handling loading, error, and empty states

## MVP Principles for Compose

### Always Follow
- **Simple composables** (small, focused functions)
- **MaterialTheme** for all colors, typography, shapes
- **LazyColumn** for any list (never Column with forEach)
- **remember + mutableStateOf** for local UI state
- **collectAsState()** for Flow observation
- **Modifier parameter** on every public composable

### Never Use (Unless Justified)
- Complex state management libraries
- Custom themes beyond MaterialTheme color scheme
- Higher-order composable patterns
- Complex animation frameworks (until needed)
- Custom layout implementations (until needed)

## Component Structure

```kotlin
@Composable
fun MyComponent(
    data: DataType,
    modifier: Modifier = Modifier  // Always accept modifier
) {
    // 1. State
    var expanded by remember { mutableStateOf(false) }

    // 2. Layout
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = data.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
```

## Theming

### Use MaterialTheme Colors
```kotlin
// GOOD - uses theme
Text(color = MaterialTheme.colorScheme.primary)
Surface(color = MaterialTheme.colorScheme.surface)

// BAD - hardcoded colors
Text(color = Color(0xFF457B9D))
```

### Brand Colors (This App)
| Role | Light | Dark |
|------|-------|------|
| Primary | Cerulean (#457B9D) | Frosted Blue (#A8DADC) |
| Background | Honeydew (#F1FAEE) | Oxford Navy (#1D3557) |
| Tertiary/Error | Punch Red (#E63946) | Light Punch Red |

## State Management

### Prefer (In This Order)
1. **Local state** - `remember { mutableStateOf(...) }`
2. **ViewModel state** - `viewModel.data.collectAsState()`
3. **Hoisted state** - Pass state up via callbacks

### Pattern
```kotlin
@Composable
fun MyScreen(viewModel: MyViewModel = viewModel()) {
    val items by viewModel.items.collectAsState(initial = emptyList())

    if (items.isEmpty()) {
        EmptyState()
    } else {
        ItemList(items = items)
    }
}
```

## Lists

### Always Use LazyColumn
```kotlin
LazyColumn(
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    items(items = list, key = { it.id }) { item ->
        ItemCard(item = item)
    }
}
```

### Grouped Lists
```kotlin
grouped.forEach { (header, items) ->
    item(key = header) {
        Text(text = header, style = MaterialTheme.typography.titleLarge)
    }
    items(items = items, key = { it.id }) { item ->
        ItemCard(item = item)
    }
}
```

## Loading, Error, Empty States

Every screen that fetches data should handle:
1. **Loading** - CircularProgressIndicator or skeleton
2. **Error** - Error message with retry
3. **Empty** - Helpful empty state message
4. **Success** - Render data

## Scaffold Pattern

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = { Text("Title") },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Content(modifier = Modifier.padding(innerPadding))
    }
}
```

## Checklist Before Committing

- [ ] All composables accept Modifier parameter
- [ ] Using MaterialTheme for colors and typography
- [ ] LazyColumn for lists (not Column + forEach)
- [ ] Loading, error, and empty states handled
- [ ] Light and dark theme both look correct
- [ ] No hardcoded colors or dimensions
- [ ] Composables under 200 lines
- [ ] Preview functions for key components
