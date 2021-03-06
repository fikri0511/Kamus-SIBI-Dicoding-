package com.dicoding.capstoneproject.core.utils

import com.dicoding.capstoneproject.core.data.local.entity.KamusEntity
import com.dicoding.capstoneproject.core.data.remote.response.KamusResponse
import com.dicoding.capstoneproject.core.domain.model.Kamus


object DataMapper {
    fun mapResponsesToEntities(input: List<KamusResponse>): List<KamusEntity> {
        val kamusList = ArrayList<KamusEntity>()
        input.map {
            val kamus = KamusEntity(
                idkata = it.idkata,
                image = it.image,
                isFavorite = false,
                namakata = it.namakata,
                deskripsi = it.deskripsi,
                kategori = it.kategori
            )
            kamusList.add(kamus)
        }
        return kamusList
    }

    fun mapEntitiesToDomain(input: List<KamusEntity>): List<Kamus> =
        input.map {
            Kamus(
                idkata = it.idkata,
                image = it.image,
                isFavorite = false,
                namakata = it.namakata,
                deskripsi = it.deskripsi,
                kategori = it.kategori
            )
        }

    fun mapDomainToEntity(input: Kamus) = KamusEntity(
        idkata = input.idkata,
        image = input.image,
        isFavorite = false,
        namakata = input.namakata,
        deskripsi = input.deskripsi,
        kategori = input.kategori
    )
}