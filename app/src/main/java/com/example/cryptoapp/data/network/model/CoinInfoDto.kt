package com.example.cryptoapp.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "full_price_list")
data class CoinInfoDto(
    @SerializedName("TYPE")
    @Expose
    val type: String?,

    @SerializedName("MARKET")
    @Expose
    val market: String?,

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String?,

    @SerializedName("PRICE")
    @Expose
    val price: String?,

    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Long?,

    @SerializedName("HIGHDAY")
    @Expose
    val highDay: Double?,

    @SerializedName("LOWDAY")
    @Expose
    val lowDay: Double?,


    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String?,


    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String?,

    @SerializedName("VOLUMEDAY")
    @Expose
    val volumeDay: Double?,

    @SerializedName("VOLUMEDAYTO")
    @Expose
    val volumeDayTo: Double?,

    @SerializedName("VOLUME24HOUR")
    @Expose
    val volume24Hour: Double?,

    @SerializedName("VOLUME24HOURTO")
    @Expose
    val volume24HourTo: Double?,

    @SerializedName("OPENDAY")
    @Expose
    val openDay: Double?,

    @SerializedName("OPEN24HOUR")
    @Expose
    val open24Hour: Double?,

    @SerializedName("HIGH24HOUR")
    @Expose
    val high24Hour: Double?,

    @SerializedName("LOW24HOUR")
    @Expose
    val low24Hour: Double?
)

