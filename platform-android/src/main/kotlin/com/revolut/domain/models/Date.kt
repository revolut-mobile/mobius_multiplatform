package com.revolut.domain.models

import org.joda.time.DateTime

actual class Date actual constructor(
        utcMillis: Long
) {
    private val dateTime = DateTime(utcMillis)

    actual fun dayOfMonth(): Int = dateTime.dayOfMonth().get()

}

