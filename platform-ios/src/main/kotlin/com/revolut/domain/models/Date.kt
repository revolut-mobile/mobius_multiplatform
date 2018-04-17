package com.revolut.domain.models

actual class Date actual constructor(
        private val utcMillis: Long
) {

    actual fun dayOfMonth(): Int {
        TODO()
    }

}
