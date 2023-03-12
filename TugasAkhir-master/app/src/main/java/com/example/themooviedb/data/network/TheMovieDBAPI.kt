package com.example.themooviedb.data.network

import com.example.themooviedb.models.MostPopular
import com.example.themooviedb.models.PlayingNow
import com.example.themooviedb.models.Video
import com.example.themooviedb.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path

interface TheMovieDBAPI {
    // mendeklarasikan antarmuka yang menentukan metode URL dan jenis respons untuk operasi jaringan
    @GET("now_playing?api_key=${API_KEY}&language=es-MX&page=1")
    suspend fun getPlayingNow(): Response<PlayingNow?>

    @GET("popular?api_key=${API_KEY}&language=es-MX&page=1")
    suspend fun getMostPopular(): Response<MostPopular?>

    @GET("{id}/videos?api_key=${API_KEY}&language=es-MX")
    suspend fun getVideo(@Path("id") id: String): Response<Video?>

}