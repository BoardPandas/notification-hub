package com.notificationhub

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.notificationhub.ui.screens.NotificationListScreen
import com.notificationhub.ui.theme.NotificationHubTheme

class MainActivity : ComponentActivity() {

    private var hasPermission by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val themePreferences = (application as NotificationHubApp).themePreferences

        setContent {
            val themeMode by themePreferences.themeMode.collectAsState()

            NotificationHubTheme(themeMode = themeMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotificationListScreen(
                        hasPermission = hasPermission,
                        onRequestPermission = { openNotificationListenerSettings() },
                        themePreferences = themePreferences
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        hasPermission = isNotificationListenerEnabled()
    }

    private fun isNotificationListenerEnabled(): Boolean {
        val componentName = ComponentName(this, com.notificationhub.service.NotificationCaptureService::class.java)
        val enabledListeners = Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
        return enabledListeners?.contains(componentName.flattenToString()) == true
    }

    private fun openNotificationListenerSettings() {
        startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
    }
}
