package com.dayanruben.maplibrecompose.core.util

import androidx.compose.runtime.Composable

public expect object PlatformUtils {
  @Composable public fun getSystemRefreshRate(): Float
}
