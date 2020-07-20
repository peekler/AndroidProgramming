package hu.bme.aut.tictactoe

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hu.bme.aut.tictactoe.model.TicTacToeModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnReset.setOnClickListener {
            ticView.resetGame()

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    public fun showText(text: String) {
        tvData.text = text
    }

    public fun showDialogMessage(text: String) {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("TicTacToe Dialog")
        builder.setMessage(text)
        builder.setPositiveButton("YES"){dialog, which ->
            // Do something when user press the positive button
            Toast.makeText(applicationContext,"Ok",Toast.LENGTH_SHORT).show()

        }
        // Display a negative button on alert dialog
        builder.setNegativeButton("No"){dialog,which ->
            Toast.makeText(applicationContext,"No",Toast.LENGTH_SHORT).show()
        }

        builder.show()
    }
}