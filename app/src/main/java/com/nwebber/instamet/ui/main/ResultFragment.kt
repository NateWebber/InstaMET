package com.nwebber.instamet.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.nwebber.instamet.R
import com.nwebber.instamet.ui.main.metapi.MetObject

import com.nwebber.instamet.ui.main.metapi.MetViewModel

import com.squareup.picasso.Picasso


/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "ResultFragment"
class ResultFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()

    private val metViewModel: MetViewModel by lazy{
        ViewModelProvider(this).get(MetViewModel::class.java)
    }

    //var current_object : MetObject? = null

    private lateinit var imageView: ImageView

    private lateinit var titleText: TextView
    private lateinit var artistText: TextView
    private lateinit var dateText: TextView
    private lateinit var mediumText: TextView


    private lateinit var nextButton: Button
    private lateinit var backButton: Button

    private var current_list_index : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        current_list_index = 0


        imageView = view.findViewById(R.id.main_imageView)

        titleText = view.findViewById(R.id.art_title_textView)
        artistText = view.findViewById(R.id.art_artist_textView)
        dateText = view.findViewById(R.id.art_date_textView)
        mediumText = view.findViewById(R.id.art_medium_textView)

        nextButton = view.findViewById(R.id.next_button)
        backButton = view.findViewById(R.id.back_button)

        sharedViewModel.search_query?.let { metViewModel.runSearchByKeyWord(it) }

        nextButton.setOnClickListener {
            updateUI()
        }

        return view
    }


    private fun updateUI(){
        Log.d(TAG, "Updating UI")

        Picasso.get().load(metViewModel.current_object?.primaryImage).into(imageView)

        if (metViewModel.current_object?.title == null || metViewModel.current_object?.title == ""){
            titleText.text = getString(R.string.unknown_title)
        }
        else{
            titleText.text = metViewModel.current_object?.title
        }

        if (metViewModel.current_object?.artistName == null || metViewModel.current_object?.artistName == ""){
            artistText.text = getString(R.string.unknown_artist)
        }
        else{
            artistText.text = metViewModel.current_object?.artistName
        }
        if (metViewModel.current_object?.beginYear == null || metViewModel.current_object?.beginYear == 0){
            dateText.text = getString(R.string.unknown_date)
        }
        else{
            dateText.text = metViewModel.current_object?.beginYear.toString() //TODO figure this out
        }
        if (metViewModel.current_object?.medium == null || metViewModel.current_object?.medium == ""){
            mediumText.text = getString(R.string.unknown_medium)
        }
        else{
            mediumText.text = metViewModel.current_object?.medium
        }


    }

    companion object {
        fun newInstance() = ResultFragment()
    }
}