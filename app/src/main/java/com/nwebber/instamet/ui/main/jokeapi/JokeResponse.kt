package com.nwebber.instamet.ui.main.jokeapi
import com.google.gson.annotations.SerializedName

data class Joke (

    val category : String = ""

)

data class JokeResponse (
    val joke: Joke? = null
)