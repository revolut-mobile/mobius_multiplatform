package com.revolut.coroutines

import kotlin.coroutines.experimental.*

actual fun launch(context: CoroutineContext, block: suspend () -> Unit) {
    block.startCoroutine(EmptyContinuation(context))
}

actual fun withContext(context: CoroutineContext, block: suspend () -> Unit) {
    val main = MainQueueDispatcher()
    val continuation = EmptyContinuation(main)
    block.startCoroutine(continuation)

}