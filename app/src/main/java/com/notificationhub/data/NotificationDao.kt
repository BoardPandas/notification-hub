package com.notificationhub.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: NotificationEntity)

    @Query("SELECT * FROM notifications WHERE timestamp >= :since ORDER BY timestamp DESC")
    fun getNotificationsSince(since: Long): Flow<List<NotificationEntity>>

    @Query("DELETE FROM notifications WHERE timestamp < :before")
    suspend fun deleteOlderThan(before: Long)

    @Query("DELETE FROM notifications WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT DISTINCT appName FROM notifications WHERE timestamp >= :since ORDER BY appName ASC")
    fun getAppNamesSince(since: Long): Flow<List<String>>

    @Query("SELECT COUNT(*) FROM notifications")
    suspend fun count(): Int
}
