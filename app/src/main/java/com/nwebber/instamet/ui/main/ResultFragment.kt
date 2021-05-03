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
import com.nwebber.instamet.ui.main.jokeapi.JokeViewModel
import com.nwebber.instamet.ui.main.metapi.MetViewModel
import com.nwebber.instamet.ui.main.stockapi.StockViewModel
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
    private val jokeViewModel: JokeViewModel by lazy{
        ViewModelProvider(this).get(JokeViewModel::class.java)
    }
    private val stockViewModel: StockViewModel by lazy{
        ViewModelProvider(this).get(StockViewModel::class.java)
    }

    private lateinit var textView : TextView
    private lateinit var imageView: ImageView
    private lateinit var testButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        textView = view.findViewById(R.id.test_text)
        textView.text = sharedViewModel.search_query

        var imageUrl : String = "https://nas-national-prod.s3.amazonaws.com/aud_gbbc-2016_dark-eyed-junco_34384_kk_oh_photo-michele-black.jpg"
        imageView = view.findViewById(R.id.test_imageView)
        Picasso.get().load(imageUrl).into(imageView)

        testButton = view.findViewById(R.id.test_button)
        testButton.setOnClickListener {
            if (sharedViewModel.search_query != null){
                //var completedQuery: String  = "?hasImages=true&q=${sharedViewModel.search_query}"
                //Log.d(TAG, "Completed Query: $completedQuery")
                metViewModel.fetchObjectByID()
            }
        }

        return view
    }

    companion object {
        fun newInstance() = ResultFragment()
    }
}