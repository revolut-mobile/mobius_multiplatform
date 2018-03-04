package com.revolut.presentation.cards

import com.revolut.coroutines.launch
import com.revolut.domain.interactors.CardsInteractor
import com.revolut.presentation.base.BasePresenter
import kotlin.coroutines.experimental.CoroutineContext

class CardsPresenter(
        private val context: CoroutineContext,
        private val interactor: CardsInteractor) : BasePresenter<CardsView>() {

    override fun onViewAttached() {
        super.onViewAttached()
    }

    override fun onViewDetached() {
        super.onViewDetached()
    }

    fun start() {
        println("Before start")
        launch(context) {
            val cards = interactor.getAllCards()
            view?.showCard(cards)
        }
        println("After start")
    }

}
