package com.revolut.data.db

import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual fun createDb(dbArgs: DbArgs): MarketDataDb {
    val driver = NativeSqliteDriver(MarketDataDb.Schema, "markets.db")
    return MarketDataDb(driver)
}

actual class DbArgs