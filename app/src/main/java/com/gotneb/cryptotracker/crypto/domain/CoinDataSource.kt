package com.gotneb.cryptotracker.crypto.domain

import com.gotneb.cryptotracker.core.domain.util.NetworkError
import com.gotneb.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}