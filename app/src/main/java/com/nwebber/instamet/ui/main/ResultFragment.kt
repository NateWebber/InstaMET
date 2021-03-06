package com.nwebber.instamet.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.preference.PreferenceManager
import com.nwebber.instamet.R
import com.nwebber.instamet.ui.main.metapi.MetObject

import com.nwebber.instamet.ui.main.metapi.MetViewModel

import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import jp.wasabeef.picasso.transformations.GrayscaleTransformation
import jp.wasabeef.picasso.transformations.gpu.InvertFilterTransformation
import jp.wasabeef.picasso.transformations.gpu.PixelationFilterTransformation

private const val TAG = "ResultFragment"
class ResultFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()

    private val prefs: SharedPreferences by lazy{
        PreferenceManager.getDefaultSharedPreferences(this.activity)
    }

    private val metViewModel: MetViewModel by lazy{
        ViewModelProvider(this).get(MetViewModel::class.java)
    }

    private lateinit var imageView: ImageView

    private lateinit var titleText: TextView
    private lateinit var artistText: TextView
    private lateinit var dateText: TextView
    private lateinit var mediumText: TextView

    private lateinit var nextButton: Button

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        setHasOptionsMenu(true)

        imageView = view.findViewById(R.id.main_imageView)

        titleText = view.findViewById(R.id.art_title_textView)
        artistText = view.findViewById(R.id.art_artist_textView)
        dateText = view.findViewById(R.id.art_date_textView)
        mediumText = view.findViewById(R.id.art_medium_textView)

        nextButton = view.findViewById(R.id.next_button)

        sharedViewModel.search_query?.let { metViewModel.runSearchByKeyWord(it) }

        if (metViewModel.search_results == null){

        }

        nextButton.setOnClickListener {
            Log.d(TAG, "Searching for a random object!")
            metViewModel.search_results?.let { it1 -> metViewModel.fetchObjectByID(it1.random()) }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        metViewModel.object_title.observe(viewLifecycleOwner) {
            titleText.text = when(it){
                null, "" -> getString(R.string.unknown_title)
                else -> it
            }
        }
        metViewModel.artist_name.observe(viewLifecycleOwner) {
            artistText.text = when(it){
                null, "" -> getString(R.string.unknown_artist)
                else -> it
            }
        }
        metViewModel.begin_year.observe(viewLifecycleOwner) {
            dateText.text = when(it){
                null, 0 -> getString(R.string.unknown_date)
                else -> it.toString()
            }
        }
        metViewModel.object_medium.observe(viewLifecycleOwner) {
            mediumText.text = when(it){
                null, "" -> getString(R.string.unknown_medium)
                else -> it
            }
        }
        metViewModel.image_url.observe(viewLifecycleOwner) {
            when(it){
                null, "" -> Log.d(TAG, "No image found!")
                else -> loadImageURL(it)
            }
        }
    }


    private fun loadImageURL(url: String){
        val picasso = Picasso.get()
        val transformations = generateTransformations()
        if (transformations.isEmpty()){
            picasso.load(url).placeholder(R.drawable.instamet_icon).into(imageView)
        }
        else{
            picasso.load(url).transform(transformations).placeholder(R.drawable.instamet_icon).into(imageView)
        }
    }

    private fun generateTransformations() : MutableList<Transformation>{
        var returnList : MutableList<Transformation> = mutableListOf()
        if (prefs.getBoolean(getString(R.string.grayscale), false)){
            Log.d(TAG, "Grayscale!")
            returnList.add(GrayscaleTransformation())
        }
        if (prefs.getBoolean(getString(R.string.pixelate), false)){
            Log.d(TAG, "Pixelate!")
            returnList.add(PixelationFilterTransformation(context))
        }
        if (prefs.getBoolean(getString(R.string.invert), false)){
            Log.d(TAG, "Invert!")
            returnList.add(InvertFilterTransformation(context))
        }
        return returnList
    }

    companion object {
        fun newInstance() = ResultFragment()
    }
}