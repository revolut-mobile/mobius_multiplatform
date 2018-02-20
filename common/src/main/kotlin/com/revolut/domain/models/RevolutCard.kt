package com.revolut.domain.models

//TODO make this simple model, move all stuff to interactors
//TODO Probably simple data class w/o expected/actual will be enough then
interface RevolutCard {

    val id: String

    fun printIdAsync()

    fun runAsync(l: (() -> Unit))

}

expect class RevolutCardImpl : RevolutCard