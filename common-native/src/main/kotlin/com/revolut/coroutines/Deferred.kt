package com.revolut.coroutines


actual class Deferred<T>() {

    actual suspend fun await(): T {
        TODO()
    }

}