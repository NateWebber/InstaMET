package com.nwebber.instamet.ui.main.metapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MetAPI {
    @GET("public/collection/v1/objects/{id}")
    fun getObject(
        @Path("id") id : String
    ): Call<MetObject>

    @GET("public/collection/v1/search")
    fun searchObjects(
            @Query("q") query : String,
            @Query("hasImages") hasImages : String
    ): Call<MetSearch>
}

