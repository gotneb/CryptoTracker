package com.gotneb.cryptotracker.crypto.data.networking

import com.gotneb.cryptotracker.core.domain.data.networking.constructUrl
import com.gotneb.cryptotracker.core.domain.data.networking.safeCall
import com.gotneb.cryptotracker.core.domain.util.NetworkError
import com.gotneb.cryptotracker.core.domain.util.Result
import com.gotneb.cryptotracker.core.domain.util.map
import com.gotneb.cryptotracker.crypto.data.mappers.toCoin
import com.gotneb.cryptotracker.crypto.data.networking.dto.CoinResponseDto
import com.gotneb.cryptotracker.crypto.domain.Coin
import com.gotneb.cryptotracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}