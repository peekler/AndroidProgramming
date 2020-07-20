package hu.bme.aut.tictactoe.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import hu.bme.aut.tictactoe.MainActivity
import hu.bme.aut.tictactoe.R
import hu.bme.aut.tictactoe.model.TicTacToeModel

class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paintBackground: Paint = Paint()
    private var paintLine: Paint = Paint()
    private var paintLineCross: Paint = Paint()

    private var paintText: Paint = Paint()

    private var bitmapFlag = BitmapFactory.decodeResource(
        context?.resources,
        R.drawable.flag
    )

    init {
        paintBackground.color = Color.BLACK
        paintBackground.style = Paint.Style.FILL

        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 5f

        paintLineCross.color = Color.GREEN
        paintLineCross.style = Paint.Style.STROKE
        paintLineCross.strokeWidth = 5f

        paintText.color = Color.GREEN
        paintText.textSize = 100f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paintText.textSize = h / 3f

        bitmapFlag = Bitmap.createScaledBitmap(bitmapFlag, width/3, height/3, false)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawRect(
            0f, 0f, width.toFloat(),
            height.toFloat(), paintBackground
        )

        drawGameArea(canvas)

        canvas.drawBitmap(bitmapFlag, 0f, 0f, null)

        drawPlayers(canvas)

        canvas.drawText("HELLO",10f, 150f, paintText)
    }


    private fun drawGameArea(canvas: Canvas) {
        // border
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintLine)
        // two horizontal lines
        canvas.drawLine(
            0f, (height / 3).toFloat(), width.toFloat(), (height / 3).toFloat(),
            paintLine
        )
        canvas.drawLine(
            0f, (2 * height / 3).toFloat(), width.toFloat(),
            (2 * height / 3).toFloat(), paintLine
        )

        // two vertical lines
        canvas.drawLine(
            (width / 3).toFloat(), 0f, (width / 3).toFloat(), height.toFloat(),
            paintLine
        )
        canvas.drawLine(
            (2 * width / 3).toFloat(), 0f, (2 * width / 3).toFloat(), height.toFloat(),
            paintLine
        )
    }


    private fun drawPlayers(canvas: Canvas) {
        for (i in 0..2) {
            for (j in 0..2) {
                if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CIRCLE) {
                    val centerX = (i * width / 3 + width / 6).toFloat()
                    val centerY = (j * height / 3 + height / 6).toFloat()
                    val radius = height / 6 - 2

                    canvas.drawCircle(centerX, centerY, radius.toFloat(), paintLine)
                } else if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CROSS) {
                    canvas.drawLine(
                        (i * width / 3).toFloat(), (j * height / 3).toFloat(),
                        ((i + 1) * width / 3).toFloat(),
                        ((j + 1) * height / 3).toFloat(), paintLineCross
                    )

                    canvas.drawLine(
                        ((i + 1) * width / 3).toFloat(), (j * height / 3).toFloat(),
                        (i * width / 3).toFloat(), ((j + 1) * height / 3).toFloat(), paintLineCross
                    )
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val tX = event.x.toInt() / (width / 3)
            val tY = event.y.toInt() / (height / 3)

            if (tX < 3 && tY < 3 && TicTacToeModel.getFieldContent(tX, tY) ==
                TicTacToeModel.EMPTY
            ) {

                TicTacToeModel.setFieldContent(tX, tY, TicTacToeModel.getNextPlayer())

                // érdemes itt vizsgálni hogy van-e győztes..
                val winnerStatus = TicTacToeModel.getWinner()
                if (winnerStatus != TicTacToeModel.NOT_FINISHED) {
                    (context as MainActivity).showText(context.resources.getString(R.string.text_end, winnerStatus))

                    (context as MainActivity).showDialogMessage("End: $winnerStatus")

                    invalidate()
                } else {
                    TicTacToeModel.changeNextPlayer()

                    var next = "O"
                    if (TicTacToeModel.getNextPlayer() == TicTacToeModel.CROSS) {
                        next = "X"
                    }
                    (context as MainActivity).showText("Next player is: $next")

                    invalidate()
                }

            }

        }

        return true
    }

    public fun resetGame() {
        TicTacToeModel.resetModel()
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = View.MeasureSpec.getSize(widthMeasureSpec)
        val h = View.MeasureSpec.getSize(heightMeasureSpec)
        val d = if (w == 0) h else if (h == 0) w else if (w < h) w else h
        setMeasuredDimension(d, d)
    }

}