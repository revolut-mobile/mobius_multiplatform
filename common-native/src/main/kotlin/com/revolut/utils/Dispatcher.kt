package com.revolut.utils

import kotlin.coroutines.experimental.*
import kotlin.coroutines.experimental.intrinsics.*

import platform.darwin.*

fun launch(context: CoroutineContext, block: suspend () -> Unit) {
    block.startCoroutine(EmptyContinuation(context))
}

class AsyncDispatcher : ContinuationDispatcher() {
    private val queue = dispatch_queue_create("com.revolut.queue", null)

    override fun <T> dispatchResume(value: T, continuation: Continuation<T>): Boolean {
        dispatch_async(queue) {
            continuation.resume(value)
        }
        return true
    }

    override fun dispatchResumeWithException(exception: Throwable, continuation: Continuation<*>): Boolean {
        dispatch_async(dispatch_get_main_queue()) {
            continuation.resumeWithException(exception)
        }
        return true
    }
}

abstract class ContinuationDispatcher : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {
    abstract fun <T> dispatchResume(value: T, continuation: Continuation<T>): Boolean
    abstract fun dispatchResumeWithException(exception: Throwable, continuation: Continuation<*>): Boolean
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> = DispatchedContinuation(this, continuation)
}

class DispatchedContinuation<T>(
        private val dispatcher: ContinuationDispatcher,
        private val continuation: Continuation<T>
) : Continuation<T> {
    override val context: CoroutineContext = continuation.context

    override fun resume(value: T) {
        if (!dispatcher.dispatchResume(value, continuation))
            continuation.resume(value)
    }

    override fun resumeWithException(exception: Throwable) {
        if (!dispatcher.dispatchResumeWithException(exception, continuation))
            continuation.resumeWithException(exception)
    }
}


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