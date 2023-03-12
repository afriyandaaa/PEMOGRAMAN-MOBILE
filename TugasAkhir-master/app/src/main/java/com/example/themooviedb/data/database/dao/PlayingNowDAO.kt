package com.example.themooviedb.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themooviedb.models.MostPopularResult
import com.example.themooviedb.models.PlayingNow
import com.example.themooviedb.models.PlayingNowResult
import kotlinx.coroutines.flow.Flow
//DAO untuk mengakses database aplikasi
@Dao
interface PlayingNowDAO {
    //menyisipkan satu atau beberapa objek User ke dalam database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayingNow(playingNow: List<PlayingNowResult>?)
    //menentukan metode yang menampilkan informasi semua pengguna
    @Query("SELECT * FROM PlayingNowResult")
    suspend fun getPlayingNow(): List<PlayingNowResult>?
    //delete
    @Query("DELETE FROM PlayingNowResult")
    suspend fun deletePlayingNow()

}