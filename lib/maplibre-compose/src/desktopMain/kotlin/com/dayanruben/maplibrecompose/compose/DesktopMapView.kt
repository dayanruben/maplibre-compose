package com.dayanruben.maplibrecompose.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import com.multiplatform.webview.jsbridge.IJsMessageHandler
import com.multiplatform.webview.jsbridge.JsMessage
import com.multiplatform.webview.jsbridge.rememberWebViewJsBridge
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.WebViewNavigator
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData
import com.dayanruben.maplibrecompose.core.MapOptions
import com.dayanruben.maplibrecompose.core.MaplibreMap
import com.dayanruben.maplibrecompose.core.SafeStyle
import com.dayanruben.maplibrecompose.core.WebviewBridge
import com.dayanruben.maplibrecompose.core.WebviewMap

@Composable
internal actual fun ComposableMapView(
  modifier: Modifier,
  styleUri: String,
  rememberedStyle: SafeStyle?,
  update: (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
  platformOptions: MapOptions,
) =
  DesktopMapView(
    modifier = modifier,
    styleUri = styleUri,
    update = update,
    onReset = onReset,
    logger = logger,
    callbacks = callbacks,
  )

@Composable
internal fun DesktopMapView(
  modifier: Modifier,
  styleUri: String,
  update: suspend (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
) {
  val data = LocalMaplibreContext.current.webviewHtml
  val state = rememberWebViewStateWithHTMLData(data)
  val navigator = rememberWebViewNavigator()
  val jsBridge = rememberWebViewJsBridge(navigator)

  WebView(
    state = state,
    modifier = modifier.fillMaxWidth(),
    navigator = navigator,
    webViewJsBridge = jsBridge,
    onCreated = { _ -> },
    onDispose = { _ -> onReset() },
  )

  if (state.isLoading) return

  val map = remember(state) { WebviewMap(WebviewBridge(state.nativeWebView, "WebviewMapBridge")) }

  LaunchedEffect(map) { map.init() }

  LaunchedEffect(map, styleUri) { map.asyncSetStyleUri(styleUri) }

  LaunchedEffect(map, update) { update(map) }
}

internal class MessageHandler(
  val name: String,
  val handler:
    (message: JsMessage, navigator: WebViewNavigator?, callback: (String) -> Unit) -> Unit,
) : IJsMessageHandler {
  override fun handle(
    message: JsMessage,
    navigator: WebViewNavigator?,
    callback: (String) -> Unit,
  ) = handler(message, navigator, callback)

  override fun methodName(): String = name
}
