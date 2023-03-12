package com.example.themooviedb.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PlayingNow(

    @SerializedName("results")
    var results: List<PlayingNowResult>? = ArrayList()

): Serializable