package com.dayanruben.maplibrecompose.demoapp.demos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.em
import com.dayanruben.maplibrecompose.compose.ClickResult
import com.dayanruben.maplibrecompose.compose.MaplibreMap
import com.dayanruben.maplibrecompose.compose.layer.SymbolLayer
import com.dayanruben.maplibrecompose.compose.rememberCameraState
import com.dayanruben.maplibrecompose.compose.rememberStyleState
import com.dayanruben.maplibrecompose.compose.source.rememberGeoJsonSource
import com.dayanruben.maplibrecompose.core.CameraPosition
import com.dayanruben.maplibrecompose.demoapp.DEFAULT_STYLE
import com.dayanruben.maplibrecompose.demoapp.Demo
import com.dayanruben.maplibrecompose.demoapp.DemoMapControls
import com.dayanruben.maplibrecompose.demoapp.DemoOrnamentSettings
import com.dayanruben.maplibrecompose.demoapp.DemoScaffold
import com.dayanruben.maplibrecompose.demoapp.generated.Res
import com.dayanruben.maplibrecompose.demoapp.generated.marker
import com.dayanruben.maplibrecompose.expressions.dsl.Feature.get
import com.dayanruben.maplibrecompose.expressions.dsl.asString
import com.dayanruben.maplibrecompose.expressions.dsl.const
import com.dayanruben.maplibrecompose.expressions.dsl.format
import com.dayanruben.maplibrecompose.expressions.dsl.image
import com.dayanruben.maplibrecompose.expressions.dsl.offset
import com.dayanruben.maplibrecompose.expressions.dsl.span
import io.github.dellisd.spatialk.geojson.Feature
import io.github.dellisd.spatialk.geojson.Position
import org.jetbrains.compose.resources.painterResource

private val CHICAGO = Position(latitude = 41.878, longitude = -87.626)

object MarkersDemo : Demo {
  override val name = "Markers, images, and formatting"
  override val description = "Add images to the style and intermingle it with text."

  @Composable
  override fun Component(navigateUp: () -> Unit) {
    DemoScaffold(this, navigateUp) {
      val marker = painterResource(Res.drawable.marker)
      val cameraState =
        rememberCameraState(firstPosition = CameraPosition(target = CHICAGO, zoom = 7.0))
      val styleState = rememberStyleState()
      var selectedFeature by remember { mutableStateOf<Feature?>(null) }

      Box(modifier = Modifier.fillMaxSize()) {
        MaplibreMap(
          styleUri = DEFAULT_STYLE,
          cameraState = cameraState,
          styleState = styleState,
          ornamentSettings = DemoOrnamentSettings(),
        ) {
          val amtrakStations =
            rememberGeoJsonSource(
              id = "amtrak-stations",
              uri =
                "https://raw.githubusercontent.com/datanews/amtrak-geojson/refs/heads/master/amtrak-stations.geojson",
            )
          SymbolLayer(
            id = "amtrak-stations",
            source = amtrakStations,
            onClick = { features ->
              selectedFeature = features.firstOrNull()
              ClickResult.Consume
            },
            iconImage = image(marker),
            textField =
              format(
                span(image("railway")),
                span(" "),
                span(get("STNCODE").asString(), textSize = const(1.2f.em)),
              ),
            textFont = const(listOf("Noto Sans Regular")),
            textColor = const(MaterialTheme.colorScheme.onBackground),
            textOffset = offset(0.em, 0.6.em),
          )
        }
        DemoMapControls(cameraState, styleState)
      }

      selectedFeature?.let { feature ->
        AlertDialog(
          onDismissRequest = { selectedFeature = null },
          confirmButton = {},
          title = { Text(feature.getStringProperty("STNNAME") ?: "") },
          text = {
            Column {
              Text("Station Code: ${feature.getStringProperty("STNCODE") ?: ""}")
              Text("Station Type: ${feature.getStringProperty("STNTYPE") ?: ""}")
              Text("Address: ${feature.getStringProperty("ADDRESS1") ?: ""}")
              Text("City: ${feature.getStringProperty("CITY") ?: ""}")
              Text("State: ${feature.getStringProperty("STATE") ?: ""}")
              Text("Zip: ${feature.getStringProperty("ZIP") ?: ""}")
            }
          },
        )
      }
    }
  }
}
