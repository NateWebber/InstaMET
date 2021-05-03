package com.nwebber.instamet.ui.main.stockapi

import com.nwebber.instamet.ui.main.jokeapi.JokeAPI
import com.nwebber.instamet.ui.main.jokeapi.JokeResponse


import android.util.Log
import androidx.lifecycle.ViewModel
import com.nwebber.instamet.ui.main.jokeapi.StockAPI
import com.nwebber.instamet.ui.main.jokeapi.StockResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


private const val TAG = "StockViewModel"



class StockViewModel : ViewModel(){
    companion object{
        val stockApi: StockAPI by lazy {
            val retrofit = Retrofit.Builder().baseUrl("https://financialmodelingprep.com").addConverterFactory(GsonConverterFactory.create()).build()
            return@lazy retrofit.create(StockAPI::class.java)
        }

    }


    fun fetchApple(){

        val stockRequest: Call<StockResponse> = stockApi.getAppleStock("37f5c6a8a9b9458d72f11c4a944fd570")
        Log.d(TAG, stockRequest.request().url().toString()) //print url
        stockRequest.enqueue(object : Callback<StockResponse> {
            override fun onResponse(call: Call<StockResponse>, response: Response<StockResponse>) {
                Log.d(TAG, "Got a response!")
                val body = response.body()
                if (body != null){
                    Log.d(TAG, "Response has non-null body!")
                    Log.d(TAG, "Body: ${body.toString()}")
                    if (body.stock != null){
                        Log.d(TAG, "Response has non-null stock!")
                    }
                }
                if (response.isSuccessful){
                    Log.d(TAG, "Response isSuccessful = true!")
                }

                //Log.d(TAG, "Value: ${body?.toString()}")
                Log.d(TAG, "Symbol: ${body?.stock?.symbol.toString()}")

            }

            override fun onFailure(call: Call<StockResponse>, t: Throwable) {
                Log.d(TAG, "No response for Stock.")
            }
        })
    }
}

