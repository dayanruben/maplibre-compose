package com.dayanruben.maplibrecompose.compose.source

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import com.dayanruben.maplibrecompose.core.source.GeoJsonOptions
import com.dayanruben.maplibrecompose.core.source.GeoJsonSource
import com.dayanruben.spatialk.geojson.GeoJson

/**
 * Remember a new [GeoJsonSource] with the given [id] and [options] from the GeoJson data at the
 * given [uri].
 *
 * @throws IllegalArgumentException if a layer with the given [id] already exists.
 */
@Composable
public fun rememberGeoJsonSource(
  id: String,
  uri: String,
  options: GeoJsonOptions = GeoJsonOptions(),
): GeoJsonSource =
  key(id, options) {
    rememberUserSource(
      factory = { GeoJsonSource(id = id, uri = uri, options = options) },
      update = { setUri(uri) },
    )
  }

/**
 * Remember a new [GeoJsonSource] with the given [id] and [options] from the given GeoJson [data].
 *
 * @throws IllegalArgumentException if a layer with the given [id] already exists.
 */
@Composable
public fun rememberGeoJsonSource(
  id: String,
  data: GeoJson,
  options: GeoJsonOptions = GeoJsonOptions(),
): GeoJsonSource =
  key(id, options) {
    rememberUserSource(
      factory = { GeoJsonSource(id = id, data = data, options = options) },
      update = { setData(data) },
    )
  }
