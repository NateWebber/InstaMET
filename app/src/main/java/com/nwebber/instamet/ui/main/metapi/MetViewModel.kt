package com.nwebber.instamet.ui.main.metapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body


private const val TAG = "MetViewModel"



class MetViewModel : ViewModel(){
    companion object{
        val metApi: MetAPI by lazy {
            val retrofit = Retrofit.Builder().baseUrl("https://collectionapi.metmuseum.org").addConverterFactory(GsonConverterFactory.create()).build()
            return@lazy retrofit.create(MetAPI::class.java)
        }
    }


    fun fetchObjectsByQuery(hasImages: String, query: String){

        val metRequest: Call<MetObjectResponse> = metApi.getObjects("561553")
        Log.d(TAG, metRequest.request().url().toString()) //print url
        metRequest.enqueue(object : Callback<MetObjectResponse> {
            override fun onResponse(call: Call<MetObjectResponse>, response: Response<MetObjectResponse>) {
                Log.d(TAG, "Got a response!")
                val body = response.body()
                if (body != null){
                    Log.d(TAG, "Response has non-null body!")
                    if (response.body()?.metObject != null){
                        Log.d(TAG, "Response has non-null metobject!")
                    }
                }


                Log.d(TAG, "ID should be 561553 and is: ${body?.metObject?.objectId.toString()}")
                Log.d(TAG, "Primary Image should be https://images.metmuseum.org/CRDImages/eg/original/23.10.49_EGDP017143.jpg and is: ${body?.metObject?.primaryImage.toString()}")
                Log.d(TAG, "objectName should be Amulet, ba-bird and is: ${body?.metObject?.objectName.toString()}")
                Log.d(TAG, "Medium should be Gold sheet and is: ${body?.metObject?.objectId.toString()}")

            }

            override fun onFailure(call: Call<MetObjectResponse>, t: Throwable) {
                Log.d(TAG, "No response for MetObject.")
            }
        })
    }
}

