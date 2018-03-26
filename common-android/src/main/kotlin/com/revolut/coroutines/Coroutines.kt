package com.revolut.coroutines

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.withContext
import kotlin.coroutines.experimental.CoroutineContext

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
