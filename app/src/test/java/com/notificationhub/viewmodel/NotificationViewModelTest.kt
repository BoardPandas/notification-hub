package com.notificationhub.viewmodel

import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.concurrent.TimeUnit

class NotificationViewModelTest {

    @Test
    fun `sevenDaysAgoTimestamp returns value roughly 7 days before now`() {
        val now = System.currentTimeMillis()
        val result = NotificationViewModel.sevenDaysAgoTimestamp(now)
        val expected = now - TimeUnit.DAYS.toMillis(7)
        assertTrue(result == expected)
    }

    @Test
    fun `sevenDaysAgoTimestamp is never negative for reasonable input`() {
        val now = System.currentTimeMillis()
        val result = NotificationViewModel.sevenDaysAgoTimestamp(now)
        assertTrue("Timestamp should be positive but was $result", result > 0)
    }

    @Test
    fun `sevenDaysAgoTimestamp with custom now value`() {
        val fixedNow = TimeUnit.DAYS.toMillis(10) // 10 days in millis
        val result = NotificationViewModel.sevenDaysAgoTimestamp(fixedNow)
        val expected = TimeUnit.DAYS.toMillis(3) // 10 - 7 = 3 days in millis
        assertTrue(result == expected)
    }
}
