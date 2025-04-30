package com.dayanruben.maplibrecompose.demoapp.demos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dayanruben.maplibrecompose.compose.MaplibreMap
import com.dayanruben.maplibrecompose.compose.rememberCameraState
import com.dayanruben.maplibrecompose.compose.rememberStyleState
import com.dayanruben.maplibrecompose.core.CameraPosition
import com.dayanruben.maplibrecompose.demoapp.DEFAULT_STYLE
import com.dayanruben.maplibrecompose.demoapp.Demo
import com.dayanruben.maplibrecompose.demoapp.DemoAppBar
import com.dayanruben.maplibrecompose.demoapp.DemoMapControls
import com.dayanruben.maplibrecompose.demoapp.DemoOrnamentSettings
import com.dayanruben.spatialk.geojson.Position

private val PORTLAND = Position(latitude = 45.521, longitude = -122.675)

object EdgeToEdgeDemo : Demo {
  override val name = "Edge-to-edge"
  override val description =
    "Fill the entire screen with a map and pad ornaments to position them correctly."

  @Composable
  override fun Component(navigateUp: () -> Unit) {
    val cameraState = rememberCameraState(CameraPosition(target = PORTLAND, zoom = 13.0))
    val styleState = rememberStyleState()

    Scaffold(topBar = { DemoAppBar(this, navigateUp, alpha = 0.5f) }) { padding ->
      Box(modifier = Modifier.consumeWindowInsets(WindowInsets.safeContent).fillMaxSize()) {
        MaplibreMap(
          styleUri = DEFAULT_STYLE,
          cameraState = cameraState,
          styleState = styleState,
          ornamentSettings = DemoOrnamentSettings(padding),
        )
        DemoMapControls(cameraState, styleState, modifier = Modifier.padding(padding))
      }
    }
  }
}
