package com.revolut.coroutines

import kotlin.coroutines.experimental.*
import kotlin.coroutines.experimental.intrinsics.*

import platform.darwin.*

class AsyncDispatcher : ContinuationDispatcher() {

    private val queue = dispatch_queue_create("com.revolut.queue", null)

    override fun <T> dispatchResume(value: T, continuation: Continuation<T>): Boolean {
        dispatch_async(queue) {
            continuation.resume(value)
        }
        return true
    }

    override fun dispatchResumeWithException(exception: Throwable, continuation: Continuation<*>): Boolean {
        dispatch_async(queue) {
            continuation.resumeWithException(exception)
        }
        return false
    }
}