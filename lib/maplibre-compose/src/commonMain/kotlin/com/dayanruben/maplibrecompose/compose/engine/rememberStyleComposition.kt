package com.dayanruben.maplibrecompose.compose.engine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.staticCompositionLocalOf
import co.touchlab.kermit.Logger
import com.dayanruben.maplibrecompose.compose.MaplibreComposable
import com.dayanruben.maplibrecompose.core.SafeStyle
import kotlinx.coroutines.awaitCancellation

@Composable
internal fun rememberStyleComposition(
  maybeStyle: SafeStyle?,
  logger: Logger?,
  content: @Composable @MaplibreComposable () -> Unit,
): State<StyleNode?> {
  val nodeState = remember { mutableStateOf<StyleNode?>(null) }
  val compositionContext = rememberCompositionContext()

  LaunchedEffect(maybeStyle, compositionContext) {
    val style = maybeStyle ?: return@LaunchedEffect
    val rootNode = StyleNode(style, logger).also { nodeState.value = it }
    val composition = Composition(MapNodeApplier(rootNode), compositionContext)

    composition.setContent {
      CompositionLocalProvider(LocalStyleNode provides rootNode) { content() }
    }

    try {
      awaitCancellation()
    } finally {
      nodeState.value = null
      composition.dispose()
    }
  }

  return nodeState
}

internal val LocalStyleNode = staticCompositionLocalOf<StyleNode> { throw IllegalStateException() }
