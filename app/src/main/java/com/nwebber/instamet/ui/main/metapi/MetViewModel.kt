package com.nwebber.instamet.ui.main.metapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nwebber.instamet.ui.main.ResultFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body


private const val TAG = "MetViewModel"



class MetViewModel : ViewModel(){

    var search_results : List<Int>? = null
    var current_object : MetObject? = null

    private val _image_url = MutableLiveData<String>()
    var image_url : LiveData<String> = _image_url

    private val _object_title = MutableLiveData<String>()
    var object_title : LiveData<String> = _object_title

    private val _artist_name = MutableLiveData<String>()
    var artist_name : LiveData<String> = _artist_name

    private val _begin_year = MutableLiveData<Int>()
    var begin_year : LiveData<Int> = _begin_year

    private val _object_medium = MutableLiveData<String>()
    var object_medium : LiveData<String> = _object_medium

    fun fetchObjectByID(id : Int){
        val metRequest: Call<MetObject> = metApi.getObject(id.toString())
        Log.d(TAG, metRequest.request().url().toString()) //print url
        metRequest.enqueue(object : Callback<MetObject> {
            override fun onResponse(call: Call<MetObject>, response: Response<MetObject>) {
                Log.d(TAG, "Got a response for Object!")
                val body : MetObject? = response.body()

                _image_url.value = ""
                _object_title.value = ""
                _artist_name.value = ""
                _begin_year.value = 0
                _object_medium.value = ""

                body?.let{
                    Log.d(TAG, "ID: ${it.objectID}")
                    Log.d(TAG, "Primary Image: ${it.primaryImage}")
                    Log.d(TAG, "Title: ${it.title}")
                    Log.d(TAG, "Artist: ${it.artistDisplayName}")
                    Log.d(TAG, "Year: ${it.objectBeginDate}")
                    Log.d(TAG, "Medium: ${it.medium}")

                    _image_url.value = it.primaryImage
                    _object_title.value = it.title
                    _artist_name.value = it.artistDisplayName
                    _begin_year.value = it.objectBeginDate
                    _object_medium.value = it.medium
                }
            }
            override fun onFailure(call: Call<MetObject>, t: Throwable) {
                Log.d(TAG, "No response for MetObject.")
            }
        })
    }

    fun runSearchByKeyWord(keyword : String){
        val metRequest: Call<MetSearch> = metApi.searchObjects(keyword, "true", "true")
        Log.d(TAG, metRequest.request().url().toString())
        metRequest.enqueue(object : Callback<MetSearch>{
            override fun onResponse(call: Call<MetSearch>, response: Response<MetSearch>) {
                Log.d(TAG, "Got a response for search!")
                val body : MetSearch? = response.body()
                if (body != null){
                    Log.d(TAG, "Total: ${body?.total}")
                    Log.d(TAG, "IDs: ${body?.objectIDs}") //might have to comment this one out later
                    search_results = null
                    search_results = body.objectIDs
                    fetchObjectByID(search_results!![0])

                }
            }

            override fun onFailure(call: Call<MetSearch>, t: Throwable) {
                Log.d(TAG, "No response for MetSearch.")
            }
        })
    }

    companion object{
        val metApi: MetAPI by lazy {
            val retrofit = Retrofit.Builder().baseUrl("https://collectionapi.metmuseum.org").addConverterFactory(MoshiConverterFactory.create()).build()
            return@lazy retrofit.create(MetAPI::class.java)
        }

    }
}

