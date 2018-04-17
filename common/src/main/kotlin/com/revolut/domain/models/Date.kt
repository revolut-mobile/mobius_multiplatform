package com.revolut.domain.models

expect class Date(utcMillis: Long) {

    fun dayOfMonth(): Int

}
