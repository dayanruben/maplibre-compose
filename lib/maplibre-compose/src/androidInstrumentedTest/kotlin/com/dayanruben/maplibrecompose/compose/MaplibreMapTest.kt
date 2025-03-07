package com.dayanruben.maplibrecompose.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MaplibreMapTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMaplibreMapDisposal() {
        var isVisible by mutableStateOf(true)

        composeTestRule.setContent {
            Box {
                if (isVisible) {
                    MaplibreMap(
                        modifier = Modifier,
                        styleUri = "mapbox://styles/mapbox/streets-v11"
                    )
                }
            }
        }

        // Show map
        composeTestRule.waitForIdle()

        // Hide map to trigger disposal
        isVisible = false
        composeTestRule.waitForIdle()

        // Map and its resources should be properly disposed here
        // Note: We can't directly verify the cleanup as it's internal,
        // but at least we ensure no crashes during disposal
    }
}
