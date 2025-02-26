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

internal expect class LineLayer(id: String, source: Source) : FeatureLayer {
  override var sourceLayer: String

  override fun setFilter(filter: CompiledExpression<BooleanValue>)

  fun setLineCap(cap: CompiledExpression<LineCap>)

  fun setLineJoin(join: CompiledExpression<LineJoin>)

  fun setLineMiterLimit(miterLimit: CompiledExpression<FloatValue>)

  fun setLineRoundLimit(roundLimit: CompiledExpression<FloatValue>)

  fun setLineSortKey(sortKey: CompiledExpression<FloatValue>)

  fun setLineOpacity(opacity: CompiledExpression<FloatValue>)

  fun setLineColor(color: CompiledExpression<ColorValue>)

  fun setLineTranslate(translate: CompiledExpression<DpOffsetValue>)

  fun setLineTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>)

  fun setLineWidth(width: CompiledExpression<DpValue>)

  fun setLineGapWidth(gapWidth: CompiledExpression<DpValue>)

  fun setLineOffset(offset: CompiledExpression<DpValue>)

  fun setLineBlur(blur: CompiledExpression<DpValue>)

  fun setLineDasharray(dasharray: CompiledExpression<VectorValue<Number>>)

  fun setLinePattern(pattern: CompiledExpression<ImageValue>)

  fun setLineGradient(gradient: CompiledExpression<ColorValue>)
}
