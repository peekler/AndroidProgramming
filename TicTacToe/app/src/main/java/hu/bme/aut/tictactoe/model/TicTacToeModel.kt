package hu.bme.aut.tictactoe.model

import java.io.Serializable

object TicTacToeModel : Serializable {
    public val EMPTY: Short = 0
    public val CIRCLE: Short = 1
    public val CROSS: Short = 2

    public val NOT_FINISHED: Short = -1
    public val TIE: Short = -2


    private val model = arrayOf(
        shortArrayOf(EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY)
    )

    private var nextPlayer = CIRCLE


    fun resetModel() {
        for (i in 0..2) {
            for (j in 0..2) {
                model[i][j] = EMPTY
            }
        }
        nextPlayer = CIRCLE
    }

    fun getFieldContent(x: Int, y: Int) = model[x][y]

    fun setFieldContent(x: Int, y: Int, content: Short) {
        model[x][y] = content
    }

    fun getNextPlayer() = nextPlayer

    fun changeNextPlayer() {
        nextPlayer = if (nextPlayer == CIRCLE) CROSS else CIRCLE
    }

    fun getWinner(): Short {
        val row1_O = model[0][0] === CIRCLE && model[1][0] === CIRCLE && model[2][0] === CIRCLE
        val row2_O = model[0][1] === CIRCLE && model[1][1] === CIRCLE && model[2][1] === CIRCLE
        val row3_O = model[0][2] === CIRCLE && model[1][2] === CIRCLE && model[2][2] === CIRCLE
        val col1_O = model[0][0] === CIRCLE && model[0][1] === CIRCLE && model[0][2] === CIRCLE
        val col2_O = model[1][0] === CIRCLE && model[1][1] === CIRCLE && model[1][2] === CIRCLE
        val col3_O = model[2][0] === CIRCLE && model[2][1] === CIRCLE && model[2][2] === CIRCLE
        val diag1_O = model[0][0] === CIRCLE && model[1][1] === CIRCLE && model[2][2] === CIRCLE
        val diag2_O = model[2][0] === CIRCLE && model[1][1] === CIRCLE && model[0][2] === CIRCLE
        val row1_X = model[0][0] === CROSS && model[1][0] === CROSS && model[2][0] === CROSS
        val row2_X = model[0][1] === CROSS && model[1][1] === CROSS && model[2][1] === CROSS
        val row3_X = model[0][2] === CROSS && model[1][2] === CROSS && model[2][2] === CROSS
        val col1_X = model[0][0] === CROSS && model[0][1] === CROSS && model[0][2] === CROSS
        val col2_X = model[1][0] === CROSS && model[1][1] === CROSS && model[1][2] === CROSS
        val col3_X = model[2][0] === CROSS && model[2][1] === CROSS && model[2][2] === CROSS
        val diag1_X = model[0][0] === CROSS && model[1][1] === CROSS && model[2][2] === CROSS
        val diag2_X = model[2][0] === CROSS && model[1][1] === CROSS && model[0][2] === CROSS

        if (row1_O || row2_O || row3_O || col1_O || col2_O || col3_O || diag1_O || diag2_O) {
            return CIRCLE
        } else if (row1_X || row2_X || row3_X || col1_X || col2_X || col3_X || diag1_X || diag2_X) {
            return CROSS
        } else {
            for (i in 0..2) {
                for (j in 0..2) {
                    if (model[i][j] === EMPTY) {
                        return NOT_FINISHED
                    }
                }
            }

            return TIE
        }
    }

}