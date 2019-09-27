package com.revolut.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 *
 * {
"Bid": 0.01960605,
"Ask": 0.01970999,
"Last": 0.01970999
}
 */
@Serializable
data class TickerResponse(
        @SerialName("Bid") val bid: Double,
        @SerialName("Ask") val ask: Double,
        @SerialName("Last") val last: Double
)