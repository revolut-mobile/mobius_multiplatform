package com.revolut.domain.network.models

import kotlinx.serialization.*

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 *
 * {
"success": true,
"message": "",
"result": {...}
}
 */
@Serializable
data class Result<T>(
        @SerialName("result") val result: T,
        @SerialName("success") val success: Boolean,
        @SerialName("message") val message: String)

