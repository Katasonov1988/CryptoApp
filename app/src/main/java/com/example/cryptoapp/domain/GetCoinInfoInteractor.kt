package com.example.cryptoapp.domain

class GetCoinInfoInteractor(
    private val repository: CoinRepository
) {
    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}