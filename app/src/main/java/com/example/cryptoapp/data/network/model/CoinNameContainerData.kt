package com.example.cryptoapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CoinNameContainerData(
    @SerializedName("coinInfo") val coinName: CoinNameData? = null
)
