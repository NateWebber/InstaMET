package com.nwebber.instamet.ui.main.metapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body


private const val TAG = "MetViewModel"



class MetViewModel : ViewModel(){

    fun fetchObjectByID(id : String){
        val metRequest: Call<MetObject> = metApi.getObject(id)
        Log.d(TAG, metRequest.request().url().toString()) //print url
        metRequest.enqueue(object : Callback<MetObject> {
            override fun onResponse(call: Call<MetObject>, response: Response<MetObject>) {
                Log.d(TAG, "Got a response!")
                val body : MetObject? = response.body()
                if (body != null){
                    Log.d(TAG, "ID: ${body?.objectID}")
                    Log.d(TAG, "Primary Image: ${body?.primaryImage}")
                    Log.d(TAG, "objectName: ${body?.objectName}")
                    Log.d(TAG, "Medium: ${body?.medium}")
                }
            }
            override fun onFailure(call: Call<MetObject>, t: Throwable) {
                Log.d(TAG, "No response for MetObject.")
            }
        })
    }

    fun runSearchByKeyWord(keyword : String){
        val metRequest: Call<MetSearch> = metApi.searchObjects(keyword, "true")
        Log.d(TAG, metRequest.request().url().toString())
        metRequest.enqueue(object : Callback<MetSearch>{
            override fun onResponse(call: Call<MetSearch>, response: Response<MetSearch>) {
                Log.d(TAG, "Got a response!")
                val body : MetSearch? = response.body()
                if (body != null){
                    Log.d(TAG, "Total: ${body?.total}")
                    Log.d(TAG, "IDs: ${body?.objectIDs}") //might have to comment this one out later
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

