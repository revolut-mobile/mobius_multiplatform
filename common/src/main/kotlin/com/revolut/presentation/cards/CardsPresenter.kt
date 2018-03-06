package com.revolut.presentation.cards

import com.revolut.coroutines.launch
import com.revolut.domain.interactors.CardsInteractor
import com.revolut.presentation.base.BasePresenter
import kotlin.coroutines.experimental.CoroutineContext

class CardsPresenter constructor(
        private val workerContext: CoroutineContext,
        private val uiContext: CoroutineContext,
        private val interactor: CardsInteractor
) : BasePresenter<CardsView>() {

    override fun onViewAttached() {
        super.onViewAttached()
    }

    override fun onViewDetached() {
        super.onViewDetached()
    }

    fun start() {
        println("Before start")
        launch(uiContext) {
            val cards = interactor.getAllCards()
            view?.showCard(cards)
        }
        println("After start")
    }

}
