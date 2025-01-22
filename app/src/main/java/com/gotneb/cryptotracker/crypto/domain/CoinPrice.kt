package com.gotneb.cryptotracker.crypto.domain

import java.time.ZonedDateTime

data class CoinPrice(
    val priceUsd: Double,
    val datetime: ZonedDateTime,
)
