package com.codenjoy.dojo.client

import com.codenjoy.dojo.services.Command
import com.codenjoy.dojo.services.Point
import com.codenjoy.dojo.services.PointImpl
import com.codenjoy.dojo.tetris.client.Board
import com.codenjoy.dojo.tetris.client.GlassBoard

class MySolver : AbstractJsonSolver<Board>() {

    override fun getAnswer(b: Board): String {
        val command = Command.LEFT
        val boardView = BoardView(b)

//        boardView.findTopLineGaps().forEach {
//            println(it.contentToString())
//        }

        println(boardView)

        return Command.RIGHT
                .then(Command.RIGHT)
                .then(Command.RIGHT)
                .then(Command.DOWN)
                .toString()
    }

}

class BoardView(val board: Board) {

    private val glass: GlassBoard = board.glass
    private val currentFigurePoints: Array<out Point> = board.predictCurrentFigurePoints()!!
    private val topFilledLineY: Int
    private var glassPoints: Array<Array<Boolean>> = arrayOf()

    init {
        val glass = board.glass
        for (y in 0 until glass.size) {
            var line = arrayOf<Boolean>()
            for (x in 0 until glass.size) {
                line += !glass.isFree(x, y)
            }
            glassPoints += line
        }

        topFilledLineY = findTopDroppedFiguresLine(currentFigurePoints.asList())
    }

    private fun findTopDroppedFiguresLine(byCurrentFigure: List<Point>): Int = glass.figures
            .filterNot { byCurrentFigure.contains(it) }
            .takeIf { it.isNotEmpty() }
            ?.maxOf { it.y } ?: 0

    override fun toString(): String = StringBuilder().apply {
        for (y in glassPoints.indices.reversed()) {
            for (x in glassPoints[y].indices) {
                append(glass.getAt(x, y))
            }
            appendLine()
        }
    }.toString()

    fun findTopLineGaps(): List<Array<Point>> = mutableListOf<Array<Point>>().run {
        var gap = arrayOf<Point>()
        for (x in glassPoints[topFilledLineY].indices) {
            if (!glassPoints[topFilledLineY][x]) {
                gap += PointImpl.pt(x, topFilledLineY)
            } else {
                if (gap.isNotEmpty()) {
                    add(gap.clone())
                }
                gap = arrayOf()
            }
        }
        return this
    }
}
