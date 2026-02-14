package com.notificationhub.service

import com.notificationhub.data.NotificationEntity

internal fun shouldCapture(packageName: String, ownPackageName: String, title: String, text: String): Boolean {
    if (packageName == ownPackageName) return false
    if (title.isBlank() && text.isBlank()) return false
    return true
}

internal fun createEntity(
    packageName: String,
    appName: String,
    title: String,
    text: String,
    postTime: Long
): NotificationEntity {
    return NotificationEntity(
        packageName = packageName,
        appName = appName,
        title = title,
        text = text,
        timestamp = postTime
    )
}
