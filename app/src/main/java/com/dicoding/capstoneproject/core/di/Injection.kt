//package com.dicoding.capstoneproject.core.di
//
//import android.content.Context
//import com.dicoding.capstoneproject.core.data.KamusRepository
//import com.dicoding.capstoneproject.core.data.local.LocalDataSource
//import com.dicoding.capstoneproject.core.data.local.room.KamusDatabase
//import com.dicoding.capstoneproject.core.data.remote.RemoteDataSource
//import com.dicoding.capstoneproject.core.data.remote.network.ApiConfig
//import com.dicoding.capstoneproject.core.domain.repository.IKamusRepository
//import com.dicoding.capstoneproject.core.domain.usecase.KamusInteractor
//import com.dicoding.capstoneproject.core.domain.usecase.KamusUseCase
//import com.dicoding.capstoneproject.core.utils.AppExecutors
//
//
//object Injection {
//    private fun provideRepository(context: Context): IKamusRepository {
//        val database = KamusDatabase.getInstance(context)
//
//        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
//        val localDataSource = LocalDataSource.getInstance(database.kamusDao())
//        val appExecutors = AppExecutors()
//
//        return KamusRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
//    }
//
//    fun provideKamusUseCase(context: Context): KamusUseCase {
//        val repository = provideRepository(context)
//        return KamusInteractor(repository)
//    }
//}
