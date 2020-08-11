package hu.bme.aut.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.fragmentdemo.fragment.FragmentDetails
import hu.bme.aut.fragmentdemo.fragment.FragmentMain
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMain.setOnClickListener {
            showFragmentByTag(FragmentMain.TAG)
        }
        btnDetails.setOnClickListener {
            showFragmentByTag(FragmentDetails.TAG)
        }
    }

    public fun showFragmentByTag(tag: String) {
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            if (FragmentMain.TAG == tag) {
                fragment = FragmentMain()
            } else if (FragmentDetails.TAG == tag) {
                fragment = FragmentDetails()
            }
        }

        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.layoutContainer, fragment, tag)

            ft.addToBackStack(null) // add fragment transaction to the back stack

            ft.commit()
        }
    }

}