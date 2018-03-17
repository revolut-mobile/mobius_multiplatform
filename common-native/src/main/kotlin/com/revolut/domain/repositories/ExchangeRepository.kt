package com.revolut.domain.repositories

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
actual class ExchangeRepository {

    actual suspend fun getAllMarkets(): List<Market> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    actual suspend fun getTicker(market: Market): Ticker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}