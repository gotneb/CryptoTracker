package com.gotneb.cryptotracker.crypto.data.mappers

import com.gotneb.cryptotracker.crypto.data.networking.dto.CoinDto
import com.gotneb.cryptotracker.crypto.data.networking.dto.CoinPriceDto
import com.gotneb.cryptotracker.crypto.domain.Coin
import com.gotneb.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

/*
 * As said, it's overkill, but it's GREAT for big projects
 * In this case, there's really no need, but if we have a additional
 * logic, like converting one DATETIME from the api to a more convenient one
 * Then it would be made here.
 */

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank.toInt(),
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd.toDouble(),
        priceUsd = priceUsd.toDouble(),
        changePercent24Hr = if (changePercent24Hr == null) 0.0 else changePercent24Hr.toDouble(),
    )
}

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        datetime = Instant
            .ofEpochMilli(time)
            .atZone(ZoneId.systemDefault())
    )
}