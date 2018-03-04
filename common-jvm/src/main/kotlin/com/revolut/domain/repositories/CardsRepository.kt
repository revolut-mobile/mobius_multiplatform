package com.revolut.domain.repositories

import com.revolut.domain.models.RevolutCard

/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
actual class CardsRepository {

    actual suspend fun getAllCards(): List<RevolutCard> {
        TODO()
    }

}