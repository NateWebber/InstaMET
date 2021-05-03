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

    fun fetchObjectByID(){

        val metRequest: Call<MetObject> = metApi.getObjects("561553")
        Log.d(TAG, metRequest.request().url().toString()) //print url
        metRequest.enqueue(object : Callback<MetObject> {
            override fun onResponse(call: Call<MetObject>, response: Response<MetObject>) {
                Log.d(TAG, "Got a response!")
                val body : MetObject? = response.body()
                if (body != null){
                    Log.d(TAG, "Response has non-null body!")
                    Log.d(TAG, body.toString())

                    var objectID = body.objectID
                    Log.d(TAG, "objectID: $objectID")
                }


                Log.d(TAG, "ID should be 561553 and is: ${body?.objectID.toString()}")
                Log.d(TAG, "Primary Image should be https://images.metmuseum.org/CRDImages/eg/original/23.10.49_EGDP017143.jpg and is: ${body?.primaryImage.toString()}")
                Log.d(TAG, "objectName should be Amulet, ba-bird and is: ${body?.objectName.toString()}")
                Log.d(TAG, "Medium should be Gold sheet and is: ${body?.medium.toString()}")

            }

            override fun onFailure(call: Call<MetObject>, t: Throwable) {
                Log.d(TAG, "No response for MetObject.")
            }
        })
    }
    companion object{
        val metApi: MetAPI by lazy {
            //val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder().baseUrl("https://collectionapi.metmuseum.org").addConverterFactory(MoshiConverterFactory.create()).build()
            return@lazy retrofit.create(MetAPI::class.java)
        }

    }
}

