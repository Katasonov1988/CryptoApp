package com.example.cryptoapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CoinNameData(
    @SerializedName("name") val name: String? = null,
)