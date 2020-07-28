package hu.bme.aut.viewdemos

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val cityNames = arrayOf("Budapest", "Bukarest", "BuDemo", "New York", "New Jersey", "New Delhi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRadioGroup()
        initSpinner()

        initAutoComplete()

        initWebView()
    }

    private fun initWebView() {
        myWebView.settings.builtInZoomControls = true
        myWebView.loadUrl("https://www.aut.bme.hu/")
    }

    private fun initAutoComplete() {
        val cityAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            cityNames
        )
        autoCity.setAdapter(cityAdapter)
    }

    private fun initSpinner() {
        var fruitsAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.fruits_array,
                android.R.layout.simple_spinner_item
        )
        fruitsAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        )
        spinnerFruits.adapter = fruitsAdapter
        spinnerFruits.onItemSelectedListener = this
    }

    private fun initRadioGroup() {
        rbtnWhite.setOnClickListener {
            layoutMain.setBackgroundColor(Color.WHITE)
        }
        rbtnGreen.setOnClickListener {
            layoutMain.setBackgroundColor(Color.GREEN)
        }
        rbtnRed.setOnClickListener {
            layoutMain.setBackgroundColor(Color.RED)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //
    }


    var selectEnabled = false

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        if (selectEnabled) {
            Toast.makeText(
                this, spinnerFruits.getItemAtPosition(position).toString(),
                Toast.LENGTH_LONG
            ).show()
        } else {
            selectEnabled = true
        }
    }
}