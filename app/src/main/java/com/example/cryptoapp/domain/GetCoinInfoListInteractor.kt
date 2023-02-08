package com.example.cryptoapp.domain

class GetCoinInfoListInteractor(
    private val repository: CoinRepository
) {
    operator fun invoke() = repository.getCoinInfoList()
}