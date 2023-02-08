package com.example.cryptoapp.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "full_price_list")
data class CoinInfoData(
    @PrimaryKey
    @SerializedName("fromSymbol") val fromSymbol: String,
    @SerializedName("toSymbol") val toSymbol: String?,
    @SerializedName("price") val price: String?,
    @SerializedName("lastUpdate") val lastUpdate: Long?,
    @SerializedName("highDay") val highDay: Double?,
    @SerializedName("lowday") val lowDay: Double?,
    @SerializedName("lastMarket") val lastMarket: String?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("openDay") val openDay: Double?
)

