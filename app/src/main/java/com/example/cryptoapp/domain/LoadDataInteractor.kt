package com.example.cryptoapp.domain

class LoadDataInteractor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke() = repository.loadData()
}