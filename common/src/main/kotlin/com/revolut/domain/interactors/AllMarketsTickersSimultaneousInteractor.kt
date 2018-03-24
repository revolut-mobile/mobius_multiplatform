package com.revolut.domain.interactors

import com.revolut.coroutines.Deferred
import com.revolut.coroutines.launch
import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.repositories.ExchangeRepository
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
class AllMarketsTickersSimultaneousInteractor(
        private val exchangeRepository: ExchangeRepository,
        private val workerContext: CoroutineContext
): AllMarketsTickersInteractor {

    override suspend fun getTickersForAllMarkets(): Map<Market, Ticker> {
        val mutableMap = mutableMapOf<Market, Deferred<Ticker>>()

        exchangeRepository.getAllMarkets().subList(0, 10).forEach { market ->
            val ticker = launch(workerContext) {
                val ticker = exchangeRepository.getTicker(market)
                ticker
            }
            mutableMap[market] = ticker
        }

        return mutableMap.mapValues { it.value.await() }
    }


}
