package com.revolut.coroutines

import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by yatsinar on 04/03/2018.
 * Revolut
 * All rights reserved
 */
expect fun <T> async(context: CoroutineContext, block: suspend () -> T): Deferred<T>

expect class Deferred<out T> {
    suspend fun await(): T
    fun cancel()
}

open class EmptyContinuation(override val context: CoroutineContext) : Continuation<Any?> {

    companion object : EmptyContinuation(context)

    override fun resume(value: Any?) {
        print("Empty Continuation")
    }

    override fun resumeWithException(exception: Throwable) {
        throw exception
    }
}