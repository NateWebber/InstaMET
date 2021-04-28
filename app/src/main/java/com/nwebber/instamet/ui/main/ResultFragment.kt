package com.nwebber.instamet.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.nwebber.instamet.R
import com.squareup.picasso.Picasso


/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()

    private lateinit var textView : TextView
    private lateinit var imageView: ImageView

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
        return view
    }

    companion object {
        fun newInstance() = ResultFragment()
    }
}