package com.revolut.coroutines

import kotlin.coroutines.experimental.*
import kotlin.coroutines.experimental.intrinsics.*

typealias NativeDeferred<T> = com.revolut.coroutines.Deferred<T>

actual fun <T> async(context: CoroutineContext, block: suspend () -> T): NativeDeferred<T> {
    return NativeDeferred(context, block)
}

actual fun <T> launch(context: CoroutineContext, block: suspend () -> T) {
    block.startCoroutine(EmptyContinuation(context))
}

actual suspend fun <T> withContext(context: CoroutineContext, block: suspend () -> T): T {
    return suspendCoroutineOrReturn { continuation ->
        block.startCoroutine(WrappedContinuation(context, continuation))
        COROUTINE_SUSPENDED
    }
}
