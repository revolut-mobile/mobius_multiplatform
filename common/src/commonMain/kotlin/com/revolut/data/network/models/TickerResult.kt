package com.revolut.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 *
 * {
"success": true,
"message": "",
"result": {...}
}
 */
@Serializable
data class TickerResult(
        @SerialName("result") val result: TickerResponse,
        @SerialName("success") val success: Boolean,
        @SerialName("message") val message: String)

