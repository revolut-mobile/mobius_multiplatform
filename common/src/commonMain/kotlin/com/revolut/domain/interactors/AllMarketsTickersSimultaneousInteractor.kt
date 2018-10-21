package com.revolut.domain.interactors

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.repositories.ExchangeRepository
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext


class AllMarketsTickersSimultaneousInteractor(
        private val repository: ExchangeRepository,
        private val context: CoroutineContext
) : AllMarketsTickersInteractor {

    override suspend fun getTickersForAllMarkets(): List<Pair<Market, Ticker>> {
        return async(context) { repository.getAllMarkets() }
                .await()
                .subList(0, 20)
                .map { market ->
                    market to async(context) {
                        repository.getTicker(market)
                    }
                }
                .map { (market, job) ->
                    market to job.await()
                }
    }

}
