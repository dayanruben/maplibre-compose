package com.dayanruben.maplibrecompose.core.source

import com.dayanruben.maplibrecompose.core.util.correctedAndroidUri
import com.dayanruben.maplibrecompose.core.util.toLatLngBounds
import com.dayanruben.maplibrecompose.core.util.toMLNExpression
import com.dayanruben.maplibrecompose.expressions.ExpressionContext
import com.dayanruben.maplibrecompose.expressions.ast.Expression
import com.dayanruben.maplibrecompose.expressions.dsl.const
import com.dayanruben.maplibrecompose.expressions.value.BooleanValue
import com.dayanruben.spatialk.geojson.Feature
import org.maplibre.android.style.sources.TileSet
import org.maplibre.android.style.sources.VectorSource as MLNVectorSource

public actual class VectorSource : Source {
  override val impl: MLNVectorSource

  internal constructor(source: MLNVectorSource) {
    impl = source
  }

  public actual constructor(id: String, uri: String) {
    impl = MLNVectorSource(id, uri.correctedAndroidUri())
  }

  public actual constructor(id: String, tiles: List<String>, options: TileSetOptions) {
    impl =
      MLNVectorSource(
        id,
        TileSet("{\"type\": \"vector\"}", *tiles.toTypedArray()).apply {
          minZoom = options.minZoom.toFloat()
          maxZoom = options.maxZoom.toFloat()
          scheme =
            when (options.tileCoordinateSystem) {
              TileCoordinateSystem.XYZ -> "xyz"
              TileCoordinateSystem.TMS -> "tms"
            }
          options.boundingBox?.let { setBounds(it.toLatLngBounds()) }
          attribution = options.attributionHtml
        },
      )
  }

  public actual fun querySourceFeatures(
    sourceLayerIds: Set<String>,
    predicate: Expression<BooleanValue>,
  ): List<Feature> {
    return impl
      .querySourceFeatures(
        sourceLayerIds = sourceLayerIds.toTypedArray(),
        filter =
          predicate
            .takeUnless { it == const(true) }
            ?.compile(ExpressionContext.None)
            ?.toMLNExpression(),
      )
      .map { Feature.fromJson(it.toJson()) }
  }
}
