package com.nwebber.instamet.ui.main.metapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MetAPI {
    @GET("/public/collection/v1/search")
    fun getObjects(
        @Query("hasImages") hasImages: String,
        @Query("q") q: String
    ): Call<QueryResponse>
}