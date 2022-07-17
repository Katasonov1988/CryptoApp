package com.example.cryptoapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptoapp.api.ApiFactory.BASE_IMAGE_URL
import com.example.cryptoapp.utils.convertTimestampToTime
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal


@Entity(tableName = "full_price_list")
data class CoinPriceInfo(
    @SerializedName("TYPE")
    @Expose
    val type: String? = null,

    @SerializedName("MARKET")
    @Expose
    val market: String? = null,

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromsymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    val tosymbol: String? = null,

    @SerializedName("PRICE")
    @Expose
    val price: String? = null,

    @SerializedName("LASTUPDATE")
    @Expose
    val lastupdate: Long? = null,

    @SerializedName("HIGHDAY")
    @Expose
    val highday: Double? = null,

    @SerializedName("LOWDAY")
    @Expose
    val lowday: Double? = null,


    @SerializedName("LASTMARKET")
    @Expose
    val lastmarket: String? = null,


    @SerializedName("IMAGEURL")
    @Expose
    val imageurl: String? = null,

    @SerializedName("VOLUMEDAY")
    @Expose
    val volumeday: Double? = null,

    @SerializedName("VOLUMEDAYTO")
    @Expose
    val volumedayto: Double? = null,

    @SerializedName("VOLUME24HOUR")
    @Expose
    val volume24hour: Double? = null,

    @SerializedName("VOLUME24HOURTO")
    @Expose
    val volume24hourto: Double? = null,

    @SerializedName("OPENDAY")
    @Expose
    val openday: Double? = null,

    @SerializedName("OPEN24HOUR")
    @Expose
    val open24hour: Double? = null,

    @SerializedName("HIGH24HOUR")
    @Expose
    val high24hour: Double? = null,

    @SerializedName("LOW24HOUR")
    @Expose
    val low24hour: Double? = null

) {
    fun getFormattedTime(): String {
        return convertTimestampToTime(lastupdate)
    }

    fun getFullImageUrl(): String {
        return BASE_IMAGE_URL + imageurl
    }

    fun getDayDifferenceCoin(): String {
        if (highday != null && lowday != null) {
            return (BigDecimal.valueOf(highday).subtract(BigDecimal.valueOf(lowday))).toString()
        } else {
            return "-"
        }
    }

    fun getDayDifferenceInPer(): String {
        val priceDouble = price?.toDouble()
        val hundredPer = 100.0

        if (priceDouble != null && openday != null) {

            return ((BigDecimal.valueOf(priceDouble)
                .subtract(BigDecimal.valueOf(openday)) * BigDecimal.valueOf(hundredPer)) / BigDecimal.valueOf(
                openday
            )).toString()
        } else {
            return "-"
        }
    }


}
