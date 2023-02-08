package com.example.cryptoapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNamesListData(
    @SerializedName("data") val names: List<CoinNameContainerData>? = null
)