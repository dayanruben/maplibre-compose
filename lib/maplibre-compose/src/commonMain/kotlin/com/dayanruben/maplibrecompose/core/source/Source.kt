package com.dayanruben.maplibrecompose.core.source

/** A data source for map data */
public expect sealed class Source {
  internal val id: String
  public val attributionLinks: List<AttributionLink>
}
