package com.example.themooviedb.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Video(

    @SerializedName("results")
    var results: List<VideoResult>? = ArrayList()

): Serializable