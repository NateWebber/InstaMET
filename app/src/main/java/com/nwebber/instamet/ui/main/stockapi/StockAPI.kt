package com.nwebber.instamet.ui.main.jokeapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface StockAPI {
    @GET("api/v3/profile/AAPL")
    fun getAppleStock(
            @Query("apikey") apikey: String
    ): Call<StockResponse>
}