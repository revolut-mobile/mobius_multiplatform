package com.revolut.coroutines

import kotlin.coroutines.experimental.*

actual fun <T> launch(context: CoroutineContext, block: suspend () -> T): com.revolut.coroutines.Deferred<T> {
    block.startCoroutine(EmptyContinuation(context))
    return com.revolut.coroutines.Deferred()
}
