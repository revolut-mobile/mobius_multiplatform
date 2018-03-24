package com.revolut.domain.interactors

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.repositories.ExchangeRepository

class AllMarketsTickersConsequentInteractor(
        private val exchangeRepository: ExchangeRepository
) : AllMarketsTickersInteractor {

    override suspend fun getTickersForAllMarkets(): Map<Market, Ticker> {
        val tickersMap = mutableMapOf<Market, Ticker>()

        exchangeRepository.getAllMarkets()
                .subList(0, 10)
                .forEach { market ->
                    tickersMap[market] = exchangeRepository.getTicker(market)
                }

        return tickersMap
    }

}
