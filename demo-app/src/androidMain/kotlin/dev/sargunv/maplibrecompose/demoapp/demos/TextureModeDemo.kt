package com.dayanruben.maplibrecompose.demoapp.demos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dayanruben.maplibrecompose.compose.MaplibreMap
import com.dayanruben.maplibrecompose.compose.rememberCameraState
import com.dayanruben.maplibrecompose.compose.rememberStyleState
import com.dayanruben.maplibrecompose.core.MapOptions
import com.dayanruben.maplibrecompose.demoapp.DEFAULT_STYLE
import com.dayanruben.maplibrecompose.demoapp.Demo
import com.dayanruben.maplibrecompose.demoapp.DemoMapControls
import com.dayanruben.maplibrecompose.demoapp.DemoOrnamentSettings
import com.dayanruben.maplibrecompose.demoapp.DemoScaffold
import org.jetbrains.compose.resources.ExperimentalResourceApi

object TextureModeDemo : Demo {
  override val name = "Texture Mode"
  override val description = "Render the map to a TextureView instead of a GLSurfaceView."

  @Composable
  @OptIn(ExperimentalResourceApi::class)
  override fun Component(navigateUp: () -> Unit) {
    DemoScaffold(this, navigateUp) {
      val cameraState = rememberCameraState()
      val styleState = rememberStyleState()

      Box(modifier = Modifier.Companion.fillMaxSize()) {
        MaplibreMap(
          styleUri = DEFAULT_STYLE,
          cameraState = cameraState,
          styleState = styleState,
          ornamentSettings = DemoOrnamentSettings(),
          platformOptions = MapOptions(textureMode = true),
        )
        DemoMapControls(cameraState, styleState)
      }
    }
  }
}
