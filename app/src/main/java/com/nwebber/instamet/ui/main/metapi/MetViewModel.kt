package com.nwebber.instamet.ui.main.metapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MetViewModel"



class MetViewModel : ViewModel(){
    companion object{
        val metApi: MetAPI by lazy {
            val retrofit = Retrofit.Builder().baseUrl("https://collectionapi.metmuseum.org").addConverterFactory(GsonConverterFactory.create()).build()
            return@lazy retrofit.create(MetAPI::class.java)
        }
    }

    private val _query = MutableLiveData<String>()

    private val _objects = MutableLiveData<Array<Int>>()
    var objects: LiveData<Array<Int>> = _objects

    private val _total = MutableLiveData<Int>()
    var total: LiveData<Int> = _total

    fun fetchObjectsByQuery(hasImages: String, query: String){
        _query.value = query

        val metRequest: Call<QueryResponse> = metApi.getObjects("true", query)
        Log.d(TAG, metRequest.request().url().toString())
        metRequest.enqueue(object : Callback<QueryResponse> {
            override fun onResponse(call: Call<QueryResponse>, response: Response<QueryResponse>) {

                _objects.value = arrayOf()
                _total.value = 0

                response.body()?.objects?.let {
                    _objects.value = it.objectIDs
                    _total.value = it.total

                }
                Log.d(TAG, _total.value.toString())
            }

            override fun onFailure(call: Call<QueryResponse>, t: Throwable) {
                Log.d(TAG, "No response for QueryObjects.")
            }
        })
    }
}

