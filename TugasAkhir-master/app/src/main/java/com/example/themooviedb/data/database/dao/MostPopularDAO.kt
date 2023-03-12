package com.example.themooviedb.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themooviedb.models.MostPopular
import com.example.themooviedb.models.MostPopularResult
import kotlinx.coroutines.flow.Flow

//DAO untuk mengakses database aplikasi
@Dao
interface MostPopularDAO {
    //menyisipkan satu atau beberapa objek User ke dalam database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMostPopular(mostPopular: List<MostPopularResult>?)

    //menentukan metode yang menampilkan informasi semua pengguna
    @Query("SELECT * FROM MostPopularResult")
    suspend fun getMostPopular(): List<MostPopularResult>?
    //delete
    @Query("DELETE FROM MostPopularResult")
    suspend fun deleteMostPopular()

}
