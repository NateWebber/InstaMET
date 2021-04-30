package com.nwebber.instamet.ui.main.metapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MetAPI {
    @GET("/public/collection/v1/objects/{id}")
    fun getObjects(
        @Path("id") id : String
    ): Call<MetObjectResponse>
}