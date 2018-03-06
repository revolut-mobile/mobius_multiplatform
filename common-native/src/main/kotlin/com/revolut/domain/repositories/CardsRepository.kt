package com.revolut.domain.repositories

import com.revolut.domain.models.RevolutCard
import com.revolut.domain.models.*
import platform.darwin.*
import kotlin.coroutines.experimental.*
import kotlin.coroutines.experimental.intrinsics.*

actual open class CardsRepository {

    open fun getAllCardsSync(callback: (List<RevolutCard>) -> Unit) {
        return callback(emptyList<RevolutCard>())
    }

    actual suspend fun getAllCards(): List<RevolutCard> {
        return suspendCoroutineOrReturn { continuation ->
            getAllCardsSync {
                continuation.resume(it)
            }
            COROUTINE_SUSPENDED
        }
    }


}