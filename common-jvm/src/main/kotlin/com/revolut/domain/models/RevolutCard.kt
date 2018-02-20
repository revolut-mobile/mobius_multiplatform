package com.revolut.domain.models

import io.reactivex.Observable
import io.reactivex.Scheduler
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

actual class RevolutCardImpl(
        override val id: String,
        private val mainThread: Scheduler
) : RevolutCard {

    override fun printIdAsync() {
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribe {
                    println("Card id = $id, executed on thread = ${Thread.currentThread().name}")
                }
    }

    override fun runAsync(l: () -> Unit) {
        class CurrentThreadExecutor : Executor {
            override fun execute(command: Runnable?) {
                command?.run()
            }
        }

        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(mainThread)
                .subscribe {
                    l.invoke()
                }
    }

}
