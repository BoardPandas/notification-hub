---
name: android-dev-guidelines
description: Android/Kotlin development principles with Room, coroutines, and NotificationListenerService patterns
---

# Android Development Guidelines

Principles for Android development with Kotlin, targeting OnePlus 15 / OxygenOS 16.0.2.

## When to Use This Skill

- Writing Kotlin code for Android
- Working with Room database
- Implementing services (NotificationListenerService)
- Managing Android lifecycle
- Handling permissions
- Working with coroutines and Flow

## MVP Principles for Android

### Always Follow
- **Simple functions** over complex class hierarchies
- **Direct Room DAO access** from ViewModels (no repository pattern for MVP)
- **AndroidViewModel** for database access (provides Application context)
- **Kotlin coroutines** for async work (Dispatchers.IO for database/network)
- **Flow** for reactive data streams from Room
- **Single Activity** architecture with Compose

### Never Use (Unless Justified)
- Hilt/Dagger dependency injection (single-dev app doesn't need it)
- Repository pattern (direct DAO is fine for MVP)
- Use case / interactor classes
- Multi-module project structure
- Complex navigation libraries (until 3+ screens)
- Abstract base classes for ViewModels

## Room Database Patterns

### Entity Definition
```kotlin
@Entity(
    tableName = "items",
    indices = [Index(value = ["timestamp"])]
)
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val timestamp: Long
)
```

### DAO Pattern
```kotlin
@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ItemEntity)

    @Query("SELECT * FROM items ORDER BY timestamp DESC")
    fun getAll(): Flow<List<ItemEntity>>

    @Query("DELETE FROM items WHERE timestamp < :before")
    suspend fun deleteOlderThan(before: Long)
}
```

### Database Singleton
```kotlin
@Database(entities = [ItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app.db")
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
```

## Service Patterns

### NotificationListenerService
```kotlin
class MyService : NotificationListenerService() {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        sbn ?: return
        scope.launch {
            try {
                // Process notification
            } catch (e: Exception) {
                Log.e("MyService", "Failed to process", e)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}
```

## ViewModel Pattern

```kotlin
class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = (application as MyApp).database.myDao()

    val items: Flow<List<ItemEntity>> = dao.getAll()
}
```

## Error Handling

- Wrap all database operations in try/catch
- Use `Log.e(TAG, message, exception)` for error logging
- Use `SupervisorJob()` in coroutine scopes so one failure doesn't cancel siblings
- Handle null from Android APIs defensively

## Permission Handling

```kotlin
// Check NotificationListenerService permission
fun isNotificationListenerEnabled(context: Context): Boolean {
    val cn = ComponentName(context, MyService::class.java)
    val listeners = Settings.Secure.getString(
        context.contentResolver, "enabled_notification_listeners"
    )
    return listeners?.contains(cn.flattenToString()) == true
}
```

## Checklist Before Committing

- [ ] Using Kotlin idioms (null safety, data classes, when expressions)
- [ ] Database operations on IO dispatcher
- [ ] Services clean up coroutine scopes in onDestroy
- [ ] Error handling with try/catch + Log.e
- [ ] No hardcoded strings (use resources or constants)
- [ ] Functions under 200 lines
- [ ] Can explain to another dev in 30 seconds
