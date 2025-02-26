package com.dayanruben.maplibrecompose.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.toSize

@Composable
internal fun MeasuredBox(
  modifier: Modifier,
  contents: @Composable (x: Dp, y: Dp, width: Dp, height: Dp) -> Unit,
) {
  val density = LocalDensity.current
  var layoutCoordinates by remember { mutableStateOf<LayoutCoordinates?>(null) }
  Box(modifier = modifier.onGloballyPositioned { layoutCoordinates = it }) {
    layoutCoordinates?.let { layoutCoordinates ->
      with(density) {
        val size = layoutCoordinates.size.toSize().toDpSize()
        val pos = layoutCoordinates.positionInParent()
        val x = pos.x.toDp()
        val y = pos.y.toDp()
        if (size.isSpecified) {
          contents(x, y, size.width, size.height)
        }
      }
    }
  }
}
