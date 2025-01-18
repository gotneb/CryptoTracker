package com.gotneb.cryptotracker.crypto.data.mappers

import com.gotneb.cryptotracker.crypto.data.networking.dto.CoinDto
import com.gotneb.cryptotracker.crypto.domain.Coin

/*
 * As said, it's overkill, but it's GREAT for big projects
 * In this case, there's really no need, but if we have a additional
 * logic, like converting one DATETIME from the api to a more convenient one
 * Then it would be made here.
 */

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}