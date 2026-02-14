package com.notificationhub.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.notificationhub.NotificationHubApp
import com.notificationhub.data.NotificationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class NotificationViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = (application as NotificationHubApp).database.notificationDao()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _selectedApp = MutableStateFlow<String?>(null)
    val selectedApp: StateFlow<String?> = _selectedApp

    private val since = sevenDaysAgoTimestamp()

    val appNames: Flow<List<String>> = dao.getAppNamesSince(since)

    val notifications: Flow<List<NotificationEntity>> = combine(
        dao.getNotificationsSince(since),
        _searchQuery,
        _selectedApp
    ) { allNotifications, query, app ->
        var filtered = allNotifications
        if (app != null) {
            filtered = filtered.filter { it.appName == app }
        }
        if (query.isNotBlank()) {
            val q = query.lowercase()
            filtered = filtered.filter {
                it.title.lowercase().contains(q) ||
                    it.text.lowercase().contains(q) ||
                    it.appName.lowercase().contains(q)
            }
        }
        filtered
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun onAppFilterSelected(appName: String?) {
        _selectedApp.value = appName
    }

    fun deleteNotification(id: Long) {
        viewModelScope.launch {
            dao.deleteById(id)
        }
    }

    companion object {
        internal fun sevenDaysAgoTimestamp(now: Long = System.currentTimeMillis()): Long {
            return now - TimeUnit.DAYS.toMillis(7)
        }
    }
}
