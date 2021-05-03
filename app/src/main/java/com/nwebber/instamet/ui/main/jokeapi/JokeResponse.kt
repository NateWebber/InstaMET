package com.nwebber.instamet.ui.main.jokeapi
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Joke (

    @field:Json(name = "category") var category: String

)

data class JokeResponse (
    val joke: Joke
)