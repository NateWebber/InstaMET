package com.nwebber.instamet.ui.main.metapi
import com.google.gson.annotations.SerializedName

class MetObject {

    @SerializedName("objectID")
    var objectID : Int = 0
    @SerializedName("primaryImage")
    var primaryImage: String = ""
    @SerializedName("objectName")
    var objectName: String = ""
    @SerializedName("medium")
    var medium: String =""
}

class MetObjectResponse {
    var metObject: MetObject? = null
}