package com.revolut.coroutines

import kotlin.coroutines.experimental.*

actual fun launch(context: CoroutineContext, block: suspend () -> Unit) {
    block.startCoroutine(EmptyContinuation(context))
}
