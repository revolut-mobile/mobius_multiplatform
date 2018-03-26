package com.revolut.coroutines

import kotlin.coroutines.experimental.*

actual class Deferred<out T>(
        private val context: CoroutineContext,
        private val block: suspend () -> T
) {

    actual suspend fun await(): T {
        return Any() as T
    }

    fun start() {
        (context as? ContinuationDispatcher)?.canceled = false
        block.startCoroutine(EmptyContinuation(context))
    }

    actual fun cancel() {
        (context as? ContinuationDispatcher)?.canceled = true
    }

}