package com.dayanruben.maplibrecompose.core.util

import androidx.compose.runtime.Composable
import com.dayanruben.maplibrecompose.compose.LocalMaplibreContext

public actual object PlatformUtils {
  @Composable
  public actual fun getSystemRefreshRate(): Float =
    LocalMaplibreContext.current.refreshRate.toFloat()
}
