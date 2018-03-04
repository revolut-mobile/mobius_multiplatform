package com.revolut.presentation.cards

import com.revolut.domain.interactors.CardsInteractor
import com.revolut.presentation.base.BasePresenter
import com.revolut.utils.*


/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
actual class CardsPresenter(private val interactor: CardsInteractor) : BasePresenter<CardsView>() {

    private val dispatcher = AsyncDispatcher()

    init {
        println("Init")
    }

    actual override fun onViewAttached() {
        super.onViewAttached()
    }

    fun start() {
        println("Before start")
        launch(dispatcher) {
            val cards = interactor.getAllCards()
            view?.showCard(cards)
        }
        println("After start")
    }

}



