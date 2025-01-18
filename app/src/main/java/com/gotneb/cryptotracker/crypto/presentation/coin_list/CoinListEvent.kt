package com.gotneb.cryptotracker.crypto.presentation.coin_list

import com.gotneb.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}