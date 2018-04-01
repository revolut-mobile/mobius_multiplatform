package com.revolut.coroutines

import kotlin.coroutines.experimental.*

actual class Deferred<out T>(
        private val context: CoroutineContext,
        private val block: suspend () -> T
) {

    private val dispatcher: ContinuationDispatcher? = context as? ContinuationDispatcher

    actual suspend fun await(): T {
        throw kotlin.IllegalStateException("Not implemented in common-native")
    }

    fun start() {
        dispatcher?.canceled = false
        block.startCoroutine(EmptyContinuation(context))
    }

    actual fun cancel() {
        dispatcher?.canceled = true
    }

}