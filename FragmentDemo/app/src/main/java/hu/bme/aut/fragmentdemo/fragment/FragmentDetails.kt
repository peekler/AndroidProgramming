package hu.bme.aut.fragmentdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import hu.bme.aut.fragmentdemo.R

class FragmentDetails : Fragment() {

    companion object {
        const val TAG = "TAG_FRAGMENT_DETAILS"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_details, container, false)
        return rootView
    }

}