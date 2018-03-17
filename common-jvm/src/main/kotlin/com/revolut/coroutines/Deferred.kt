package com.revolut.coroutines

import kotlinx.coroutines.experimental.Deferred

actual class Deferred<T>(
        private val wrappedDeferred: Deferred<T>
) {

    actual suspend fun await(): T {
        return wrappedDeferred.await()
    }

}