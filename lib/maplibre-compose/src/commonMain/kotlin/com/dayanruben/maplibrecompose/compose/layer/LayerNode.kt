package com.dayanruben.maplibrecompose.compose.layer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.Updater
import androidx.compose.runtime.key
import com.dayanruben.maplibrecompose.compose.FeaturesClickHandler
import com.dayanruben.maplibrecompose.compose.MaplibreComposable
import com.dayanruben.maplibrecompose.compose.engine.LayerNode
import com.dayanruben.maplibrecompose.compose.engine.MapNodeApplier
import com.dayanruben.maplibrecompose.core.layer.Layer

@Composable
@MaplibreComposable
internal fun <T : Layer> LayerNode(
  factory: () -> T,
  update: Updater<LayerNode<T>>.() -> Unit,
  onClick: FeaturesClickHandler?,
  onLongClick: FeaturesClickHandler?,
) {
  val anchor = LocalAnchor.current
  key(factory, anchor) {
    ComposeNode<LayerNode<T>, MapNodeApplier>(
      factory = { LayerNode(layer = factory(), anchor = anchor) },
      update = {
        update()
        set(onClick) { this.onClick = it }
        set(onLongClick) { this.onLongClick = it }
      },
    )
  }
}
