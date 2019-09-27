package com.revolut.data.network.models

import com.revolut.data.db.models.MarketDb
import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
fun MarketResponse.toDomain(): Market = Market(
        marketName = marketName,
        isActive = isActive,
        logoUrl = logoUrl
)

internal fun Market.toDb(): MarketDb = MarketDb(
        marketName = marketName,
        isActive = if (isActive) 1L else 0L,
        logoUrl = logoUrl
)

internal fun MarketDb.toDomain() = Market(
        marketName = marketName,
        isActive = isActive == 1L,
        logoUrl = logoUrl
)


fun TickerResponse.toDomain(): Ticker = Ticker(
        bid, ask, last
)