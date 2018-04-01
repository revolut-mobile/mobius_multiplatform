package com.revolut.domain.interactors

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker

/**
 * Created by yatsinar on 24/03/2018.
 * Revolut
 * All rights reserved
 */
interface AllMarketsTickersInteractor {

    suspend fun getTickersForAllMarkets(): List<Pair<Market, Ticker>>

}