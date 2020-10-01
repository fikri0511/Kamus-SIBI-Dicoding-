package com.dicoding.capstoneproject.view.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.capstoneproject.core.domain.usecase.KamusUseCase

class FavoriteViewModel(kamusUseCase: KamusUseCase) : ViewModel() {
    val favoriteKamus = kamusUseCase.getFavoriteKamus().asLiveData()
}