package com.revolut.presentation.cards

import com.revolut.domain.interactors.CardsInteractor
import com.revolut.presentation.base.BasePresenter
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.startCoroutine

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
        kotlinx.coroutines.experimental.launch {  }
    }

    open class EmptyContinuation(override val context: CoroutineContext) : Continuation<Any?> {
        companion object : EmptyContinuation(context)

        override fun resume(value: Any?) {

            println("EmptyContinuation.resume")
        }

        override fun resumeWithException(exception: Throwable) {
            println("EmptyContinuation.resumeWithException $exception")
            throw exception
        }
    }

    private fun launch(context: CoroutineContext, block: suspend () -> Unit) {
    }

}