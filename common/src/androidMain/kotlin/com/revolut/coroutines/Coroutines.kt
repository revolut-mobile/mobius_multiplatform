package com.revolut.coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by yatsinar on 04/03/2018.
 * Revolut
 * All rights reserved
 */
actual fun <T> async(context: CoroutineContext, block: suspend () -> T): com.revolut.coroutines.Deferred<T> {
    return Deferred(async {
        withContext(context, block = block)
    })
}

actual fun <T> launch(context: CoroutineContext, block: suspend () -> T) {
    GlobalScope.launch(context, CoroutineStart.DEFAULT) {
        block()
    }
}

actual suspend fun <T> withContext(context: CoroutineContext, block: suspend () -> T): T {
    return kotlinx.coroutines.withContext(context = context, block = { block() })
}

