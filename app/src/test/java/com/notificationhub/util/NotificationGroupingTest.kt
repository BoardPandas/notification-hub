package com.notificationhub.util

import com.notificationhub.data.NotificationEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar

class NotificationGroupingTest {

    private fun entity(timestamp: Long, id: Long = 0) = NotificationEntity(
        id = id,
        packageName = "com.test",
        appName = "Test",
        title = "Title",
        text = "Text",
        timestamp = timestamp
    )

    private fun todayTimestamp(): Long {
        return System.currentTimeMillis()
    }

    private fun yesterdayTimestamp(): Long {
        return Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, -1)
        }.timeInMillis
    }

    private fun daysAgoTimestamp(days: Int): Long {
        return Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, -days)
        }.timeInMillis
    }

    @Test
    fun `empty list returns empty result`() {
        val result = groupByDay(emptyList())
        assertTrue(result.isEmpty())
    }

    @Test
    fun `single notification today is labeled Today`() {
        val result = groupByDay(listOf(entity(todayTimestamp())))
        assertEquals(1, result.size)
        assertEquals("Today", result[0].first)
        assertEquals(1, result[0].second.size)
    }

    @Test
    fun `notification yesterday is labeled Yesterday`() {
        val result = groupByDay(listOf(entity(yesterdayTimestamp())))
        assertEquals(1, result.size)
        assertEquals("Yesterday", result[0].first)
    }

    @Test
    fun `older notification gets formatted date label`() {
        val result = groupByDay(listOf(entity(daysAgoTimestamp(5))))
        assertEquals(1, result.size)
        val label = result[0].first
        // Should not be "Today" or "Yesterday"
        assertTrue("Expected formatted date but got: $label", label != "Today" && label != "Yesterday")
        // Should contain a comma (format is "EEEE, MMM d")
        assertTrue("Expected date format with comma but got: $label", label.contains(","))
    }

    @Test
    fun `multiple notifications same day are grouped together`() {
        val now = todayTimestamp()
        val notifications = listOf(
            entity(now, id = 1),
            entity(now - 1000, id = 2),
            entity(now - 2000, id = 3)
        )
        val result = groupByDay(notifications)
        assertEquals(1, result.size)
        assertEquals("Today", result[0].first)
        assertEquals(3, result[0].second.size)
    }

    @Test
    fun `notifications across multiple days are grouped correctly`() {
        val notifications = listOf(
            entity(todayTimestamp(), id = 1),
            entity(yesterdayTimestamp(), id = 2),
            entity(daysAgoTimestamp(3), id = 3)
        )
        val result = groupByDay(notifications)
        assertEquals(3, result.size)
        assertEquals("Today", result[0].first)
        assertEquals("Yesterday", result[1].first)
        // Third group is a formatted date
        assertTrue(result[2].first != "Today" && result[2].first != "Yesterday")
    }
}
