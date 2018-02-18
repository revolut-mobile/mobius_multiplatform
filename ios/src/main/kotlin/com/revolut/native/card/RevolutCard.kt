package com.revolut.native.card

import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

actual class RevolutCardImpl(
        override val id: String
) : RevolutCard {

    override fun runOnBackgroundThread() {
        dispatch_async(dispatch_get_main_queue()) {
            println("This is run on the background queue")
        }
    }

    override fun runOnBackgroundThreadBlock(l: (() -> Unit)) {
        dispatch_async(dispatch_get_main_queue()) {
           l.invoke()
        }
    }

}
