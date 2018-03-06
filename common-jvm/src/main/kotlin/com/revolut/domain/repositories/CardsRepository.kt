package com.revolut.domain.repositories

import com.revolut.domain.models.RevolutCard
import com.revolut.domain.models.RevolutCardImpl
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
actual class CardsRepository {

    actual suspend fun getAllCards(): List<RevolutCard> {
        return (1..5).map {
            async(CommonPool) {
                println("processing card $it on ${Thread.currentThread().name}")
                delay(1000)
                RevolutCardImpl("id $it")
            }
        }.map { it.await() }
    }

}