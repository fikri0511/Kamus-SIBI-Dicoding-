package com.dicoding.capstoneproject.core.domain.usecase

import com.dicoding.capstoneproject.core.domain.model.Kamus
import com.dicoding.capstoneproject.core.domain.repository.IKamusRepository

class KamusInteractor(private val kamusRepository: IKamusRepository): KamusUseCase {

    override fun getAllKamus() = kamusRepository.getAllKamus()

    override fun getFavoriteKamus() = kamusRepository.getFavoriteKamus()

    override fun setFavoriteKamus(kamus: Kamus, state: Boolean) = kamusRepository.setFavoriteKamus(kamus, state)
}