package com.notificationhub.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Test

class NotificationEntityTest {

    @Test
    fun `default id is 0`() {
        val entity = NotificationEntity(
            packageName = "com.test",
            appName = "Test",
            title = "Title",
            text = "Text",
            timestamp = 1000L
        )
        assertEquals(0L, entity.id)
    }

    @Test
    fun `default iconUri is null`() {
        val entity = NotificationEntity(
            packageName = "com.test",
            appName = "Test",
            title = "Title",
            text = "Text",
            timestamp = 1000L
        )
        assertNull(entity.iconUri)
    }

    @Test
    fun `data class equality works`() {
        val entity1 = NotificationEntity(
            id = 1,
            packageName = "com.test",
            appName = "Test",
            title = "Title",
            text = "Text",
            timestamp = 1000L
        )
        val entity2 = entity1.copy()
        assertEquals(entity1, entity2)
    }

    @Test
    fun `data class copy with changes`() {
        val original = NotificationEntity(
            id = 1,
            packageName = "com.test",
            appName = "Test",
            title = "Title",
            text = "Text",
            timestamp = 1000L
        )
        val modified = original.copy(title = "New Title")
        assertNotEquals(original, modified)
        assertEquals("New Title", modified.title)
        assertEquals(original.packageName, modified.packageName)
    }
}
