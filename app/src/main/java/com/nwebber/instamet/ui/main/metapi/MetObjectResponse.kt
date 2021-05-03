package com.nwebber.instamet.ui.main.metapi

import com.squareup.moshi.Json


data class MetObject (
        @Json(name = "objectID") var objectID: Int,
        @Json(name = "primaryImage") var primaryImage: String,
        @Json(name = "objectName") var objectName: String,
        @Json(name = "medium") var medium: String
)

/*data class MetObjectResponse (
    val metObject: MetObject
)*/