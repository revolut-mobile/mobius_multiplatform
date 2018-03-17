package com.revolut.domain.models

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
data class Market(
        val marketCurrency: String,
        val marketCurrencyLong: String,
        val baseCurrency: String,
        val baseCurrencyLong: String,
        val minTradeSize: Double,
        val marketName: String,
        val isActive: Boolean,
        val created: String,
        val logoUrl: String?
)