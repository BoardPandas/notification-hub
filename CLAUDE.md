# Notification Hub

Android app that captures and stores all phone notifications for 7-day review. Sideloaded on OnePlus 15 / OxygenOS 16.0.2.

## Quick Reference

- See [README.md](README.md) for project overview
- Skills in [.claude/skills/](.claude/skills/)
- Agents in [.claude/agents/](.claude/agents/)

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose + Material Design 3
- **Database**: Room (SQLite)
- **Architecture**: ViewModel + Kotlin Flow
- **Build**: Gradle 8.11 + AGP 8.7
- **Min SDK**: 29 (Android 10)
- **Target SDK**: 35 (Android 15)
- **Target Device**: OnePlus 15, OxygenOS 16.0.2

## Key Commands

```bash
# Build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest

# Lint
./gradlew lint

# Install on device
adb install app/build/outputs/apk/debug/app-debug.apk
```

## Development Principles

1. **Simple > Complex** - Always favor the simpler approach
2. **YAGNI** - Don't build for hypothetical futures
3. **TDD Required** - Write tests first, always
4. **Ship Fast** - Working MVP beats perfect vaporware
5. **Production Quality** - Error handling, logging, validation are non-negotiable
6. **Rule of Three** - Don't extract until 3rd duplicate
7. **200 Lines** - Consider extracting functions at 200+ lines

## Brand Color Palette

| Name | Hex | Compose Variable |
|------|-----|-----------------|
| Punch Red | `#E63946` | `PunchRed` |
| Honeydew | `#F1FAEE` | `Honeydew` |
| Frosted Blue | `#A8DADC` | `FrostedBlue` |
| Cerulean | `#457B9D` | `Cerulean` |
| Oxford Navy | `#1D3557` | `OxfordNavy` |

Theme files: `app/src/main/java/com/notificationhub/ui/theme/`

## Project Structure

```
app/src/main/java/com/notificationhub/
├── NotificationHubApp.kt           # Application class
├── MainActivity.kt                 # Entry point
├── data/                           # Room database layer
│   ├── NotificationEntity.kt
│   ├── NotificationDao.kt
│   └── NotificationDatabase.kt
├── service/                        # Background services
│   └── NotificationCaptureService.kt
├── ui/                             # Jetpack Compose UI
│   ├── theme/                      # Color, Theme, Typography
│   ├── screens/                    # Full-screen composables
│   └── components/                 # Reusable UI components
└── viewmodel/                      # ViewModels
```

## Architecture Notes

- **NotificationListenerService** captures all notifications in background
- **Room** stores notifications with auto-cleanup of entries older than 7 days
- **Flow** provides reactive updates from DB to UI
- **Grouped by day**: Today, Yesterday, then date format
- Light/dark theme follows system setting via `isSystemInDarkTheme()`
- No network calls - everything is local/offline

## Android-Specific Guidelines

- Always use `remember` and `mutableStateOf` for Compose state
- Use `collectAsState()` to observe Flows in Compose
- Keep composables small and focused
- Use `LazyColumn` for lists (never `Column` with `forEach`)
- Test with both light and dark themes
- Handle permission state changes in `onResume()`

## Skills & Commands

- `/dev-docs [task]` - Create strategic implementation plan
- `/dev-docs-update` - Update plan with progress
- `/build-and-fix` - Run Gradle build and fix all errors
- `/code-review` - Architecture review against best practices

Core skills (auto-activate when relevant):
- `android-dev-guidelines` - Android/Kotlin development principles
- `compose-ui-guidelines` - Jetpack Compose UI patterns
- `production-principles` - Anti-over-engineering + production quality
- `security-practices` - Permissions, data safety
- `tdd-workflow` - Test-Driven Development workflow

## Environment

- No .env files needed (no API keys, no backend)
- All data stored locally in Room database
- Sideloaded via ADB or direct APK install

---

**Philosophy**: Working, simple, reliable code beats perfect vaporware. Ship it.
