package com.notificationhub.service

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class NotificationExtractorTest {

    @Test
    fun `shouldCapture skips own package name`() {
        val result = shouldCapture(
            packageName = "com.notificationhub",
            ownPackageName = "com.notificationhub",
            title = "Title",
            text = "Text"
        )
        assertFalse(result)
    }

    @Test
    fun `shouldCapture skips when both title and text are blank`() {
        val result = shouldCapture(
            packageName = "com.other",
            ownPackageName = "com.notificationhub",
            title = "",
            text = "   "
        )
        assertFalse(result)
    }

    @Test
    fun `shouldCapture accepts when title is present but text is blank`() {
        val result = shouldCapture(
            packageName = "com.other",
            ownPackageName = "com.notificationhub",
            title = "Title",
            text = ""
        )
        assertTrue(result)
    }

    @Test
    fun `shouldCapture accepts when text is present but title is blank`() {
        val result = shouldCapture(
            packageName = "com.other",
            ownPackageName = "com.notificationhub",
            title = "",
            text = "Some text"
        )
        assertTrue(result)
    }

    @Test
    fun `shouldCapture accepts normal notification`() {
        val result = shouldCapture(
            packageName = "com.whatsapp",
            ownPackageName = "com.notificationhub",
            title = "New message",
            text = "Hello!"
        )
        assertTrue(result)
    }

    @Test
    fun `createEntity maps fields correctly`() {
        val entity = createEntity(
            packageName = "com.whatsapp",
            appName = "WhatsApp",
            title = "New message",
            text = "Hello!",
            postTime = 1234567890L
        )
        assertEquals("com.whatsapp", entity.packageName)
        assertEquals("WhatsApp", entity.appName)
        assertEquals("New message", entity.title)
        assertEquals("Hello!", entity.text)
        assertEquals(1234567890L, entity.timestamp)
        assertEquals(0L, entity.id)
    }
}
