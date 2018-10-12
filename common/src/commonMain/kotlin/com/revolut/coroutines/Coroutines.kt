package com.revolut.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

expect fun <T> async(context: CoroutineContext, block: suspend () -> T): Deferred<T>

expect fun <T> launch(context: CoroutineContext, block: suspend () -> T)

expect suspend fun <T> withContext(context: CoroutineContext, block: suspend () -> T): T

expect class Deferred<out T> {
    suspend fun await(): T
}

open class EmptyContinuation(override val context: CoroutineContext) : Continuation<Any?> {

    override fun resumeWith(result: Result<Any?>) = Unit

    companion object : EmptyContinuation(context)

}

open class WrappedContinuation<in T>(
        override val context: CoroutineContext,
        val continuation: Continuation<T>
) : Continuation<T> {

    companion object : WrappedContinuation<Any>(context, continuation)

    override fun resumeWith(result: Result<T>) = continuation.resumeWith(result)

}