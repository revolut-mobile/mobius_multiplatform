package com.revolut.presentation.cards

import kotlin.coroutines.experimental.CoroutineContext
import com.revolut.domain.interactors.CardsInteractor
import com.revolut.presentation.base.BasePresenter


/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
actual class CardsPresenter(
        val context: CoroutineContext,
        private val interactor: CardsInteractor
) : BasePresenter<CardsView>() {


    private fun launch(context: CoroutineContext, block: suspend () -> Unit) {
        TODO("This will be moved out to common module when bug with crashing compliler is fixed")
    }


}