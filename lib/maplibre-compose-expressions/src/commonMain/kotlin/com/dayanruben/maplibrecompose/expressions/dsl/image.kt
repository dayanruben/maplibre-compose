package com.dayanruben.maplibrecompose.expressions.dsl

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize
import com.dayanruben.maplibrecompose.expressions.ast.BitmapLiteral
import com.dayanruben.maplibrecompose.expressions.ast.Expression
import com.dayanruben.maplibrecompose.expressions.ast.FunctionCall
import com.dayanruben.maplibrecompose.expressions.ast.PainterLiteral
import com.dayanruben.maplibrecompose.expressions.value.ImageValue
import com.dayanruben.maplibrecompose.expressions.value.StringValue

/**
 * Returns an image type for use in `iconImage` (see
 * [SymbolLayer][com.dayanruben.maplibrecompose.compose.layer.SymbolLayer]), `pattern` entries (see
 * [BackgroundLayer][com.dayanruben.maplibrecompose.compose.layer.BackgroundLayer],
 * [FillLayer][com.dayanruben.maplibrecompose.compose.layer.FillLayer],
 * [FillExtrusionLayer][com.dayanruben.maplibrecompose.compose.layer.FillExtrusionLayer],
 * [LineLayer][com.dayanruben.maplibrecompose.compose.layer.LineLayer]) and as a section in the
 * [format] expression.
 *
 * If set, the image argument will check that the requested image exists in the style and will
 * return either the resolved image name or `null`, depending on whether or not the image is
 * currently in the style. This validation process is synchronous and requires the image to have
 * been added to the style before requesting it in the image argument.
 */
public fun image(value: Expression<StringValue>): Expression<ImageValue> =
  FunctionCall.of("image", value).cast()

/**
 * Returns an image type for use in `iconImage` (see
 * [SymbolLayer][com.dayanruben.maplibrecompose.compose.layer.SymbolLayer]), `pattern` entries (see
 * [BackgroundLayer][com.dayanruben.maplibrecompose.compose.layer.BackgroundLayer],
 * [FillLayer][com.dayanruben.maplibrecompose.compose.layer.FillLayer],
 * [FillExtrusionLayer][com.dayanruben.maplibrecompose.compose.layer.FillExtrusionLayer],
 * [LineLayer][com.dayanruben.maplibrecompose.compose.layer.LineLayer]) and as a section in the
 * [format] expression.
 *
 * The image argument will check that the requested image exists in the style and will return either
 * the resolved image name or `null`, depending on whether or not the image is currently in the
 * style. This validation process is synchronous and requires the image to have been added to the
 * style before requesting it in the image argument.
 */
public fun image(value: String): Expression<ImageValue> = image(const(value))

/**
 * Returns an image type for use in `iconImage` (see
 * [SymbolLayer][com.dayanruben.maplibrecompose.compose.layer.SymbolLayer]), `pattern` entries (see
 * [BackgroundLayer][com.dayanruben.maplibrecompose.compose.layer.BackgroundLayer],
 * [FillLayer][com.dayanruben.maplibrecompose.compose.layer.FillLayer],
 * [FillExtrusionLayer][com.dayanruben.maplibrecompose.compose.layer.FillExtrusionLayer],
 * [LineLayer][com.dayanruben.maplibrecompose.compose.layer.LineLayer]) and as a section in the
 * [format] expression.
 *
 * The [ImageBitmap] will be registered with the style when it's referenced by a layer, and
 * unregistered from the style if it's no longer referenced by any layer. An ID referencing the
 * bitmap will be generated automatically and inserted into the expression.
 */
public fun image(value: ImageBitmap, sdf: Boolean = false): Expression<ImageValue> =
  FunctionCall.of("image", BitmapLiteral.of(value, sdf)).cast()

/**
 * Returns an image type for use in `iconImage` (see
 * [SymbolLayer][com.dayanruben.maplibrecompose.compose.layer.SymbolLayer]), `pattern` entries (see
 * [BackgroundLayer][com.dayanruben.maplibrecompose.compose.layer.BackgroundLayer],
 * [FillLayer][com.dayanruben.maplibrecompose.compose.layer.FillLayer],
 * [FillExtrusionLayer][com.dayanruben.maplibrecompose.compose.layer.FillExtrusionLayer],
 * [LineLayer][com.dayanruben.maplibrecompose.compose.layer.LineLayer]) and as a section in the
 * [format] expression.
 *
 * The [Painter] will be drawn to an [ImageBitmap] and registered with the style when it's
 * referenced by a layer, and unregistered from the style if it's no longer referenced by any layer.
 * An ID referencing the bitmap will be generated automatically and inserted into the expression.
 *
 * The bitmap will be created with the provided [size], or the intrinsic size of the painter if not
 * provided, or 16x16 DP if the painter has no intrinsic size.
 */
public fun image(
  value: Painter,
  size: DpSize? = null,
  sdf: Boolean = false,
): Expression<ImageValue> = FunctionCall.of("image", PainterLiteral.of(value, size, sdf)).cast()
