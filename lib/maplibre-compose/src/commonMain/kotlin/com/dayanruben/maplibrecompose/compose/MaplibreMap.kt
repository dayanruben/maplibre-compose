package com.dayanruben.maplibrecompose.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import co.touchlab.kermit.Logger
import com.dayanruben.maplibrecompose.compose.engine.LayerNode
import com.dayanruben.maplibrecompose.compose.engine.rememberStyleComposition
import com.dayanruben.maplibrecompose.core.CameraMoveReason
import com.dayanruben.maplibrecompose.core.GestureSettings
import com.dayanruben.maplibrecompose.core.MaplibreMap
import com.dayanruben.maplibrecompose.core.OrnamentSettings
import com.dayanruben.maplibrecompose.core.StandardMaplibreMap
import com.dayanruben.maplibrecompose.core.Style
import com.dayanruben.maplibrecompose.core.util.PlatformUtils
import io.github.dellisd.spatialk.geojson.Position
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

/**
 * Displays a MapLibre based map.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param styleUri The URI of the map style specification JSON to use, see
 *   [MapLibre Style](https://maplibre.org/maplibre-style-spec/).
 * @param zoomRange The allowable bounds for the camera zoom level.
 * @param pitchRange The allowable bounds for the camera pitch.
 * @param gestureSettings Defines which user map gestures are enabled.
 * @param ornamentSettings Defines which additional UI elements are displayed on top of the map.
 * @param cameraState The camera state specifies what position of the map is rendered, at what zoom,
 *   at what tilt, etc.
 * @param onMapClick Invoked when the map is clicked. A click callback can be defined per layer,
 *   too, see e.g. the `onClick` parameter for
 *   [LineLayer][com.dayanruben.maplibrecompose.compose.layer.LineLayer]. However, this callback is
 *   always called first and can thus prevent subsequent callbacks to be invoked by consuming the
 *   event.
 * @param onMapLongClick Invoked when the map is long-clicked. See [onMapClick].
 * @param onFrame Invoked on every rendered frame.
 * @param onMapReady Invoked when the map is ready to use after the style has been loaded.
 * @param isDebugEnabled Whether the map debug information is shown.
 * @param maximumFps The maximum frame rate at which the map view is rendered, but it can't exceed
 *   the ability of device hardware.
 *
 * Note: This parameter does not take effect on web and desktop.
 *
 * @param logger kermit logger to use
 * @param content The map content additional to what is already part of the map as defined in the
 *   base map style linked in [styleUri].
 *
 * Additional [sources](https://maplibre.org/maplibre-style-spec/sources/) can be added via:
 * - [rememberGeoJsonSource][com.dayanruben.maplibrecompose.compose.source.rememberGeoJsonSource] (see
 *   [GeoJsonSource][com.dayanruben.maplibrecompose.core.source.GeoJsonSource]),
 * - [rememberVectorSource][com.dayanruben.maplibrecompose.compose.source.rememberVectorSource] (see
 *   [VectorSource][com.dayanruben.maplibrecompose.core.source.VectorSource]),
 * - [rememberRasterSource][com.dayanruben.maplibrecompose.compose.source.rememberRasterSource] (see
 *   [RasterSource][com.dayanruben.maplibrecompose.core.source.RasterSource])
 *
 * A source that is already defined in the base map style can be referenced via
 * [getBaseSource][com.dayanruben.maplibrecompose.compose.source.getBaseSource].
 *
 * The data from a source can then be used in
 * [layer](https://maplibre.org/maplibre-style-spec/layers/) definition(s), which define how that
 * data is rendered, see:
 * - [BackgroundLayer][com.dayanruben.maplibrecompose.compose.layer.BackgroundLayer]
 * - [LineLayer][com.dayanruben.maplibrecompose.compose.layer.LineLayer]
 * - [FillExtrusionLayer][com.dayanruben.maplibrecompose.compose.layer.FillExtrusionLayer]
 * - [FillLayer][com.dayanruben.maplibrecompose.compose.layer.FillLayer]
 * - [HeatmapLayer][com.dayanruben.maplibrecompose.compose.layer.HeatmapLayer]
 * - [HillshadeLayer][com.dayanruben.maplibrecompose.compose.layer.HillshadeLayer]
 * - [LineLayer][com.dayanruben.maplibrecompose.compose.layer.LineLayer]
 * - [RasterLayer][com.dayanruben.maplibrecompose.compose.layer.RasterLayer]
 * - [SymbolLayer][com.dayanruben.maplibrecompose.compose.layer.SymbolLayer]
 *
 * By default, the layers defined in this scope are put on top of the layers from the base style, in
 * the order they are defined. Alternatively, it is possible to anchor layers at certain layers from
 * the base style. This is done, for example, in order to add a layer just below the first symbol
 * layer from the base style so that it isn't above labels. See:
 * - [Anchor.Top][com.dayanruben.maplibrecompose.compose.layer.Anchor.Companion.Top],
 * - [Anchor.Bottom][com.dayanruben.maplibrecompose.compose.layer.Anchor.Companion.Bottom],
 * - [Anchor.Above][com.dayanruben.maplibrecompose.compose.layer.Anchor.Companion.Above],
 * - [Anchor.Below][com.dayanruben.maplibrecompose.compose.layer.Anchor.Companion.Below],
 * - [Anchor.Replace][com.dayanruben.maplibrecompose.compose.layer.Anchor.Companion.Replace],
 * - [Anchor.At][com.dayanruben.maplibrecompose.compose.layer.Anchor.Companion.At]
 */
