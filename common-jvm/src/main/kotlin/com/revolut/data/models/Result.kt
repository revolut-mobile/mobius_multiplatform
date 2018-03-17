package com.revolut.data.models

import com.google.gson.annotations.SerializedName

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
data class Result<T>(
        @SerializedName("result") val result: T,
        @SerializedName("success") val success: Boolean,
        @SerializedName("message") val message: String)

