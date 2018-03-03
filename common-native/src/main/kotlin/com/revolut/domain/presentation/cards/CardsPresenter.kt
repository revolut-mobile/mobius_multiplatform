package com.revolut.presentation.cards

import com.revolut.domain.interactors.CardsInteractor
import com.revolut.presentation.base.BasePresenter

import kotlin.coroutines.experimental.*
import kotlin.coroutines.experimental.intrinsics.*


/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
actual class CardsPresenter(
        private val dispatcher: ContinuationDispatcher
) : BasePresenter<CardsView>() {

    init {
        println("Init")
    }

    actual override fun onViewAttached() {
        super.onViewAttached()
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

    private fun launchCustom(context: CoroutineContext, block: suspend () -> Unit) {
        block.startCoroutine(EmptyContinuation(context))
    }

    fun start() {
        launchCustom(dispatcher, {
            println(" World!")
        })
        print("Hello,")
    }

}

//
//abstract class CoroutineDispatcher(key: CoroutineContext.Key<*>) : AbstractCoroutineContextElement(key), ContinuationInterceptor {
//    abstract fun dispatch(context: CoroutineContext, block: Runnable)
//}
//
//interface Runnable {
//    fun run()
//}


abstract class ContinuationDispatcher : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {
    abstract fun <T> dispatchResume(value: T, continuation: Continuation<T>): Boolean
    abstract fun dispatchResumeWithException(exception: Throwable, continuation: Continuation<*>): Boolean
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> = DispatchedContinuation(this, continuation)
}

class DispatchedContinuation<T>(
        val dispatcher: ContinuationDispatcher,
        val continuation: Continuation<T>
) : Continuation<T> {
    override val context: CoroutineContext = continuation.context

    override fun resume(value: T) {
        if (!dispatcher.dispatchResume(value, continuation))
            continuation.resume(value)
    }

    override fun resumeWithException(exception: Throwable) {
        if (!dispatcher.dispatchResumeWithException(exception, continuation))
            continuation.resumeWithException(exception)
    }
}



