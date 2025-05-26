@file:Suppress("unused")

package com.dayanruben.maplibrecompose.demoapp.docs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dayanruben.maplibrecompose.compose.MaplibreMap
import com.dayanruben.maplibrecompose.compose.rememberCameraState
import com.dayanruben.maplibrecompose.compose.rememberStyleState
import com.dayanruben.maplibrecompose.core.OrnamentSettings
import com.dayanruben.maplibrecompose.material3.controls.CompassButton
import com.dayanruben.maplibrecompose.material3.controls.DisappearingCompassButton
import com.dayanruben.maplibrecompose.material3.controls.DisappearingScaleBar
import com.dayanruben.maplibrecompose.material3.controls.ExpandingAttributionButton
import com.dayanruben.maplibrecompose.material3.controls.ScaleBar

@Composable
fun Material3() {
  // -8<- [start:controls]
  val cameraState = rememberCameraState()
  val styleState = rememberStyleState()

  Box(Modifier.fillMaxSize()) {
    MaplibreMap(
      cameraState = cameraState,
      styleState = styleState,
      ornamentSettings = OrnamentSettings.AllDisabled,
    )

    Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
      ScaleBar(cameraState.metersPerDpAtTarget, modifier = Modifier.align(Alignment.TopStart))
      CompassButton(cameraState, modifier = Modifier.align(Alignment.TopEnd))
      ExpandingAttributionButton(
        cameraState = cameraState,
        styleState = styleState,
        modifier = Modifier.align(Alignment.BottomEnd),
        contentAlignment = Alignment.BottomEnd,
      )
    }
  }
  // -8<- [end:controls]

  // -8<- [start:disappearing-controls]
  Box(modifier = Modifier.fillMaxSize()) {
    MaplibreMap(
      cameraState = cameraState,
      styleState = styleState,
      ornamentSettings = OrnamentSettings.AllDisabled,
    )

    Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
      DisappearingScaleBar(
        metersPerDp = cameraState.metersPerDpAtTarget,
        zoom = cameraState.position.zoom,
        modifier = Modifier.align(Alignment.TopStart),
      ) // (1)!
      DisappearingCompassButton(cameraState, modifier = Modifier.align(Alignment.TopEnd)) // (2)!
      ExpandingAttributionButton(
        cameraState = cameraState,
        styleState = styleState,
        modifier = Modifier.align(Alignment.BottomEnd),
        contentAlignment = Alignment.BottomEnd,
      )
    }
  }
  // -8<- [end:disappearing-controls]
}
