package com.nwebber.instamet.ui.main.metapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface MetAPI {
    @GET("public/collection/v1/objects/{id}")
    fun getObjects(
        @Path("id") id : String
    ): Call<MetObject>
}