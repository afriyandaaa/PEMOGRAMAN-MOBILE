package com.example.themooviedb.di

import android.content.Context
import androidx.room.Room
import com.example.themooviedb.data.database.TheMovieDBDatabase
import com.example.themooviedb.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context, TheMovieDBDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun playingNowDAO(database: TheMovieDBDatabase) = database.playingNowDAO()

    @Singleton
    @Provides
    fun mostPopularDAO(database: TheMovieDBDatabase) = database.mostPopularDAO()

}