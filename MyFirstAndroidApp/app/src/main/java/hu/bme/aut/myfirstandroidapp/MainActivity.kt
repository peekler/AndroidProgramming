package hu.bme.aut.myfirstandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initItems()
    }

    private fun initItems() {
        var rand = Random()

        // DRY - Do not Repeat Yourself
        btnTime.setOnClickListener {
            if (etData.text.isNotEmpty()) {
                var num = rand.nextInt(100)
                var currentTime = "$num ${etData.text.toString()} ${Date(System.currentTimeMillis()).toString()}"
                tvData.text = currentTime

                Toast.makeText(this,
                        currentTime,
                        Toast.LENGTH_LONG).show()
            } else {
                etData.error = "Ez nem lehet üres"
            }
        }
    }

}