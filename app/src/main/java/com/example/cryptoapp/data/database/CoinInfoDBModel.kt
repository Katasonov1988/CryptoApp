package com.example.cryptoapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_price_list")
data class CoinInfoDBModel(
    @PrimaryKey
    val fromsymbol: String,
    val tosymbol: String?,
    val price: String?,
    val lastupdate: Long?,
    val highday: Double?,
    val lowday: Double?,
    val lastmarket: String?,
    val imageurl: String,
    val openday: Double?
)