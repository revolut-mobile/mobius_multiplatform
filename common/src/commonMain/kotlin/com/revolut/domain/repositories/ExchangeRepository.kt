package com.revolut.domain.repositories

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
expect class ExchangeRepository {

    suspend fun getAllMarkets(): List<Market>

    suspend fun getTicker(market: Market): Ticker

}