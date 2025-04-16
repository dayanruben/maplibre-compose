package com.dayanruben.maplibrecompose.core.source

import com.dayanruben.maplibrecompose.expressions.ast.Expression
import com.dayanruben.maplibrecompose.expressions.value.BooleanValue
import com.dayanruben.spatialk.geojson.Feature

public actual class VectorSource actual constructor(id: String, uri: String) : Source() {
  override val impl: Nothing = TODO()

  public actual fun querySourceFeatures(
    sourceLayerIds: Set<String>,
    predicate: Expression<BooleanValue>,
  ): List<Feature> {
    TODO()
  }
}
