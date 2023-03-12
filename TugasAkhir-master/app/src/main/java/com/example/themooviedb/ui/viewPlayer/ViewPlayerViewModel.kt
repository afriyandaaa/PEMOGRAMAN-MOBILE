package com.example.themooviedb.ui.viewPlayer

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.themooviedb.MyApplication
import com.example.themooviedb.data.Repository
import com.example.themooviedb.models.Video
import com.example.themooviedb.util.Features
import com.example.themooviedb.util.NetworkResult
import kotlinx.coroutines.launch

class ViewPlayerViewModel @ViewModelInject constructor(private val repository: Repository, application: Application): AndroidViewModel(application) {

    private var features: Features = Features()
    //Live data , jika data update maka langsung terupdate karena adanya live data
    var video: MutableLiveData<NetworkResult<Video?>> = MutableLiveData()

    fun getVideo(id: String) = viewModelScope.launch {
        getVideoSafeCall(id)
    }

    private suspend fun getVideoSafeCall(id: String) {
        video.value = NetworkResult.Loading()
        if (features.isConnected(MyApplication.context!!)){
            try {

                val response = repository.remote.getVideo(id)

                if(response.isSuccessful){
                    
                    video.value = NetworkResult.Success(response.body()!!)

                }else{

                    video.value = NetworkResult.Error("Error en la consulta.")

                }

            }catch (e: Exception){

                video.value = NetworkResult.Error("Error en la conexion a la ruta.")

            }

        }else{

            video.value = NetworkResult.Error("Error en la conexion a internet.")

        }
    }
}