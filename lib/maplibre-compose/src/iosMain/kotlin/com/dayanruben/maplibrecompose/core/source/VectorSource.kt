package com.dayanruben.maplibrecompose.core.source

import cocoapods.MapLibre.MLNFeatureProtocol
import cocoapods.MapLibre.MLNVectorTileSource
import com.dayanruben.maplibrecompose.core.util.toFeature
import com.dayanruben.maplibrecompose.core.util.toNSPredicate
import com.dayanruben.maplibrecompose.expressions.ExpressionContext
import com.dayanruben.maplibrecompose.expressions.ast.Expression
import com.dayanruben.maplibrecompose.expressions.dsl.const
import com.dayanruben.maplibrecompose.expressions.value.BooleanValue
import io.github.dellisd.spatialk.geojson.Feature
import platform.Foundation.NSURL

public actual class VectorSource : Source {
  override val impl: MLNVectorTileSource

  internal constructor(source: MLNVectorTileSource) {
    impl = source
  }

  public actual constructor(id: String, uri: String) : super() {
    this.impl = MLNVectorTileSource(id, NSURL(string = uri))
  }

  public actual fun querySourceFeatures(
    sourceLayerIds: Set<String>,
    predicate: Expression<BooleanValue>,
  ): List<Feature> {
    return impl
      .featuresInSourceLayersWithIdentifiers(
        sourceLayerIdentifiers = sourceLayerIds,
        predicate =
          predicate
            .takeUnless { it == const(true) }
            ?.compile(ExpressionContext.None)
            ?.toNSPredicate(),
      )
      .map { (it as MLNFeatureProtocol).toFeature() }
  }
}
