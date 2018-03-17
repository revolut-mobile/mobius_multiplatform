package com.revolut.domain.models

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
data class Ticker(
        val bid: Double,
        val ask: Double,
        val last: Double
)