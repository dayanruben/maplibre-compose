package com.dayanruben.maplibrecompose.compose.engine

import com.dayanruben.maplibrecompose.compose.FeaturesClickHandler
import com.dayanruben.maplibrecompose.compose.layer.Anchor
import com.dayanruben.maplibrecompose.core.layer.Layer

internal class LayerNode<T : Layer>(val layer: T, val anchor: Anchor) : MapNode() {
  internal var added: Boolean = false

  override fun allowsChild(node: MapNode) = false

  internal var onClick: FeaturesClickHandler? = null
  internal var onLongClick: FeaturesClickHandler? = null

  override fun toString(): String {
    return "LayerNode(layer=${layer.id}, anchor=$anchor, added=$added)"
  }
}
