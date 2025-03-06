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
import com.dayanruben.maplibrecompose.core.Style
import kotlinx.coroutines.awaitCancellation

@Composable
internal fun rememberStyleComposition(
  maybeStyle: Style?,
  logger: Logger?,
  content: @Composable @MaplibreComposable () -> Unit,
): State<StyleNode?> {
  val nodeState = remember { mutableStateOf<StyleNode?>(null) }
  val compositionContext = rememberCompositionContext()

  // Initialize root node immediately to allow content to be called
  val rootNode = remember { StyleNode(Style.Null, logger) }
  nodeState.value = rootNode

  // Set up composition for immediate content
  val composition = remember(compositionContext) {
    Composition(MapNodeApplier(rootNode), compositionContext)
  }

  // Always ensure content is composed
  LaunchedEffect(compositionContext) {
    composition.setContent {
      CompositionLocalProvider(LocalStyleNode provides rootNode) { content() }
    }
  }

  // Handle style updates
  LaunchedEffect(maybeStyle) {
    if (maybeStyle != null) {
      rootNode.style = maybeStyle
    }
    try {
      awaitCancellation()
    } finally {
      rootNode.style = Style.Null
      composition.dispose()
    }
  }

  return nodeState
}

internal val LocalStyleNode = staticCompositionLocalOf<StyleNode> { throw IllegalStateException() }
