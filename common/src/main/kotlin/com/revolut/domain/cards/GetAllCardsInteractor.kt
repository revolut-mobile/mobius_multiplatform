package com.revolut.domain.cards

/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
class GetAllCardsInteractor(val cardsRepository: CardsRepository) {

    suspend fun getAllCards() = cardsRepository.getAllCards()

}

