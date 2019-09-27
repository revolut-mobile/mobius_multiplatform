package com.revolut.data.db.models

import kotlinx.serialization.Serializable

@Serializable
internal data class MarketDb(
    val marketName: String,
    val isActive: Long,
    val logoUrl: String?
)