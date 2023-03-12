package com.example.themooviedb.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themooviedb.data.database.dao.MostPopularDAO
import com.example.themooviedb.data.database.dao.PlayingNowDAO
import com.example.themooviedb.models.MostPopularResult
import com.example.themooviedb.models.PlayingNowResult

//database yang memanggil class PlayingNowDAO ,MostPopularDAO
@Database(entities = [PlayingNowResult::class, MostPopularResult::class], version = 1, exportSchema = false)
abstract class TheMovieDBDatabase: RoomDatabase() {

    abstract fun playingNowDAO(): PlayingNowDAO

    abstract fun mostPopularDAO(): MostPopularDAO

}