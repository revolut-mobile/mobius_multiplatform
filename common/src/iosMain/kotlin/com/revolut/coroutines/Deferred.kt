package com.revolut.coroutines

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine


actual class Deferred<out T>(
        private val context: CoroutineContext,
        private val block: suspend () -> T
) {

    actual suspend fun await(): T {
        return suspendCoroutine { continuation ->
            block.startCoroutine(WrappedContinuation(context, continuation))
        }
    }

}