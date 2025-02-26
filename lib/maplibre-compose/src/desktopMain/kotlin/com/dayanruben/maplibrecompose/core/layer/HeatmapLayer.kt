package com.dayanruben.maplibrecompose.core.layer

import com.dayanruben.maplibrecompose.core.source.Source
import com.dayanruben.maplibrecompose.expressions.ast.CompiledExpression
import com.dayanruben.maplibrecompose.expressions.value.BooleanValue
import com.dayanruben.maplibrecompose.expressions.value.ColorValue
import com.dayanruben.maplibrecompose.expressions.value.DpValue
import com.dayanruben.maplibrecompose.expressions.value.FloatValue

internal actual class HeatmapLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {
  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setHeatmapRadius(radius: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setHeatmapWeight(weight: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setHeatmapIntensity(intensity: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setHeatmapColor(color: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setHeatmapOpacity(opacity: CompiledExpression<FloatValue>) {
    TODO()
  }
}
