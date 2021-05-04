package com.nwebber.instamet.ui.main

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.nwebber.instamet.R
import com.nwebber.instamet.ui.main.MainFragment.Companion.newInstance

private const val TAG = "MainFragment"

class MainFragment : Fragment(), AlertDialogFragment.ProceedListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var searchButton: Button
    private lateinit var inputField: EditText
    private lateinit var main_view: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view =  inflater.inflate(R.layout.main_fragment, container, false)
        //setHasOptionsMenu(true)
        inputField = view.findViewById(R.id.keyword_input_textView)
        searchButton = view.findViewById(R.id.search_button)

        searchButton.setOnClickListener {
            var input : String = inputField.text.toString()
            if ((input != null) and (input != "Keyword") and (input != "")){ //ensure that there's an actual input
                sharedViewModel.search_query = input
                view.findNavController().navigate(R.id.action_mainFragment_to_resultFragment)
            }
            else{
                Log.d(TAG, "Showing alert!")
                val dialog = AlertDialogFragment.newInstance(this)
                dialog.show(childFragmentManager, "dialog")
            }
        }

        main_view = view
        return view
    }

    override fun proceedSearch() {
        Log.d(TAG, "Proceeding with potentially bad keyword!")
        sharedViewModel.search_query = inputField.text.toString()
        main_view.findNavController().navigate(R.id.action_mainFragment_to_resultFragment)
    }


}