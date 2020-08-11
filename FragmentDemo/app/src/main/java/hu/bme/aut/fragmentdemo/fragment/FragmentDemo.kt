package hu.bme.aut.fragmentdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import hu.bme.aut.fragmentdemo.R
import kotlinx.android.synthetic.main.fragment_demo.view.*

class FragmentDemo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_demo, container, false)

        rootView.btnDemo.setOnClickListener {
            Toast.makeText(activity, "Demo", Toast.LENGTH_LONG).show()
        }

        return rootView
    }

}