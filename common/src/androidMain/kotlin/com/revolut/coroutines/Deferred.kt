package com.revolut.coroutines

import kotlinx.coroutines.Deferred

actual class Deferred<out T>(
        private val wrappedDeferred: Deferred<T>
) {

    actual suspend fun await(): T {
        return wrappedDeferred.await()
    }

}