package com.gotneb.cryptotracker.core.domain.data.networking

import android.util.Log
import com.gotneb.cryptotracker.core.domain.util.NetworkError
import com.gotneb.cryptotracker.core.domain.util.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

suspend inline fun <reified  T> responseToResult(
    response: HttpResponse
): Result<T, NetworkError> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Log.e("responseToResult", "NoTransformationFoundException: ${e.toString()}")
                try {
                    // This is needed because the api weirdly returns a `text/html` response header
                    val responseBody = response.bodyAsText()
                    val parsedResponse = Json {
                        ignoreUnknownKeys = true
                    }.decodeFromString<T>(responseBody)
                    Result.Success(parsedResponse)
                } catch (serializationException: Exception) {
                    Log.e("responseToResult", "Serialization Error: ${serializationException.toString()}")
                    Result.Error(NetworkError.SERIALIZATION)
                }
            }
        }
        408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
        else -> Result.Error(NetworkError.UNKNOWN)
    }
}