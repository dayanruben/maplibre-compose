package com.dayanruben.maplibrecompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import com.dayanruben.maplibrecompose.core.MapOptions
import com.dayanruben.maplibrecompose.core.MaplibreMap
import com.dayanruben.maplibrecompose.core.SafeStyle

@Composable
internal expect fun ComposableMapView(
  modifier: Modifier,
  styleUri: String,
  rememberedStyle: SafeStyle?,
  update: (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
  platformOptions: MapOptions,
)
