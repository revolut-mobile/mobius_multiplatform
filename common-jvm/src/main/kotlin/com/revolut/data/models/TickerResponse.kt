package com.revolut.data.models

import com.google.gson.annotations.SerializedName

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
data class TickerResponse(
        @SerializedName("Bid") val bid: Double,
        @SerializedName("Ask") val ask: Double,
        @SerializedName("Last") val last: Double
)