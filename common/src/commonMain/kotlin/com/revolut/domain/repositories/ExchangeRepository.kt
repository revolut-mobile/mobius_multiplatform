package com.revolut.domain.repositories

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.network.models.MarketResponse
import com.revolut.domain.network.models.MarketResult
import com.revolut.domain.network.models.TickerResponse
import com.revolut.domain.network.models.TickerResult
import com.revolut.domain.network.models.toDomain
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
class ExchangeRepository {

    private val api = Api()

    suspend fun getAllMarkets(): List<Market> = api.getMarkets().let(MarketResult::result).map(MarketResponse::toDomain)

    suspend fun getTicker(market: Market): Ticker = api.getTicker(market.marketName).result.toDomain()

}

class Api {

    private val client = HttpClient {
        install(CallLoggingFeature)

        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                register<MarketResult>()
                register<MarketResponse>()
                register<TickerResponse>()
                register<TickerResult>()
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