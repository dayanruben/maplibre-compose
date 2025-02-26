package com.dayanruben.maplibrecompose.demoapp.demos

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dayanruben.maplibrecompose.compose.MaplibreMap
import com.dayanruben.maplibrecompose.compose.layer.Anchor
import com.dayanruben.maplibrecompose.compose.layer.LineLayer
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
import com.dayanruben.maplibrecompose.expressions.dsl.const
import com.dayanruben.maplibrecompose.expressions.dsl.exponential
import com.dayanruben.maplibrecompose.expressions.dsl.interpolate
import com.dayanruben.maplibrecompose.expressions.dsl.zoom
import com.dayanruben.maplibrecompose.expressions.value.LineCap
import com.dayanruben.maplibrecompose.expressions.value.LineJoin
import io.github.dellisd.spatialk.geojson.Position
import org.jetbrains.compose.resources.ExperimentalResourceApi

private const val ROUTES_FILE = "files/data/amtrak_routes.geojson"

private val US = Position(latitude = 46.336, longitude = -96.205)

object AnimatedLayerDemo : Demo {
  override val name = "Animated layer"
  override val description = "Change layer properties at runtime."

  @Composable
  @OptIn(ExperimentalResourceApi::class)
  override fun Component(navigateUp: () -> Unit) {
    DemoScaffold(this, navigateUp) {
      val cameraState = rememberCameraState(firstPosition = CameraPosition(target = US, zoom = 2.0))
      val styleState = rememberStyleState()

      Box(modifier = Modifier.fillMaxSize()) {
        MaplibreMap(
          styleUri = DEFAULT_STYLE,
          cameraState = cameraState,
          styleState = styleState,
          ornamentSettings = DemoOrnamentSettings(),
        ) {
          val routeSource =
            rememberGeoJsonSource(id = "amtrak-routes", uri = Res.getUri(ROUTES_FILE))

          val infiniteTransition = rememberInfiniteTransition()
          val animatedColor by
            infiniteTransition.animateColor(
              Color.hsl(0f, 1f, 0.5f),
              Color.hsl(0f, 1f, 0.5f),
              animationSpec =
                infiniteRepeatable(
                  animation =
                    keyframes {
                      durationMillis = 10000
                      for (i in 1..9) Color.hsl(i * 36f, 1f, 0.5f) at (i * 1000)
                    }
                ),
            )

          Anchor.Below("waterway_line_label") {
            LineLayer(
              id = "amtrak-routes",
              source = routeSource,
              color = const(animatedColor),
              cap = const(LineCap.Round),
              join = const(LineJoin.Round),
              width =
                interpolate(
                  type = exponential(1.2f),
                  input = zoom(),
                  7 to const(1.75.dp),
                  20 to const(22.dp),
                ),
            )
          }
        }
        DemoMapControls(cameraState, styleState)
      }
    }
  }
}
