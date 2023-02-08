package com.example.cryptoapp.data.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoJsonContainerData(
    @SerializedName("raw")
    @Expose
    val json: JsonObject? = null
)

