package com.revolut.data.db

import com.revolut.data.db.models.MarketDb

class MarketsDao(private val database: MarketDataDb) {

    internal fun insert(item: MarketDb) {
        database.marketsQueries.insertItem(
            marketName = item.marketName,
            isActive = item.isActive,
            logoUrl = item.logoUrl
        )
    }

    internal fun selectAll():List<MarketDb> = database.marketsQueries.selectAll().executeAsList().map {
        MarketDb(
            marketName = it.marketName,
            isActive = it.isActive,
            logoUrl = it.logoUrl
        )
    }
}