package com.revolut.presentation.cards

import com.revolut.domain.interactors.CardsInteractor
import com.revolut.presentation.base.BasePresenter
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
actual class CardsPresenter(
        private val interactor: CardsInteractor
) : BasePresenter<CardsView>() {

    actual override fun onViewAttached() {
        super.onViewAttached()

    }

    actual override fun onViewDetached() {
        super.onViewDetached()
    }

    private fun launch(context: CoroutineContext, block: suspend () -> Unit) {
        TODO()
    }

}