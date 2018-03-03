package com.revolut.domain.interactors

import com.revolut.domain.repositories.CardsRepository

/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
class CardsInteractor(
        private val cardsRepository: CardsRepository
) {

    suspend fun getAllCards() = cardsRepository.getAllCards()

}

