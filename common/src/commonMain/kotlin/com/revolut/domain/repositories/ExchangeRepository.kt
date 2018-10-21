package com.revolut.domain.repositories

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.network.models.MarketResponse
import com.revolut.domain.network.models.Result
import com.revolut.domain.network.models.TickerResponse
import com.revolut.domain.network.models.toDomain
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.KSerializer

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
class ExchangeRepository {

    suspend fun getAllMarkets(): List<Market> = Api.getMarkets().map { it.toDomain() }

    suspend fun getTicker(market: Market): Ticker = Api.getTicker(market.marketName).result.toDomain()

}

object Api {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                setMapper(MarketResponse::class, MarketResponse.serializer() as KSerializer<MarketResponse>)
                setMapper(TickerResponse::class, TickerResponse.serializer() as KSerializer<TickerResponse>)
                setMapper(Result::class, Result.serializer(TickerResponse.serializer()) as KSerializer<Result<*>>)
            }
        }
    }

    suspend fun getMarkets(): List<MarketResponse> = client.get {
        url {
            takeFrom("https://bittrex.com/api/v1.1/public/")
            encodedPath = "getmarkets"
        }
    }

    suspend fun getTicker( market: String): Result<TickerResponse>  = client.get {
        url {
            takeFrom("https://bittrex.com/api/v1.1/public/")
            encodedPath = "getticker"
            parameter("market", market)
        }
    }


}