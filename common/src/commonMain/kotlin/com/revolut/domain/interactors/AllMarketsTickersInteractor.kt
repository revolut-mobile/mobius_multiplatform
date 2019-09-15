package com.revolut.domain.interactors

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

/**
 * Created by yatsinar on 24/03/2018.
 * Revolut
 * All rights reserved
 */
interface AllMarketsTickersInteractor {

    suspend fun getTickersForAllMarkets(): List<Pair<Market, Ticker>>

    fun observeTickers(coroutineContext: CoroutineContext): ReceiveChannel<List<Pair<Market, Ticker>>>

}