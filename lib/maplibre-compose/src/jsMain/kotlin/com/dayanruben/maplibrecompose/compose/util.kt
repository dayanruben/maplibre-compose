package com.dayanruben.maplibrecompose.compose

@JsName("performance")
internal external object performance {
    internal fun now(): Double
}

internal actual fun nanoTime(): Long = (performance.now() * 1_000_000).toLong()
