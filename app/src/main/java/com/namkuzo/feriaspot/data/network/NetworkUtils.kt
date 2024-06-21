package com.namkuzo.feriaspot.data.network

import kotlinx.coroutines.delay
import java.io.IOException

suspend fun <T> retryIO(
    times: Int = 3,
    initialDelayMillis: Long = 100,
    maxDelayMillis: Long = 1000,
    factor: Double = 2.0,
    block: suspend () -> T
): T {
    var currentDelay = initialDelayMillis
    repeat(times - 1) {
        try {
            return block()
        } catch (e: IOException) {
            // Network or IO error occurred, retry after delay
            delay(currentDelay)
            currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelayMillis)
        }
    }
    // Last attempt without catching exceptions
    return block()
}