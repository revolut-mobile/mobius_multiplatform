package com.revolut.domain.repositories

import com.revolut.data.db.DbArgs
import com.revolut.data.db.MarketsDao
import com.revolut.data.db.createDb
import com.revolut.data.network.models.*
import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
class ExchangeRepository(
    dbArgs: DbArgs
) {

    private val marketsDao: MarketsDao = MarketsDao(createDb(dbArgs))
    private val api = Api()

    suspend fun getAllMarkets(): List<Market> {
        val cachedMarkets = marketsDao.selectAll().map { it.toDomain() }

        if (!cachedMarkets.isNullOrEmpty()) return cachedMarkets

        val networkMarkets =
            api.getMarkets().let(MarketResult::result).subList(0, 20).map(MarketResponse::toDomain)

        networkMarkets.forEach { marketsDao.insert(it.toDb()) }

        return networkMarkets
    }

    suspend fun getTicker(market: Market): Ticker =
        api.getTicker(market.marketName).result.toDomain()

}

class Api {

    private val client = HttpClient {
        install(CallLoggingFeature)

        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
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