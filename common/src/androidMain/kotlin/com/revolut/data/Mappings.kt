package com.revolut.data

import com.revolut.data.models.MarketResponse
import com.revolut.data.models.TickerResponse
import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
fun MarketResponse.toDomain(): Market = Market(
        marketCurrency,
        marketCurrencyLong,
        baseCurrency,
        baseCurrencyLong,
        minTradeSize,
        marketName,
        isActive,
        created,
        logoUrl
)

fun Market.toNetwork(): MarketResponse = MarketResponse(
        marketCurrency, marketCurrencyLong, baseCurrency, baseCurrencyLong, minTradeSize, marketName, isActive, created, logoUrl
)

fun TickerResponse.toDomain(): Ticker = Ticker(
        bid, ask, last
)