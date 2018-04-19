package com.revolut.domain.models

import platform.Foundation.*

actual class Date actual constructor(
        private val utcMillis: Long
) {

    private val dateTime = NSDate.dateWithTimeIntervalSince1970(utcMillis / 1000.0)

    private val calendarComponents by lazy {
        with(NSCalendar(ident = NSGregorianCalendar)) {
            components(unitFlags = NSCalendarUnitDay, fromDate = dateTime)
        }
    }

    actual fun dayOfMonth(): Int {
        return calendarComponents.day.toInt()
    }

}
