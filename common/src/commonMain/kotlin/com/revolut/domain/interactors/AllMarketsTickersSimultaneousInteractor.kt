package com.revolut.domain.interactors

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.repositories.ExchangeRepository


class AllMarketsTickersSimultaneousInteractor(
        private val repository: ExchangeRepository
) : AllMarketsTickersInteractor {

    override suspend fun getTickersForAllMarkets(): List<Pair<Market, Ticker>> {
        return  repository.getAllMarkets()
                .subList(0, 20)
                .map { market ->
                    market to repository.getTicker(market) }
    }

}
