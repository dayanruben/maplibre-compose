package com.dayanruben.maplibrecompose.core.source

import com.dayanruben.maplibrecompose.core.util.correctedAndroidUri
import org.maplibre.android.style.sources.RasterSource as MLNRasterSource

public actual class RasterSource : Source {
  override val impl: MLNRasterSource

  internal constructor(source: MLNRasterSource) {
    impl = source
  }

  public actual constructor(id: String, uri: String, tileSize: Int) {
    impl = MLNRasterSource(id, uri.correctedAndroidUri(), tileSize)
  }
}
