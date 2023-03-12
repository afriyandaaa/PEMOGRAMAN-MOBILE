package com.example.themooviedb.data

import com.example.themooviedb.data.database.dao.MostPopularDAO
import com.example.themooviedb.data.database.dao.PlayingNowDAO
import com.example.themooviedb.models.MostPopular
import com.example.themooviedb.models.MostPopularResult
import com.example.themooviedb.models.PlayingNow
import com.example.themooviedb.models.PlayingNowResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val playingNowDAO: PlayingNowDAO, private val mostPopularDAO: MostPopularDAO) {

    //Playing Now
    suspend fun insertPlayingNow(playingNow: List<PlayingNowResult>?) {
        playingNowDAO.insertPlayingNow(playingNow)
    }

    suspend fun getPlayingNow(): List<PlayingNowResult>? {
        return playingNowDAO.getPlayingNow()
    }

    suspend fun deletePlayingNow() {
        playingNowDAO.deletePlayingNow()
    }

    //Most Popular
    suspend fun insertMostPopular(mostPopular: List<MostPopularResult>?) {
        mostPopularDAO.insertMostPopular(mostPopular)
    }

    suspend fun getMostPopular(): List<MostPopularResult>? {
        return mostPopularDAO.getMostPopular()
    }

    suspend fun deleteMostPopular() {
        mostPopularDAO.deleteMostPopular()
    }

}