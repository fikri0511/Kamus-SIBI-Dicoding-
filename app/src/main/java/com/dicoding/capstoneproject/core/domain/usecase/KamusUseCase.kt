package com.dicoding.capstoneproject.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dicoding.capstoneproject.core.data.Resource
import com.dicoding.capstoneproject.core.domain.model.Kamus
import kotlinx.coroutines.flow.Flow


interface KamusUseCase {
    fun getAllKamus(): Flow<Resource<List<Kamus>>>
    fun getFavoriteKamus(): Flow<List<Kamus>>
    fun setFavoriteKamus(kamus: Kamus, state: Boolean)
}