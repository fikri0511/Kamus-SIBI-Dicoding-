package com.dicoding.capstoneproject.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dicoding.capstoneproject.core.data.local.LocalDataSource
import com.dicoding.capstoneproject.core.data.remote.RemoteDataSource
import com.dicoding.capstoneproject.core.data.remote.network.ApiResponse
import com.dicoding.capstoneproject.core.data.remote.response.KamusResponse
import com.dicoding.capstoneproject.core.domain.model.Kamus
import com.dicoding.capstoneproject.core.domain.repository.IKamusRepository
import com.dicoding.capstoneproject.core.utils.AppExecutors
import com.dicoding.capstoneproject.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class KamusRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IKamusRepository {

    companion object {
        @Volatile
        private var instance: KamusRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): KamusRepository =
            instance ?: synchronized(this) {
                instance ?: KamusRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllKamus(): Flow<Resource<List<Kamus>>> =
        object : NetworkBoundResource<List<Kamus>, List<KamusResponse>>() {
            override fun loadFromDB(): Flow<List<Kamus>> {
                return localDataSource.getAllKamus().map{DataMapper.mapEntitiesToDomain(it)}
            }

            override fun shouldFetch(data: List<Kamus>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<KamusResponse>>> =
                remoteDataSource.getAllKamus()

            override suspend fun saveCallResult(data: List<KamusResponse>) {
                val kamusList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertKamus(kamusList)
            }
        }.asFlow()

    override fun getFavoriteKamus(): Flow<List<Kamus>> {
        return localDataSource.getFavoriteKamus().map { DataMapper.mapEntitiesToDomain(it) }

    }

    override fun setFavoriteKamus(kamus: Kamus, state: Boolean) {
        val kamusEntity = DataMapper.mapDomainToEntity(kamus)
        appExecutors.diskIO().execute { localDataSource.setFavoriteKamus(kamusEntity, state) }
    }
}

