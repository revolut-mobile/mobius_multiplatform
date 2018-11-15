package com.revolut.domain.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * {
"success": true,
"message": "",
"result": [...]
}
 */
@Serializable
data class MarketResult(@SerialName("result") val result: List<MarketResponse>,
                        @SerialName("success") val success: Boolean,
                        @SerialName("message") val message: String)