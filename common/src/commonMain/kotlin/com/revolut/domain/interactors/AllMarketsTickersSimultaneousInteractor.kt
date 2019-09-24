package com.revolut.domain.interactors

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.repositories.ExchangeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class AllMarketsTickersSimultaneousInteractor(
    private val repository: ExchangeRepository
) : AllMarketsTickersInteractor {

    override suspend fun getTickersForAllMarkets(): List<Pair<Market, Ticker>> {
        return repository.getAllMarkets()
            .subList(0, 10)
            .map { market ->
                market to GlobalScope.async(Dispatchers.Unconfined) {
                    repository.getTicker(market)
                }
            }
            .map { (market, job) -> market to job.await() }
    }

    override fun observeTickers(coroutineContext: CoroutineContext): ReceiveChannel<List<Pair<Market, Ticker>>> {
        return GlobalScope.produce(coroutineContext) {
            while (true) {
                send(getTickersForAllMarkets())
                delay(timeMillis = 1000)
            }
        }
    }

}
