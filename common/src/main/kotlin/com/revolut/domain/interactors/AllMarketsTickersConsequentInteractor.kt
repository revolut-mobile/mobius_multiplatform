package com.revolut.domain.interactors

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.repositories.ExchangeRepository

@Suppress("unused")
class AllMarketsTickersConsequentInteractor(
        private val exchangeRepository: ExchangeRepository
) : AllMarketsTickersInteractor {

    private val lastTickers = mutableMapOf<Market, Ticker>()

    override suspend fun getTickersForAllMarkets() = exchangeRepository.getAllMarkets()
            .subList(0, 60)
            .filterNot { lastTickers.containsKey(it) }
            .take(10)
            .map { market -> market to exchangeRepository.getTicker(market) }
            .sortedBy { (market, _) -> market.marketName }
            .also {
                lastTickers.clear()
                lastTickers.putAll(it)
            }

}

