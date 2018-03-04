package com.revolut.coroutines

import kotlin.coroutines.experimental.*
/**
 * Created by yatsinar on 04/03/2018.
 * Revolut
 * All rights reserved
 */
expect fun launch(context: CoroutineContext, block: suspend () -> Unit)

open class EmptyContinuation(override val context: CoroutineContext) : Continuation<Any?> {
    companion object : EmptyContinuation(context)

    override fun resume(value: Any?) {
        //println("EmptyContinuation.resume")
    }

    override fun resumeWithException(exception: Throwable) {
        //println("EmptyContinuation.resumeWithException $exception")
        throw exception
    }
}