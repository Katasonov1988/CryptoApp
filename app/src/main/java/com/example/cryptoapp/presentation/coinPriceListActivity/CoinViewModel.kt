package com.example.cryptoapp.presentation.coinPriceListActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoapp.domain.GetCoinInfoListInteractor
import com.example.cryptoapp.domain.GetCoinInfoInteractor
import com.example.cryptoapp.domain.LoadDataInteractor
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListInteractor = GetCoinInfoListInteractor(repository)
    private val getCoinInfoInteractor = GetCoinInfoInteractor(repository)
    private val loadDataInteractor = LoadDataInteractor(repository)

    val coinInfoList = getCoinInfoListInteractor()

    fun getDetailInfo(fSym: String) = getCoinInfoInteractor(fSym)

    init {
        viewModelScope.launch {
            loadDataInteractor()
        }

    }
}