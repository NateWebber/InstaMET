package com.nwebber.instamet.ui.main.metapi

import com.squareup.moshi.Json


data class MetObject (
        @Json(name = "objectID") var objectID: Int,
        @Json(name = "primaryImage") var primaryImage: String,
        @Json(name = "title") var title: String,
        @Json(name = "medium") var medium: String,
        @Json(name = "artistDisplayName") var artistDisplayName: String,
        @Json(name = "objectBeginDate") var objectBeginDate: Int
        //@Json(name = "objectEndDate") var objectEndDate: Int

)

data class MetSearch (
        @Json(name = "total") var total: Int,
        @Json(name = "objectIDs") var objectIDs: List<Int>
        )
