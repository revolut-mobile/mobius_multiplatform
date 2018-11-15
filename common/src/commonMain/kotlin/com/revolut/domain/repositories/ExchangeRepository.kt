package com.revolut.domain.repositories

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.network.models.*
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JSON

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
class ExchangeRepository {

    suspend fun getAllMarkets(): List<Market> = Api.getMarkets().let(MarketResult::result).map(MarketResponse::toDomain)

    suspend fun getTicker(market: Market): Ticker = Api.getTicker(market.marketName).result.toDomain()

}

object Api {

    private val client = HttpClient {
        install(CallLoggingFeature)

        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                setMapper(MarketResult::class, MarketResult.serializer())
                setMapper(MarketResponse::class, MarketResponse.serializer())
                setMapper(TickerResponse::class, TickerResponse.serializer())
                setMapper(TickerResult::class, TickerResult.serializer())
            }
        }
    }

    suspend fun getMarkets(): MarketResult = client.get {
        url {
            protocol = URLProtocol.HTTPS
            host = "bittrex.com"
            encodedPath = "api/v1.1/public/getmarkets"
        }
    }

    suspend fun getTicker(market: String): TickerResult = client.get {
        url {
            protocol = URLProtocol.HTTPS
            host = "bittrex.com"
            encodedPath = "api/v1.1/public/getticker"
            parameter("market", market)
        }
    }


}