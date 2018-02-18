package com.revolut.native.card


interface RevolutCard {

    val id: String

    fun printIdAsync()

    fun runAsync(l: (() -> Unit))

}

expect class RevolutCardImpl : RevolutCard