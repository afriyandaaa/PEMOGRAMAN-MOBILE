package com.example.themooviedb.data

import com.example.themooviedb.data.network.TheMovieDBAPI
import com.example.themooviedb.models.MostPopular
import com.example.themooviedb.models.PlayingNow
import com.example.themooviedb.models.Video
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val theMovieDBAPI: TheMovieDBAPI) {

    //Playing Now
    suspend fun getPlayingNow(): Response<PlayingNow?> {
        return theMovieDBAPI.getPlayingNow()
    }

    //Most Popular
    suspend fun getMostPopular(): Response<MostPopular?> {
        return theMovieDBAPI.getMostPopular()
    }

    //Video
    suspend fun getVideo(id: String): Response<Video?> {
        return theMovieDBAPI.getVideo(id)
    }

}