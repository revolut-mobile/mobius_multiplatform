package com.revolut.domain.interactors

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.repositories.ExchangeRepository

/**
 * Created by yatsinar on 24/03/2018.
 * Revolut
 * All rights reserved
 */
class AllMarketsTickersConsequentInteractor(
        private val exchangeRepository: ExchangeRepository
) : AllMarketsTickersInteractor {

    suspend override fun getTickersForAllMarkets(): Map<Market, Ticker> {
        val mutableMap = mutableMapOf<Market, Ticker>()

        exchangeRepository.getAllMarkets().subList(0, 10).forEach { market ->
            val ticker = exchangeRepository.getTicker(market)
            mutableMap[market] = ticker
        }

        return mutableMap
    }

}
