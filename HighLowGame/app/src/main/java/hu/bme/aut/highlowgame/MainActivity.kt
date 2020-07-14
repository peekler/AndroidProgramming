package hu.bme.aut.highlowgame

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_GEN = "KEY_GEN"
    }

    private var generatedNum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnNew = Button(this)
        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutMain.addView(btnNew, lp)

        if (savedInstanceState != null && savedInstanceState.containsKey(MainActivity.KEY_GEN)) {
            generatedNum = savedInstanceState.getInt(MainActivity.KEY_GEN)
        } else {
            generateNewNumber()
        }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_restart) {
            generateNewNumber()
            tvResult.text = "New game started"
        } else if (item.itemId == R.id.action_exit) {
            finish()
        }

        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(MainActivity.KEY_GEN, generatedNum)
        super.onSaveInstanceState(outState)
    }

    private fun generateNewNumber() {
        generatedNum = Random(System.currentTimeMillis()).nextInt(10) // 0..2
    }

}