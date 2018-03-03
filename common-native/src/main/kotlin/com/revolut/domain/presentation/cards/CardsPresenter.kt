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
        private val dispatcher: ContinuationDispatcher,
        private val getter: Getter
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

    private suspend fun runSuspended() {
        suspendCoroutineOrReturn<String> { continuation ->
            /** Here any iOS async loading libray can be used,
             *  after loading is done:
             *  continuation.resume(List<RevolutCard>) must be called.
             *  If any error: continuation.error(Throwable).
             * */

            val value = getter.getString()
            println(value)
            continuation.resume(value)

//            Networking.getAllCards(onSuccess -> {
//                continuation.resume(it)
//            }, onError-> {
//                continuation.resumeWithException(it)
//            })
            COROUTINE_SUSPENDED
        }
    }


    fun start() {
        launchCustom(dispatcher, {
            runSuspended()
            runSuspended()
        })
        print("Hello,")
    }

}

interface Getter {

    fun getString(): String

}

abstract class ContinuationDispatcher : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {
    abstract fun <T> dispatchResume(value: T, continuation: Continuation<T>): Boolean
    abstract fun dispatchResumeWithException(exception: Throwable, continuation: Continuation<*>): Boolean
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> = DispatchedContinuation(this, continuation)
}

class DispatchedContinuation<T>(
        private val dispatcher: ContinuationDispatcher,
        private val continuation: Continuation<T>
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



