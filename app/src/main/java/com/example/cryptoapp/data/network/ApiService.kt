package com.example.cryptoapp.data.network

import com.example.cryptoapp.data.network.model.CoinNamesListData
import com.example.cryptoapp.data.network.model.CoinInfoJsonContainerData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val CURRENCY = "USD"
        private const val APIKEY = "eb01ed33b14130d16619d2cad9a5813e5a7fad68ce4f6445932e7e97706ebe31"
        private const val LIMIT = 10
    }

    //    https://min-api.cryptocompare.com/data/top/totalvolfull?limit=10&tsym=USD
    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(QUERY_PARAM_LIMIT) limit: Int = LIMIT,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = APIKEY
    ): CoinNamesListData

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = APIKEY
    ): CoinInfoJsonContainerData

}