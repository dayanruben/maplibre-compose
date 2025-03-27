package com.dayanruben.maplibrecompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.viewinterop.AndroidView
import co.touchlab.kermit.Logger
import com.dayanruben.maplibrecompose.core.AndroidMap
import com.dayanruben.maplibrecompose.core.AndroidScaleBar
import com.dayanruben.maplibrecompose.core.MaplibreMap
import org.maplibre.android.MapLibre
import org.maplibre.android.maps.MapLibreMapOptions
import org.maplibre.android.maps.MapView

@Composable
internal actual fun ComposableMapView(
  modifier: Modifier,
  styleUri: String,
  update: (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
) {
  AndroidMapView(
    modifier = modifier,
    styleUri = styleUri,
    update = update,
    onReset = onReset,
    logger = logger,
    callbacks = callbacks,
  )
}

@Composable
internal fun AndroidMapView(
  modifier: Modifier,
  styleUri: String,
  update: (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
) {
  val layoutDir = LocalLayoutDirection.current
  val density = LocalDensity.current
  val currentOnReset by rememberUpdatedState(onReset)

  // Create a key that will force recomposition when the composable enters composition
  val compositionKey = remember { Any() }

  // Use the composition key to force state recreation
  var currentMapView by remember(compositionKey) { mutableStateOf<MapView?>(null) }
  var currentMap by remember(compositionKey) { mutableStateOf<AndroidMap?>(null) }

  // Ensure proper lifecycle management
  MapViewLifecycleEffect(currentMapView)

  // Ensure proper cleanup when the composable leaves composition
  DisposableEffect(Unit) {
    onDispose {
      // Perform thorough cleanup
      currentMap?.let { map ->
        // Any additional cleanup needed for the map
      }
      currentMapView?.onDestroy()
      currentOnReset()
      currentMap = null
      currentMapView = null
    }
  }

  AndroidView(
    modifier = modifier,
    factory = { context ->
      MapLibre.getInstance(context)
      MapView(context, MapLibreMapOptions.createFromAttributes(context).textureMode(false)).also {
          mapView ->
        currentMapView = mapView
        mapView.getMapAsync { map ->
          currentMap =
            AndroidMap(
              mapView = mapView,
              map = map,
              scaleBar = AndroidScaleBar(context, mapView, map),
              layoutDir = layoutDir,
              density = density,
              callbacks = callbacks,
              styleUri = styleUri,
              logger = logger,
            )

          currentMap?.let { update(it) }
        }
      }
    },
    update = { view ->
      val map = currentMap ?: return@AndroidView
      map.layoutDir = layoutDir
      map.density = density
      map.callbacks = callbacks
      map.logger = logger
      map.setStyleUri(styleUri)
      update(map)
    },
  )
}
