package com.nwebber.instamet.ui.main.jokeapi

import android.util.Log
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val TAG = "JokeViewModel"



class JokeViewModel : ViewModel(){
    companion object{
        val jokeApi: JokeAPI by lazy {
            val retrofit = Retrofit.Builder().baseUrl("https://v2.jokeapi.dev").addConverterFactory(GsonConverterFactory.create()).build()
            return@lazy retrofit.create(JokeAPI::class.java)
        }

    }


    fun fetchAnyJoke(){

        val jokeRequest: Call<JokeResponse> = jokeApi.getAnyJoke()
        Log.d(TAG, jokeRequest.request().url().toString()) //print url
        jokeRequest.enqueue(object : Callback<JokeResponse> {
            override fun onResponse(call: Call<JokeResponse>, response: Response<JokeResponse>) {
                Log.d(TAG, "Got a response!")
                val body = response.body()
                if (body != null){
                    Log.d(TAG, "Response has non-null body!")
                    Log.d(TAG, "Body: ${body.toString()}")
                    if (response.body()?.joke != null){
                        Log.d(TAG, "Response has non-null joke!")
                    }
                }
                if (response.isSuccessful){
                    Log.d(TAG, "Response isSuccessful = true!")
                }

                //Log.d(TAG, "Value: ${body?.toString()}")
                Log.d(TAG, "Category: ${body?.joke?.category.toString()}")

            }

            override fun onFailure(call: Call<JokeResponse>, t: Throwable) {
                Log.d(TAG, "No response for Joke.")
            }
        })
    }
}

