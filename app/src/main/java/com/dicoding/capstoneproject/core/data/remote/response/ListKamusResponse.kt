package com.dicoding.capstoneproject.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListKamusResponse (
    @field:SerializedName("Kata")
    val kata: List<KamusResponse>
)