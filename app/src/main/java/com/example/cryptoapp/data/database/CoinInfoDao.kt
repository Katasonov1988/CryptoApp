package com.example.cryptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinInfoDao {

    @Query("SELECT * FROM full_price_list ORDER BY lastupdate")
    fun getPriceList(): LiveData<List<CoinInfoDBModel>>

    @Query("SELECT * FROM full_price_list WHERE fromSymbol == :fSym LIMIT 1")
    fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinInfoDBModel>

    @Query("SELECT * FROM full_price_list WHERE fromsymbol LIKE '%' || :searchQuery || '%'")
    fun getDateFromQuery(searchQuery: String): LiveData<List<CoinInfoDBModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(priceList: List<CoinInfoDBModel>)
}