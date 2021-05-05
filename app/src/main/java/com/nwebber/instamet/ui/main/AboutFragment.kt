package com.nwebber.instamet.ui.main

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.nwebber.instamet.InstaMetApp.Companion.APP_VERSION
import com.nwebber.instamet.R


class AboutFragment : Fragment() {

    private lateinit var nameTextView: TextView
    private lateinit var versionTextView: TextView
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        nameTextView = view.findViewById(R.id.app_name_textView)
        versionTextView = view.findViewById(R.id.version_textView)

        nameTextView.text = getString(R.string.app_name)
        versionTextView.text = "v $APP_VERSION"
        return view
    }

}