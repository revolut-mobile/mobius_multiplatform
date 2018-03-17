package com.revolut.coroutines

import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by yatsinar on 04/03/2018.
 * Revolut
 * All rights reserved
 */
expect fun <T> launch(context: CoroutineContext, block: suspend () -> T): Deferred<T>

expect class Deferred<T> {
    suspend fun await(): T
}

open class EmptyContinuation(override val context: CoroutineContext) : Continuation<Any?> {

    companion object : EmptyContinuation(context)

    override fun resume(value: Any?) {

    }

    override fun resumeWithException(exception: Throwable) {
        throw exception
    }
}