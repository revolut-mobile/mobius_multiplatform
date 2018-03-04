package com.revolut.coroutines

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.withContext
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by yatsinar on 04/03/2018.
 * Revolut
 * All rights reserved
 */
actual fun launch(context: CoroutineContext, block: suspend () -> Unit) {
    async { withContext(context, block = block) }
}

actual fun withContext(context: CoroutineContext, block: suspend () -> Unit) {
    kotlinx.coroutines.experimental.launch(context) {
        block.invoke()
    }
}