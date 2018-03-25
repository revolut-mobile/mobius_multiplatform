package com.revolut.coroutines

import kotlin.coroutines.experimental.*

actual fun <T> launch(context: CoroutineContext, block: suspend () -> T): com.revolut.coroutines.Deferred<T> {
    val def = com.revolut.coroutines.Deferred(context, block)
    def.start()
    return def
}
