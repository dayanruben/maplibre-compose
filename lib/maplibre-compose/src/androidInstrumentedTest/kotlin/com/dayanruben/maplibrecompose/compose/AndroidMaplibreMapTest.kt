package com.dayanruben.maplibrecompose.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

class AndroidMaplibreMapTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testContentBlockExecution() {
        var contentExecuted by mutableStateOf(false)

        composeTestRule.setContent {
            MaplibreMap(
                styleUri = "mapbox://styles/mapbox/streets-v11",
            ) {
                contentExecuted = true
            }
        }

        composeTestRule.waitForIdle()
        assertTrue(contentExecuted, "Content block should be executed")
    }
}
