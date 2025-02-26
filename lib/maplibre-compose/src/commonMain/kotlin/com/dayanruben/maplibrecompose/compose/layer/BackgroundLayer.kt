package com.dayanruben.maplibrecompose.compose.layer

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.dayanruben.maplibrecompose.compose.MaplibreComposable
import com.dayanruben.maplibrecompose.core.layer.BackgroundLayer
import com.dayanruben.maplibrecompose.expressions.ast.Expression
import com.dayanruben.maplibrecompose.expressions.dsl.const
import com.dayanruben.maplibrecompose.expressions.dsl.nil
import com.dayanruben.maplibrecompose.expressions.value.ColorValue
import com.dayanruben.maplibrecompose.expressions.value.FloatValue
import com.dayanruben.maplibrecompose.expressions.value.ImageValue

/**
 * The background layer just draws the map background, by default, plain black.
 *
 * @param id Unique layer name.
 * @param minZoom The minimum zoom level for the layer. At zoom levels less than this, the layer
 *   will be hidden. A value in the range of `[0..24]`.
 * @param maxZoom The maximum zoom level for the layer. At zoom levels equal to or greater than
 *   this, the layer will be hidden. A value in the range of `[0..24]`.
 * @param visible Whether the layer should be displayed.
 * @param opacity Background opacity. A value in range `[0..1]`.
 * @param color Background color.
 *
 *   Ignored if [pattern] is specified.
 *
 * @param pattern Image to use for drawing image fills. For seamless patterns, image width and
 *   height must be a factor of two (2, 4, 8, ..., 512). Note that zoom-dependent expressions will
 *   be evaluated only at integer zoom levels.
 */
@Composable
@MaplibreComposable
public fun BackgroundLayer(
  id: String,
  minZoom: Float = 0.0f,
  maxZoom: Float = 24.0f,
  visible: Boolean = true,
  opacity: Expression<FloatValue> = const(1f),
  color: Expression<ColorValue> = const(Color.Black),
  pattern: Expression<ImageValue> = nil(),
) {
  val compile = rememberPropertyCompiler()

  val compiledOpacity = compile(opacity)
  val compiledColor = compile(color)
  val compiledPattern = compile(pattern)

  LayerNode(
    factory = { BackgroundLayer(id = id) },
    update = {
      set(minZoom) { layer.minZoom = it }
      set(maxZoom) { layer.maxZoom = it }
      set(visible) { layer.visible = it }
      set(compiledColor) { layer.setBackgroundColor(it) }
      set(compiledPattern) { layer.setBackgroundPattern(it) }
      set(compiledOpacity) { layer.setBackgroundOpacity(it) }
    },
    onClick = null,
    onLongClick = null,
  )
}
