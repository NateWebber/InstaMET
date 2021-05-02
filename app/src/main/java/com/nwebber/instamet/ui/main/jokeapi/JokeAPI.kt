package com.nwebber.instamet.ui.main.jokeapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface JokeAPI {
    @GET("joke/Any")
    fun getAnyJoke(
    ): Call<JokeResponse>
}