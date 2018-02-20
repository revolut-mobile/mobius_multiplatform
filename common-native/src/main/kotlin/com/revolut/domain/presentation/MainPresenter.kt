package com.revolut.presentation

import kotlin.coroutines.experimental.CoroutineContext
import com.revolut.domain.cards.GetAllCardsInteractor


/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
actual class MainPresenter(val context: CoroutineContext,
                           val interactor: GetAllCardsInteractor) {

    actual fun attach(view: MainView) {
//        launch(context) {
//            val cards = interactor.getAllCards()
//            view.setCards
//        }
    }

    actual fun detach() {

    }


    private fun launch(context: CoroutineContext, block: suspend () -> Unit) {
        TODO("This will be moved out to common module when bug with crashing compliler is fixed")
    }

}