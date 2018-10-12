package com.revolut.coroutines


import platform.darwin.*
import kotlin.coroutines.*

abstract class ContinuationDispatcher :
        AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {

    abstract fun <T> dispatchResumeWith(value: Result<T>, continuation: Continuation<T>): Boolean

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        return DispatchedContinuation(this, continuation)
    }

}

internal class DispatchedContinuation<T>(
        private val dispatcher: ContinuationDispatcher,
        private val continuation: Continuation<T>
) : Continuation<T> {

    override val context: CoroutineContext = continuation.context

    override fun resumeWith(result: Result<T>) {
        if (!dispatcher.dispatchResumeWith(result, continuation)) {
            continuation.resumeWith(result)
        }
    }

}


class AsyncDispatcher : ContinuationDispatcher() {

    private val queue = dispatch_queue_create("com.revolut.queue", null)

    override fun <T> dispatchResumeWith(value: Result<T>, continuation: Continuation<T>): Boolean {
        println("AsyncDispatcher resume")
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT.toLong(), 0)) {
            continuation.resumeWith(value)
        }
        return true
    }
}


class MainQueueDispatcher : ContinuationDispatcher() {

    override fun <T> dispatchResumeWith(value: Result<T>, continuation: Continuation<T>): Boolean {
        println("MainQueueDispatcher resume")
        dispatch_async(dispatch_get_main_queue()) {
            continuation.resumeWith(value)
        }
        return true
    }

}