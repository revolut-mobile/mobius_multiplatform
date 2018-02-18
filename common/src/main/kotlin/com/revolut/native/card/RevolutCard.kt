package com.revolut.native.card


interface RevolutCard {

    val id: String

    fun runOnBackgroundThread()

    fun runOnBackgroundThreadBlock(l: (() -> Unit))

}

expect class RevolutCardImpl : RevolutCard