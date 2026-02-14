package com.notificationhub.util

import com.notificationhub.data.NotificationEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

internal fun groupByDay(notifications: List<NotificationEntity>): List<Pair<String, List<NotificationEntity>>> {
    val calendar = Calendar.getInstance()
    val today = Calendar.getInstance()
    val yesterday = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }
    val dateFormat = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())

    return notifications
        .groupBy { notification ->
            calendar.timeInMillis = notification.timestamp
            val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
            val year = calendar.get(Calendar.YEAR)

            when {
                dayOfYear == today.get(Calendar.DAY_OF_YEAR) && year == today.get(Calendar.YEAR) -> "Today"
                dayOfYear == yesterday.get(Calendar.DAY_OF_YEAR) && year == yesterday.get(Calendar.YEAR) -> "Yesterday"
                else -> dateFormat.format(Date(notification.timestamp))
            }
        }
        .toList()
}
