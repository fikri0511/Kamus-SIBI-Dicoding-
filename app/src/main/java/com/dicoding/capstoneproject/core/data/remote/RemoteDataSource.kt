package com.dicoding.capstoneproject.core.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.capstoneproject.core.data.remote.network.ApiResponse
import com.dicoding.capstoneproject.core.data.remote.network.ApiService
import com.dicoding.capstoneproject.core.data.remote.response.KamusResponse
import com.dicoding.capstoneproject.core.data.remote.response.ListKamusResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllKamus(): Flow<ApiResponse<List<KamusResponse>>> {
        return flow {
            try {
                val respons = apiService.getList()
                val data=respons.kata
                if (data.isNotEmpty()){
                    emit(ApiResponse.Success(respons.kata))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource",e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

