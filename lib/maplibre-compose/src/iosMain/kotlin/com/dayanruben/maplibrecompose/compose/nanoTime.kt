package com.dayanruben.maplibrecompose.compose

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

internal actual fun nanoTime(): Long = (NSDate().timeIntervalSince1970 * 1_000_000_000).toLong()
