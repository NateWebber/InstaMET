package com.nwebber.instamet.ui.main

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.nwebber.instamet.R

private const val TAG = "MainViewModel"



class MainViewModel : ViewModel() {
    var search_query : String? = null

    val optionNameList = listOf(R.string.grayscale, R.string.pixelate, R.string.invert)

    private val _optionVocab = MutableLiveData<List<Int>>()
    var optionVocab : LiveData<List<Int>> = _optionVocab

    init{
        _optionVocab.value = optionNameList
        Log.d(TAG, "Options: $optionNameList")
        optionVocab = _optionVocab
    }

}