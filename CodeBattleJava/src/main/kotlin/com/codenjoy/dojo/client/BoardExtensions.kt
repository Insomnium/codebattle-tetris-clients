package com.codenjoy.dojo.client

import com.codenjoy.dojo.services.Point
import com.codenjoy.dojo.services.PointImpl
import com.codenjoy.dojo.tetris.model.Elements

fun Array<out Point>.getBottomWidth(): Int {
    val bottomLineIndex = minOf { it.y }
    return count { it.y == bottomLineIndex }
}

fun Point.from(x: Int, y: Int): Point = PointImpl.pt(x, y)

fun Elements.free() = this == Elements.NONE
