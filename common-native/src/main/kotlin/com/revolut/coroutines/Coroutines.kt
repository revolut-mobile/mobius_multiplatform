package com.revolut.coroutines

import kotlin.coroutines.experimental.*

typealias NativeDeferred<T> = com.revolut.coroutines.Deferred<T>

actual fun <T> async(context: CoroutineContext, block: suspend () -> T): NativeDeferred<T> {
    return NativeDeferred(context, block).apply {
        start()
    }
}
