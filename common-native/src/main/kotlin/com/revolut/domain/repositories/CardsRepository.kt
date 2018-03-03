package com.revolut.domain.repositories

import com.revolut.domain.models.RevolutCard
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.experimental.*
import kotlin.coroutines.experimental.intrinsics.*
import com.revolut.domain.models.*

actual class CardsRepository {

    actual suspend fun getAllCards(): List<RevolutCard> {
        return suspendCoroutineOrReturn { continuation ->
            /** Here any iOS async loading libray can be used,
             *  after loading is done:
             *  continuation.resume(List<RevolutCard>) must be called.
             *  If any error: continuation.error(Throwable).
             * */

//            Networking.getAllCards(onSuccess -> {
//                continuation.resume(it)
//            }, onError-> {
//                continuation.resumeWithException(it)
//            })
            COROUTINE_SUSPENDED
        }
    }


}