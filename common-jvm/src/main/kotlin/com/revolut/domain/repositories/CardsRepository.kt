package com.revolut.domain.repositories

import com.revolut.domain.models.RevolutCard
import com.revolut.domain.models.RevolutCardImpl
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.delay

/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
actual class CardsRepository {

    actual suspend fun getAllCards(): List<RevolutCard> {
        return (1..10).map {
            println("processing card $it on ${Thread.currentThread().name}")
            delay(1000)
            RevolutCardImpl("id $it", Schedulers.newThread())
        }
    }

}