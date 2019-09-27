package com.revolut.data.db

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver

actual fun createDb(dbArgs: DbArgs): MarketDataDb {
    val driver = AndroidSqliteDriver(MarketDataDb.Schema, dbArgs.context, "markets.db")
    return MarketDataDb(driver)
}

actual class DbArgs(
    val context: Context
)

