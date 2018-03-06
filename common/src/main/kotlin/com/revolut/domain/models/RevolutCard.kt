package com.revolut.domain.models

interface RevolutCard {

    val id: String

}

expect class RevolutCardImpl : RevolutCard