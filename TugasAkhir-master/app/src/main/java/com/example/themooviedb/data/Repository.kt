package com.example.themooviedb.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

//mengambil data dari remoteDataSource,localDataSource jika tidak ada jarngan data masih ada
@ActivityRetainedScoped
class Repository @Inject constructor(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) {

    val remote = remoteDataSource

    val local = localDataSource

}