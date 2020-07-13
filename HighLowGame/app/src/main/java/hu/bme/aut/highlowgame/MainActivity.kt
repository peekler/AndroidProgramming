package hu.bme.aut.highlowgame

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var generatedNum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnNew = Button(this)
        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutMain.addView(btnNew, lp)


        generateNewNumber()

        btnGuess.setOnClickListener {
            try {
                if (etGuess.text!!.isNotEmpty()) {
                    val myNum = etGuess.text.toString().toInt()

                    if (myNum == generatedNum) {
                        tvResult.text = "Congratulations!"
                    } else if (myNum < generatedNum) {
                        tvResult.text = "The number is higher"
                    } else {
                        tvResult.text = "The number is lower"
                    }
                } else {
                    etGuess.error = "This filed can not be empty"
                }
            } catch (e: Exception) {
                etGuess.error = "Error: ${e.message}"
            }
        }
    }

    private fun generateNewNumber() {
        generatedNum = Random(System.currentTimeMillis()).nextInt(10) // 0..2
    }

}