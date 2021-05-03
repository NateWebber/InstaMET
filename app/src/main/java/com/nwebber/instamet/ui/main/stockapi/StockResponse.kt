package com.nwebber.instamet.ui.main.jokeapi
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Stock (

        @field:Json(name = "symbol") var symbol: String

)

data class StockResponse (
        val stock: Stock
)