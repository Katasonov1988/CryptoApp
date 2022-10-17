package com.example.cryptoapp.data.mapper

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import com.example.cryptoapp.data.database.CoinInfoDBModel
import com.example.cryptoapp.data.network.model.CoinInfoDto
import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerDto
import com.example.cryptoapp.data.network.model.CoinNamesListDto
import com.example.cryptoapp.domain.CoinInfo
import com.google.gson.Gson
import java.util.*

class CoinMapper {
    fun mapDtoCoinToDbModel(dto: CoinInfoDto) = CoinInfoDBModel(
        fromsymbol = dto.fromsymbol,
        tosymbol = dto.tosymbol,
        price = dto.price,
        lastupdate = dto.lastupdate,
        highday = dto.highday,
        lowday = dto.lowday,
        lastmarket = dto.lastmarket,
        imageurl = BASE_IMAGE_URL + dto.imageurl,
        openday = dto.openday
    )

fun mapJsonContainerToListCoinInfo(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
    val result = mutableListOf<CoinInfoDto>()
    val jsonObject = jsonContainer.json ?: return result
    val coinKeySet = jsonObject.keySet()
    for (coinKey in coinKeySet) {
        val currencyJson = jsonObject.getAsJsonObject(coinKey)
        val currencyKeySet = currencyJson.keySet()
        for (currencyKey in currencyKeySet) {
            val priceInfo = Gson().fromJson(
                currencyJson.getAsJsonObject(currencyKey),
                CoinInfoDto::class.java
            )
            result.add(priceInfo)
        }
    }
    return result
}
    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
       return namesListDto.data?.map { it.coinName?.name }?.joinToString(",") ?: ""
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDBModel) = CoinInfo(
            fromsymbol = dbModel.fromsymbol,
            tosymbol = dbModel.tosymbol,
            price = dbModel.price,
            lastupdate = convertTimestampToTime(dbModel.lastupdate),
            highday = dbModel.highday,
            lowday = dbModel.lowday,
            lastmarket = dbModel.lastmarket,
            imageurl = dbModel.imageurl,
            openday = dbModel.openday
        )
    // преобразование секунд с 1970 года в нужный формат
      private fun convertTimestampToTime(timestamp: Long?): String {
          if (timestamp == null) return ""
          val stamp = java.sql.Timestamp(timestamp * 1000)
          val date = Date(stamp.time)
          val pattern = "HH:mm:ss"
          val sdf = SimpleDateFormat(pattern, Locale.getDefault())
          sdf.timeZone = TimeZone.getDefault()
          return sdf.format(date)
      }

    companion object{
         const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }
}