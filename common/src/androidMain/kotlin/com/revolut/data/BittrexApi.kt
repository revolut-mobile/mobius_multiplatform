package com.revolut.data

import com.revolut.data.models.MarketResponse
import com.revolut.data.models.Result
import com.revolut.data.models.TickerResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
interface BittrexApi {

    @GET("getmarkets")
    fun getMarkets(): Deferred<Result<List<MarketResponse>>>

    @GET("getticker")
    fun getTicker(@Query("market") market: String): Deferred<Result<TickerResponse>>

}