package com.revolut.domain.repositories

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import kotlin.coroutines.experimental.*
import kotlin.coroutines.experimental.intrinsics.*

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
actual open class ExchangeRepository {

    open fun getAllMarkets(callback: Continuation<List<Market>>) {
        throw NotImplementedError("Not implemented")
    }

    open fun getTicker(market: Market, callback: Continuation<Ticker>) {
        throw NotImplementedError("Not implemented")
    }

    actual suspend fun getAllMarkets(): List<Market> {
        return suspendCoroutineOrReturn { continuation ->
            getAllMarkets(continuation)
            COROUTINE_SUSPENDED
        }
    }

    actual suspend fun getTicker(market: Market): Ticker {
        return suspendCoroutineOrReturn { continuation ->
            getTicker(market, continuation)
            COROUTINE_SUSPENDED
        }
    }

}