package com.revolut.presentation

import com.revolut.domain.cards.GetAllCardsInteractor
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
actual class MainPresenter(val interactor: GetAllCardsInteractor) {


    actual fun attach(view: MainView) {}
    actual fun detach() {}

    private fun launch(context: CoroutineContext, block: suspend () -> Unit) {
        TODO()
    }

}