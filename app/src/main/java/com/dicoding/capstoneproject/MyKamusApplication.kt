package com.dicoding.capstoneproject

import android.app.Application
import com.dicoding.capstoneproject.core.di.databaseModule
import com.dicoding.capstoneproject.core.di.networkModule
import com.dicoding.capstoneproject.core.di.repositoryModule
import com.dicoding.capstoneproject.di.useCaseModule
import com.dicoding.capstoneproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyKamusApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyKamusApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}