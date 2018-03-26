package com.revolut.domain.repositories

import com.revolut.data.BittrexApi
import com.revolut.data.toDomain
import com.revolut.data.toNetwork
import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
actual class ExchangeRepository(
        private val bittrexApi: BittrexApi
) {

    actual suspend fun getAllMarkets(): List<Market> {
        return bittrexApi.getMarkets().await().result.map { it.toDomain() }
    }

    actual suspend fun getTicker(market: Market): Ticker {
        return bittrexApi.getTicker(market.toNetwork().marketName).await().result.toDomain()
    }

}