package com.dayanruben.maplibrecompose.compose.source

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.dayanruben.maplibrecompose.compose.engine.LocalStyleNode
import com.dayanruben.maplibrecompose.core.source.Source

/**
 * Get the source with the given [id] from the base style specified via the `styleUri` parameter in
 * [MaplibreMap][com.dayanruben.maplibrecompose.compose.MaplibreMap].
 *
 * @throws IllegalStateException if the source does not exist
 */
@Composable
public fun getBaseSource(id: String): Source {
  val node = LocalStyleNode.current
  return remember(node, id) { node.sourceManager.getBaseSource(id) }
}
