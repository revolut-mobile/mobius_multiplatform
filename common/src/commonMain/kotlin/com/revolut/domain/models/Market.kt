package com.revolut.domain.models


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


