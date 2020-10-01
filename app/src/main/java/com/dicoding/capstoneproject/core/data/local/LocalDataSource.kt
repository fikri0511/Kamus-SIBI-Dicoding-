package com.dicoding.capstoneproject.core.data.local

import androidx.lifecycle.LiveData
import com.dicoding.capstoneproject.core.data.local.entity.KamusEntity
import com.dicoding.capstoneproject.core.data.local.room.KamusDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource public constructor(private val kamusDao: KamusDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(kamusDao: KamusDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(kamusDao)
            }
    }

    fun getAllKamus(): Flow<List<KamusEntity>> = kamusDao.getAllKamus()

    fun getFavoriteKamus(): Flow<List<KamusEntity>> = kamusDao.getFavoriteKamus()

    suspend fun insertKamus(kamusList: List<KamusEntity>) = kamusDao.insertKamus(kamusList)

    fun setFavoriteKamus(kamus: KamusEntity, newState: Boolean) {
        kamus.isFavorite = newState
        kamusDao.updateFavoriteKamus(kamus)
    }
}