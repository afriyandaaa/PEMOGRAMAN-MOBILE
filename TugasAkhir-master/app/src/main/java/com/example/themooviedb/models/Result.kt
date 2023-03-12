package com.example.themooviedb.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Result(

    @SerializedName("overview")
    var overview: String?,

    @SerializedName("original_language")
    var original_language: String?,

    @SerializedName("original_title")
    var original_title: String?,

    @SerializedName("video")
    var video: String?,

    @SerializedName("title")
    var title: String?,

    @SerializedName("poster_path")
    var poster_path: String?,

    @SerializedName("backdrop_path")
    var backdrop_path: String?,

    @SerializedName("release_date")
    var release_date: String?,

    @SerializedName("popularity")
    var popularity: String?,
    @SerializedName("vote_average")
    var vote_average: String?,

    @SerializedName("id")
    var id: String?,

    @SerializedName("adult")
    var adult: String?,

    @SerializedName("vote_count")
    var vote_count: String?

): Serializable