package com.notificationhub

import android.app.Application
import com.notificationhub.data.NotificationDatabase
import com.notificationhub.data.ThemePreferences

class NotificationHubApp : Application() {

    val database: NotificationDatabase by lazy {
        NotificationDatabase.getInstance(this)
    }

    val themePreferences: ThemePreferences by lazy {
        ThemePreferences(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: NotificationHubApp
            private set
    }
}
