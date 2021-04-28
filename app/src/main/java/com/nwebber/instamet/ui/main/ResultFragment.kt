package com.nwebber.instamet.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.nwebber.instamet.R


/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()

private lateinit var textView : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        textView = view.findViewById(R.id.test_text)
        textView.text = sharedViewModel.search_query

        return view
    }

    companion object {
        fun newInstance() = ResultFragment()
    }
}