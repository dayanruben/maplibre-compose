package com.dayanruben.maplibrecompose.core.layer

import cocoapods.MapLibre.MLNLineStyleLayer
import com.dayanruben.maplibrecompose.core.source.Source
import com.dayanruben.maplibrecompose.core.util.toNSExpression
import com.dayanruben.maplibrecompose.core.util.toNSPredicate
import com.dayanruben.maplibrecompose.expressions.ast.CompiledExpression
import com.dayanruben.maplibrecompose.expressions.ast.NullLiteral
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

  override val impl = MLNLineStyleLayer(id, source.impl)

  actual override var sourceLayer: String
    get() = impl.sourceLayerIdentifier!!
    set(value) {
      impl.sourceLayerIdentifier = value
    }

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    impl.predicate = filter.toNSPredicate()
  }

  actual fun setLineCap(cap: CompiledExpression<LineCap>) {
    impl.lineCap = cap.toNSExpression()
  }

  actual fun setLineJoin(join: CompiledExpression<LineJoin>) {
    impl.lineJoin = join.toNSExpression()
  }

  actual fun setLineMiterLimit(miterLimit: CompiledExpression<FloatValue>) {
    impl.lineMiterLimit = miterLimit.toNSExpression()
  }

  actual fun setLineRoundLimit(roundLimit: CompiledExpression<FloatValue>) {
    impl.lineRoundLimit = roundLimit.toNSExpression()
  }

  actual fun setLineSortKey(sortKey: CompiledExpression<FloatValue>) {
    impl.lineSortKey = sortKey.toNSExpression()
  }

  actual fun setLineOpacity(opacity: CompiledExpression<FloatValue>) {
    impl.lineOpacity = opacity.toNSExpression()
  }

  actual fun setLineColor(color: CompiledExpression<ColorValue>) {
    impl.lineColor = color.toNSExpression()
  }

  actual fun setLineTranslate(translate: CompiledExpression<DpOffsetValue>) {
    impl.lineTranslation = translate.toNSExpression()
  }

  actual fun setLineTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    impl.lineTranslationAnchor = translateAnchor.toNSExpression()
  }

  actual fun setLineWidth(width: CompiledExpression<DpValue>) {
    impl.lineWidth = width.toNSExpression()
  }

  actual fun setLineGapWidth(gapWidth: CompiledExpression<DpValue>) {
    impl.lineGapWidth = gapWidth.toNSExpression()
  }

  actual fun setLineOffset(offset: CompiledExpression<DpValue>) {
    impl.lineOffset = offset.toNSExpression()
  }

  actual fun setLineBlur(blur: CompiledExpression<DpValue>) {
    impl.lineBlur = blur.toNSExpression()
  }

  actual fun setLineDasharray(dasharray: CompiledExpression<VectorValue<Number>>) {
    impl.lineDashPattern = dasharray.toNSExpression()
  }

  actual fun setLinePattern(pattern: CompiledExpression<ImageValue>) {
    // TODO: figure out how to unset a pattern in iOS
    if (pattern != NullLiteral) {
      impl.linePattern = pattern.toNSExpression()
    }
  }

  actual fun setLineGradient(gradient: CompiledExpression<ColorValue>) {
    impl.lineGradient = gradient.toNSExpression()
  }
}
