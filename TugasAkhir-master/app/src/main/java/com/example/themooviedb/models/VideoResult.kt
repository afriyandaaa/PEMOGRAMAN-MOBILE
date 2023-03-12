package com.example.themooviedb.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class VideoResult(

    @SerializedName("name")
    var name: String?,

    @SerializedName("key")
    var key: String?,

    @SerializedName("site")
    var site: String?

): Serializable