package com.example.cryptoapp.domain

class GetCoinUseCase (
    private val repository: CoinRepository
    ) {
operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}