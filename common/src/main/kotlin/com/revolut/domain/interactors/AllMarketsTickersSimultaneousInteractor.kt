package com.revolut.domain.interactors

import com.revolut.coroutines.Deferred
import com.revolut.coroutines.async
import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.repositories.ExchangeRepository
import kotlin.coroutines.experimental.CoroutineContext


class AllMarketsTickersSimultaneousInteractor(
        private val exchangeRepository: ExchangeRepository,
        private val workerContext: CoroutineContext
) : AllMarketsTickersInteractor {

    override suspend fun getTickersForAllMarkets(): Map<Market, Ticker> {
        val deferredTickersMap = mutableMapOf<Market, Deferred<Ticker>>()

        exchangeRepository.getAllMarkets()
                .subList(0, 50)
                .forEach { market ->
                    deferredTickersMap[market] = async(workerContext) {
                        exchangeRepository.getTicker(market)
                    }
                }

        return deferredTickersMap.mapValues { it.value.await() }
    }

}
