package com.codenjoy.dojo.client

import com.codenjoy.dojo.services.Point
import com.codenjoy.dojo.services.PointImpl
import com.codenjoy.dojo.tetris.client.Board
import com.codenjoy.dojo.tetris.model.Elements
import org.junit.jupiter.api.Test


class MySolverTest {

    @Test
    fun `should find gaps in top filled line`() {
        // given
        val boardView = BoardView(prepareBoard("""
            .......
            .......
            .......
            .......
            .......
            ...OO..
            ...OO..
        """.trimIndent(), Elements.YELLOW, PointImpl.pt(3, 6))
        )

        // when
        val topGaps = boardView.findTopLineGaps()

        // then
        val debug = "debug"
    }

    private fun prepareBoard(repr: String, currentFigureType: Elements, figurePoint: Point): Board = Board().apply {
        forString("""
            {
                "currentFigurePoint": { "x": ${figurePoint.x}, "y": ${figurePoint.y} },
                "currentFigureType": "${currentFigureType.ch()}",
                "layers": [ "${repr.replace("\n", "")}" ] 
            }
            """.trimIndent())
    }
}
