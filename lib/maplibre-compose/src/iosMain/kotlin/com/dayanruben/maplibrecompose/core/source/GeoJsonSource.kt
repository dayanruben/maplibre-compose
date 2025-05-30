package com.dayanruben.maplibrecompose.core.source

import cocoapods.MapLibre.MLNShapeSource
import cocoapods.MapLibre.MLNShapeSourceOptionBuffer
import cocoapods.MapLibre.MLNShapeSourceOptionClusterProperties
import cocoapods.MapLibre.MLNShapeSourceOptionClusterRadius
import cocoapods.MapLibre.MLNShapeSourceOptionClustered
import cocoapods.MapLibre.MLNShapeSourceOptionLineDistanceMetrics
import cocoapods.MapLibre.MLNShapeSourceOptionMaximumZoomLevel
import cocoapods.MapLibre.MLNShapeSourceOptionMaximumZoomLevelForClustering
import cocoapods.MapLibre.MLNShapeSourceOptionMinimumZoomLevel
import cocoapods.MapLibre.MLNShapeSourceOptionSimplificationTolerance
import com.dayanruben.maplibrecompose.core.util.toMLNShape
import com.dayanruben.maplibrecompose.core.util.toNSExpression
import com.dayanruben.maplibrecompose.expressions.ExpressionContext
import io.github.dellisd.spatialk.geojson.GeoJson
import platform.Foundation.NSNumber
import platform.Foundation.NSURL

public actual class GeoJsonSource : Source {
  override val impl: MLNShapeSource

  internal constructor(source: MLNShapeSource) {
    impl = source
  }

  public actual constructor(id: String, uri: String, options: GeoJsonOptions) {
    impl =
      MLNShapeSource(identifier = id, URL = NSURL(string = uri), options = buildOptionMap(options))
  }

  public actual constructor(id: String, data: GeoJson, options: GeoJsonOptions) {
    impl =
      MLNShapeSource(identifier = id, shape = data.toMLNShape(), options = buildOptionMap(options))
  }

  private fun buildOptionMap(options: GeoJsonOptions) =
    buildMap<Any?, Any?> {
      put(MLNShapeSourceOptionMinimumZoomLevel, NSNumber(options.minZoom))
      put(MLNShapeSourceOptionMaximumZoomLevel, NSNumber(options.maxZoom))
      put(MLNShapeSourceOptionBuffer, NSNumber(options.buffer))
      put(MLNShapeSourceOptionLineDistanceMetrics, NSNumber(options.lineMetrics))
      put(MLNShapeSourceOptionSimplificationTolerance, NSNumber(options.tolerance.toDouble()))
      put(MLNShapeSourceOptionClustered, NSNumber(options.cluster))
      put(MLNShapeSourceOptionMaximumZoomLevelForClustering, NSNumber(options.clusterMaxZoom))
      put(MLNShapeSourceOptionClusterRadius, NSNumber(options.clusterRadius))
      put(
        MLNShapeSourceOptionClusterProperties,
        options.clusterProperties.mapValues { (name, aggregator) ->
          listOf(
            aggregator.reducer.compile(ExpressionContext.None).toNSExpression(),
            aggregator.mapper.compile(ExpressionContext.None).toNSExpression(),
          )
        },
      )
    }

  public actual fun setUri(uri: String) {
    impl.setURL(NSURL(string = uri))
  }

  public actual fun setData(geoJson: GeoJson) {
    impl.setShape(geoJson.toMLNShape())
  }
}
