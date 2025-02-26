package com.dayanruben.maplibrecompose.core.layer

import com.dayanruben.maplibrecompose.core.source.Source
import com.dayanruben.maplibrecompose.expressions.ast.CompiledExpression
import com.dayanruben.maplibrecompose.expressions.value.BooleanValue
import com.dayanruben.maplibrecompose.expressions.value.ColorValue
import com.dayanruben.maplibrecompose.expressions.value.DpOffsetValue
import com.dayanruben.maplibrecompose.expressions.value.DpPaddingValue
import com.dayanruben.maplibrecompose.expressions.value.DpValue
import com.dayanruben.maplibrecompose.expressions.value.FloatOffsetValue
import com.dayanruben.maplibrecompose.expressions.value.FloatValue
import com.dayanruben.maplibrecompose.expressions.value.FormattedValue
import com.dayanruben.maplibrecompose.expressions.value.IconPitchAlignment
import com.dayanruben.maplibrecompose.expressions.value.IconRotationAlignment
import com.dayanruben.maplibrecompose.expressions.value.IconTextFit
import com.dayanruben.maplibrecompose.expressions.value.ImageValue
import com.dayanruben.maplibrecompose.expressions.value.ListValue
import com.dayanruben.maplibrecompose.expressions.value.StringValue
import com.dayanruben.maplibrecompose.expressions.value.SymbolAnchor
import com.dayanruben.maplibrecompose.expressions.value.SymbolOverlap
import com.dayanruben.maplibrecompose.expressions.value.SymbolPlacement
import com.dayanruben.maplibrecompose.expressions.value.SymbolZOrder
import com.dayanruben.maplibrecompose.expressions.value.TextJustify
import com.dayanruben.maplibrecompose.expressions.value.TextPitchAlignment
import com.dayanruben.maplibrecompose.expressions.value.TextRotationAlignment
import com.dayanruben.maplibrecompose.expressions.value.TextTransform
import com.dayanruben.maplibrecompose.expressions.value.TextVariableAnchorOffsetValue
import com.dayanruben.maplibrecompose.expressions.value.TextWritingMode
import com.dayanruben.maplibrecompose.expressions.value.TranslateAnchor

internal actual class SymbolLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {
  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setSymbolPlacement(placement: CompiledExpression<SymbolPlacement>) {
    TODO()
  }

  actual fun setSymbolSpacing(spacing: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setSymbolAvoidEdges(avoidEdges: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setSymbolSortKey(sortKey: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setSymbolZOrder(zOrder: CompiledExpression<SymbolZOrder>) {
    TODO()
  }

  actual fun setIconAllowOverlap(allowOverlap: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setIconOverlap(overlap: CompiledExpression<StringValue>) {
    TODO()
  }

  actual fun setIconIgnorePlacement(ignorePlacement: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setIconOptional(optional: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setIconRotationAlignment(
    rotationAlignment: CompiledExpression<IconRotationAlignment>
  ) {
    TODO()
  }

  actual fun setIconSize(size: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setIconTextFit(textFit: CompiledExpression<IconTextFit>) {
    TODO()
  }

  actual fun setIconTextFitPadding(textFitPadding: CompiledExpression<DpPaddingValue>) {
    TODO()
  }

  actual fun setIconImage(image: CompiledExpression<ImageValue>) {
    TODO()
  }

  actual fun setIconRotate(rotate: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setIconPadding(padding: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setIconKeepUpright(keepUpright: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setIconOffset(offset: CompiledExpression<DpOffsetValue>) {
    TODO()
  }

  actual fun setIconAnchor(anchor: CompiledExpression<SymbolAnchor>) {
    TODO()
  }

  actual fun setIconPitchAlignment(pitchAlignment: CompiledExpression<IconPitchAlignment>) {
    TODO()
  }

  actual fun setIconOpacity(opacity: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setIconColor(color: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setIconHaloColor(haloColor: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setIconHaloWidth(haloWidth: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setIconHaloBlur(haloBlur: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setIconTranslate(translate: CompiledExpression<DpOffsetValue>) {
    TODO()
  }

  actual fun setIconTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    TODO()
  }

  actual fun setTextPitchAlignment(pitchAlignment: CompiledExpression<TextPitchAlignment>) {
    TODO()
  }

  actual fun setTextRotationAlignment(
    rotationAlignment: CompiledExpression<TextRotationAlignment>
  ) {
    TODO()
  }

  actual fun setTextField(field: CompiledExpression<FormattedValue>) {
    TODO()
  }

  actual fun setTextFont(font: CompiledExpression<ListValue<StringValue>>) {
    TODO()
  }

  actual fun setTextSize(size: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setTextMaxWidth(maxWidth: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setTextLineHeight(lineHeight: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setTextLetterSpacing(letterSpacing: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setTextJustify(justify: CompiledExpression<TextJustify>) {
    TODO()
  }

  actual fun setTextRadialOffset(radialOffset: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setTextVariableAnchor(variableAnchor: CompiledExpression<ListValue<SymbolAnchor>>) {
    TODO()
  }

  actual fun setTextVariableAnchorOffset(
    variableAnchorOffset: CompiledExpression<TextVariableAnchorOffsetValue>
  ) {
    TODO()
  }

  actual fun setTextAnchor(anchor: CompiledExpression<SymbolAnchor>) {
    TODO()
  }

  actual fun setTextMaxAngle(maxAngle: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setTextWritingMode(writingMode: CompiledExpression<ListValue<TextWritingMode>>) {
    TODO()
  }

  actual fun setTextRotate(rotate: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setTextPadding(padding: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setTextKeepUpright(keepUpright: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setTextTransform(transform: CompiledExpression<TextTransform>) {
    TODO()
  }

  actual fun setTextOffset(offset: CompiledExpression<FloatOffsetValue>) {
    TODO()
  }

  actual fun setTextAllowOverlap(allowOverlap: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setTextOverlap(overlap: CompiledExpression<SymbolOverlap>) {
    TODO()
  }

  actual fun setTextIgnorePlacement(ignorePlacement: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setTextOptional(optional: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setTextOpacity(opacity: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setTextColor(color: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setTextHaloColor(haloColor: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setTextHaloWidth(haloWidth: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setTextHaloBlur(haloBlur: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setTextTranslate(translate: CompiledExpression<DpOffsetValue>) {
    TODO()
  }

  actual fun setTextTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    TODO()
  }
}