@Composable
public fun MaplibreMap(
  modifier: Modifier = Modifier,
  styleUri: String = "https://demotiles.maplibre.org/style.json",
  zoomRange: ClosedRange<Float> = 0f..20f,
  pitchRange: ClosedRange<Float> = 0f..60f,
  gestureSettings: GestureSettings = GestureSettings.AllEnabled,
  ornamentSettings: OrnamentSettings = OrnamentSettings.AllEnabled,
  cameraState: CameraState = rememberCameraState(),
  styleState: StyleState = rememberStyleState(),
  onMapClick: MapClickHandler = { _, _ -> ClickResult.Pass },
  onMapLongClick: MapClickHandler = { _, _ -> ClickResult.Pass },
  onFrame: (framesPerSecond: Double) -> Unit = {},
  onMapReady: () -> Unit = {},
  isDebugEnabled: Boolean = false,
  maximumFps: Int = PlatformUtils.getSystemRefreshRate().roundToInt(),
  logger: Logger? = remember { Logger.withTag("maplibre-compose") },
  content: @Composable @MaplibreComposable () -> Unit = {},
) {
  var rememberedStyle by remember { mutableStateOf<Style?>(null) }
  var isMapInitialized by remember { mutableStateOf(false) }
  val styleComposition by rememberStyleComposition(if (isMapInitialized) Style.Null else null, logger, content)

  val callbacks =
    remember(cameraState, styleState, styleComposition) {
      object : MaplibreMap.Callbacks {
        override fun onStyleChanged(map: MaplibreMap, style: Style?) {
          map as StandardMaplibreMap
          if (!isMapInitialized) {
            isMapInitialized = true
            onMapReady()
          }
          styleState.attach(style)
          rememberedStyle = style
          cameraState.metersPerDpAtTargetState.value =
            map.metersPerDpAtLatitude(map.getCameraPosition().target.latitude)
        }

        override fun onCameraMoveStarted(map: MaplibreMap, reason: CameraMoveReason) {
          cameraState.moveReasonState.value = reason
        }

        override fun onCameraMoved(map: MaplibreMap) {
          map as StandardMaplibreMap
          cameraState.positionState.value = map.getCameraPosition()
          cameraState.metersPerDpAtTargetState.value =
            map.metersPerDpAtLatitude(map.getCameraPosition().target.latitude)
        }

        override fun onCameraMoveEnded(map: MaplibreMap) {}

        private fun layerNodesInOrder(): List<LayerNode<*>> {
          val layerNodes =
            (styleComposition?.children?.filterIsInstance<LayerNode<*>>() ?: emptyList())
              .associateBy { node -> node.layer.id }
          val layers = styleComposition?.style?.getLayers() ?: emptyList()
          return layers.asReversed().mapNotNull { layer -> layerNodes[layer.id] }
        }

        override fun onClick(map: MaplibreMap, latLng: Position, offset: DpOffset) {
          map as StandardMaplibreMap
          if (onMapClick(latLng, offset).consumed) return
          layerNodesInOrder().find { node ->
            val handle = node.onClick ?: return@find false
            val features =
              map.queryRenderedFeatures(
                offset = offset,
                layerIds = setOf(node.layer.id),
                predicate = null,
              )
            features.isNotEmpty() && handle(features).consumed
          }
        }

        override fun onLongClick(map: MaplibreMap, latLng: Position, offset: DpOffset) {
          map as StandardMaplibreMap
          if (onMapLongClick(latLng, offset).consumed) return
          layerNodesInOrder().find { node ->
            val handle = node.onLongClick ?: return@find false
            val features =
              map.queryRenderedFeatures(
                offset = offset,
                layerIds = setOf(node.layer.id),
                predicate = null,
              )
            features.isNotEmpty() && handle(features).consumed
          }
        }

        override fun onFrame(fps: Double) {
          onFrame(fps)
        }
      }
    }

  val scope = rememberCoroutineScope()

  ComposableMapView(
    modifier = modifier.fillMaxSize(),
    styleUri = styleUri,
    update = { map ->
      when (map) {
        is StandardMaplibreMap -> {
          cameraState.map = map
          map.setDebugEnabled(isDebugEnabled)
          map.setMinZoom(zoomRange.start.toDouble())
          map.setMaxZoom(zoomRange.endInclusive.toDouble())
          map.setMinPitch(pitchRange.start.toDouble())
          map.setMaxPitch(pitchRange.endInclusive.toDouble())
          map.setGestureSettings(gestureSettings)
          map.setOrnamentSettings(ornamentSettings)
          map.setMaximumFps(maximumFps)
        }

        else ->
          scope.launch {
            map.asyncSetDebugEnabled(isDebugEnabled)
            map.asyncSetMinZoom(zoomRange.start.toDouble())
            map.asyncSetMaxZoom(zoomRange.endInclusive.toDouble())
            map.asyncSetMinPitch(pitchRange.start.toDouble())
            map.asyncSetMaxPitch(pitchRange.endInclusive.toDouble())
            map.asyncSetGestureSettings(gestureSettings)
            map.asyncSetOrnamentSettings(ornamentSettings)
            map.asyngSetMaximumFps(maximumFps)
          }
      }
    },
    onReset = {
      cameraState.map = null
      rememberedStyle = null
    },
    logger = logger,
    callbacks = callbacks,
  )
}
