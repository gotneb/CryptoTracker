package com.gotneb.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

/*
 * Although this seems overkill, for big projects it's really useful to have a DTO.
 * For this project it isn't necessary to do this, we could just use the `Coin` model class
 * and mark it as serializable.
 */

@Serializable
data class CoinDto(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: Double,
    val priceUsd: Double,
    val changePercent24Hr: Double
)
