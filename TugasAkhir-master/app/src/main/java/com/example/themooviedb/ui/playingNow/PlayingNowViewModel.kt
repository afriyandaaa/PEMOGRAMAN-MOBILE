package com.example.themooviedb.ui.playingNow

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.themooviedb.MyApplication
import com.example.themooviedb.data.Repository
import com.example.themooviedb.models.PlayingNowResult
import com.example.themooviedb.util.Features
import com.example.themooviedb.util.NetworkResult
import kotlinx.coroutines.launch

class PlayingNowViewModel @ViewModelInject constructor(private val repository: Repository, application: Application): AndroidViewModel(application) {

    private var features: Features = Features()
    //Live data , jika data update maka langsung terupdate karena adanya live data
    var playingNow: MutableLiveData<NetworkResult<List<PlayingNowResult>?>> = MutableLiveData()

    fun getPlayingNow() = viewModelScope.launch {
        getPlayingNowSafeCall()
    }

    private suspend fun getPlayingNowSafeCall() {
        playingNow.value = NetworkResult.Loading()
        if (features.isConnected(MyApplication.context!!)){
            try {

                val response = repository.remote.getPlayingNow()

                if(response.isSuccessful){

                    repository.local.deletePlayingNow()

                    repository.local.insertPlayingNow(response.body()!!.results)

                    playingNow.value = NetworkResult.Success(response.body()!!.results)

                }else{

                    val responseRoom = repository.local.getPlayingNow()

                    playingNow.value = NetworkResult.Error("Error en la consulta.", responseRoom)

                }

            }catch (e: Exception){

                val responseRoom = repository.local.getPlayingNow()

                playingNow.value = NetworkResult.Error("Error en la conexion a la ruta.", responseRoom)

            }

        }else{

            val responseRoom = repository.local.getPlayingNow()

            playingNow.value = NetworkResult.Error("Error en la conexion a internet.", responseRoom)

        }
    }

}