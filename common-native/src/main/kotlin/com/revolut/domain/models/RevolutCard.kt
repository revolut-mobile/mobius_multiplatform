package com.revolut.domain.models

import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

actual class RevolutCardImpl(
        override val id: String
) : RevolutCard {

    override fun printIdAsync() {
        dispatch_async(dispatch_get_main_queue()) {
            println("This card id is = $id")
        }
    }

    override fun runAsync(l: (() -> Unit)) {
        dispatch_async(dispatch_get_main_queue()) {
           l.invoke()
        }
    }

}
