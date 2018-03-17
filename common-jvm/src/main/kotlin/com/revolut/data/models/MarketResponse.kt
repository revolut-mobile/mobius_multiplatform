package com.revolut.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 * {
"MarketCurrency": "LTC",
"BaseCurrency": "BTC",
"MarketCurrencyLong": "Litecoin",
"BaseCurrencyLong": "Bitcoin",
"MinTradeSize": 0.01231527,
"MarketName": "BTC-LTC",
"IsActive": true,
"Created": "2014-02-13T00:00:00",
"Notice": null,
"IsSponsored": null,
"LogoUrl": "https://bittrexblobstorage.blob.core.windows.net/public/6defbc41-582d-47a6-bb2e-d0fa88663524.png"
}
 */
data class MarketResponse(
        @SerializedName("MarketCurrency") val marketCurrency: String,
        @SerializedName("MarketCurrencyLong") val marketCurrencyLong: String,
        @SerializedName("BaseCurrency") val baseCurrency: String,
        @SerializedName("BaseCurrencyLong") val baseCurrencyLong: String,
        @SerializedName("MinTradeSize") val minTradeSize: Double,
        @SerializedName("MarketName") val marketName: String,
        @SerializedName("IsActive") val isActive: Boolean,
        @SerializedName("Created") val created: String,
        @SerializedName("LogoUrl") val logoUrl: String?
)