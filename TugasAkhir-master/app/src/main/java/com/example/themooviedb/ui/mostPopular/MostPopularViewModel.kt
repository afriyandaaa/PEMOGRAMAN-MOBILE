package com.example.themooviedb.ui.mostPopular

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.themooviedb.MyApplication
import com.example.themooviedb.data.Repository
import com.example.themooviedb.models.MostPopularResult
import com.example.themooviedb.util.Features
import com.example.themooviedb.util.NetworkResult
import kotlinx.coroutines.launch

class MostPopularViewModel @ViewModelInject constructor(private val repository: Repository, application: Application): AndroidViewModel(application) {

    private var features: Features = Features()
    //Live data , jika data update maka langsung terupdate karena adanya live data
    var mostPopular: MutableLiveData<NetworkResult<List<MostPopularResult>?>> = MutableLiveData()

    fun getMostPopular() = viewModelScope.launch {
        getMostPopularSafeCall()
    }

    private suspend fun getMostPopularSafeCall() {
        mostPopular.value = NetworkResult.Loading()
        if (features.isConnected(MyApplication.context!!)){
            try {

                val response = repository.remote.getMostPopular()

                if(response.isSuccessful){

                    repository.local.deleteMostPopular()

                    repository.local.insertMostPopular(response.body()!!.results)

                    mostPopular.value = NetworkResult.Success(response.body()!!.results)

                }else{

                    val responseRoom = repository.local.getMostPopular()

                    mostPopular.value = NetworkResult.Error("Error en la consulta.", responseRoom)

                }

            }catch (e: Exception){

                val responseRoom = repository.local.getMostPopular()

                mostPopular.value = NetworkResult.Error("Error en la conexion a la ruta.", responseRoom)

            }

        }else{

            val responseRoom = repository.local.getMostPopular()

            mostPopular.value = NetworkResult.Error("Error en la conexion a internet.", responseRoom)

        }
    }

}
