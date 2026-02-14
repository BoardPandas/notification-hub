package com.notificationhub.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.notificationhub.NotificationHubApp
import com.notificationhub.data.NotificationEntity
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

class NotificationViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = (application as NotificationHubApp).database.notificationDao()

    val notifications: Flow<List<NotificationEntity>> = dao.getNotificationsSince(
        since = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7)
    )
}
