# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keep @androidx.room.Dao class *
-dontwarn androidx.room.paging.**

# Room generated code
-keep class * implements androidx.room.RoomDatabase$Callback { *; }
-keep class * extends androidx.room.RoomOpenHelper$Delegate { *; }

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.coroutines.** { volatile <fields>; }
-dontwarn kotlinx.coroutines.flow.**

# Kotlin
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**

# Compose
-dontwarn androidx.compose.**

# NotificationListenerService
-keep class com.notificationhub.service.NotificationCaptureService { *; }
