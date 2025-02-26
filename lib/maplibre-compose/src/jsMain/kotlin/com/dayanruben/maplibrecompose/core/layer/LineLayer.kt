package com.dayanruben.maplibrecompose.core.layer

import com.dayanruben.maplibrecompose.core.source.Source
import com.dayanruben.maplibrecompose.expressions.ast.CompiledExpression
import com.dayanruben.maplibrecompose.expressions.value.BooleanValue
import com.dayanruben.maplibrecompose.expressions.value.ColorValue
import com.dayanruben.maplibrecompose.expressions.value.DpOffsetValue
import com.dayanruben.maplibrecompose.expressions.value.DpValue
import com.dayanruben.maplibrecompose.expressions.value.FloatValue
import com.dayanruben.maplibrecompose.expressions.value.ImageValue
import com.dayanruben.maplibrecompose.expressions.value.LineCap
import com.dayanruben.maplibrecompose.expressions.value.LineJoin
import com.dayanruben.maplibrecompose.expressions.value.TranslateAnchor
import com.dayanruben.maplibrecompose.expressions.value.VectorValue

internal actual class LineLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setLineCap(cap: CompiledExpression<LineCap>) {
    TODO()
  }

  actual fun setLineJoin(join: CompiledExpression<LineJoin>) {
    TODO()
  }

  actual fun setLineMiterLimit(miterLimit: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setLineRoundLimit(roundLimit: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setLineSortKey(sortKey: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setLineOpacity(opacity: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setLineColor(color: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setLineTranslate(translate: CompiledExpression<DpOffsetValue>) {
    TODO()
  }

  actual fun setLineTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    TODO()
  }

  actual fun setLineWidth(width: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setLineGapWidth(gapWidth: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setLineOffset(offset: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setLineBlur(blur: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setLineDasharray(dasharray: CompiledExpression<VectorValue<Number>>) {
    TODO()
  }

  actual fun setLinePattern(pattern: CompiledExpression<ImageValue>) {
    TODO()
  }

  actual fun setLineGradient(gradient: CompiledExpression<ColorValue>) {
    TODO()
  }
}
