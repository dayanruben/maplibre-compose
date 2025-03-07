package com.dayanruben.maplibrecompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.dayanruben.maplibrecompose.core.MapViewLifecycleObserver
import org.maplibre.android.maps.MapView

@Composable
internal fun MapViewLifecycleEffect(mapView: MapView?) {
  if (mapView == null) return
  val observer = remember(mapView) { MapViewLifecycleObserver(mapView) }
  val lifecycle = LocalLifecycleOwner.current.lifecycle
  DisposableEffect(lifecycle, observer) {
    lifecycle.addObserver(observer)
    onDispose { lifecycle.removeObserver(observer) }
  }
}
