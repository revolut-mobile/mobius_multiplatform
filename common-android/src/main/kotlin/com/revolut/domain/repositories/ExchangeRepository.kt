package com.revolut.domain.repositories

import com.revolut.data.BittrexApi
import com.revolut.data.toDomain
import com.revolut.data.toNetwork
import com.revolut.domain.models.Market


actual class ExchangeRepository(
        private val api: BittrexApi) {

    actual suspend fun getAllMarkets() = api.getMarkets().await()
            .result.map { it.toDomain() }

    actual suspend fun getTicker(market: Market) = api.getTicker(market.toNetwork().marketName)
            .await().result.toDomain()

}

