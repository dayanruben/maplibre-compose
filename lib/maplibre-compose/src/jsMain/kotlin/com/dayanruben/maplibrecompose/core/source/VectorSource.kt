package com.dayanruben.maplibrecompose.core.source

import com.dayanruben.maplibrecompose.expressions.ast.Expression
import com.dayanruben.maplibrecompose.expressions.value.BooleanValue
import com.dayanruben.spatialk.geojson.Feature

public actual class VectorSource : Source {
  public actual constructor(id: String, uri: String) : super() {
    this.impl = TODO()
  }

  public actual constructor(id: String, tiles: List<String>, options: TileSetOptions) : super() {
    this.impl = TODO()
  }

  override val impl: Nothing

  public actual fun querySourceFeatures(
    sourceLayerIds: Set<String>,
    predicate: Expression<BooleanValue>,
  ): List<Feature> {
    TODO()
  }
}
