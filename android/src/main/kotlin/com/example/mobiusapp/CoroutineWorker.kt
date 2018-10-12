package com.example.mobiusapp

import io.reactivex.Scheduler
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer
import io.reactivex.internal.disposables.EmptyDisposable
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class CoroutineScheduler : Scheduler() {

    override fun createWorker(): Worker = CoroutineWorker()

    class CoroutineWorker : Scheduler.Worker() {

        @Volatile
        internal var disposed: Boolean = false

        override fun isDisposed(): Boolean = disposed

        override fun schedule(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
            return if (disposed) {
                EmptyDisposable.INSTANCE
            } else scheduleActual(run, delay, unit, null)
        }

        private fun scheduleActual(run: Runnable, delayTime: Long, @NonNull unit: TimeUnit, @Nullable parent: DisposableContainer?): Disposable {
            val decoratedRun = RxJavaPlugins.onSchedule(run)

            val deferred = GlobalScope.async(Dispatchers.Default, CoroutineStart.DEFAULT) {
                delay(unit.toMillis(delayTime))
                decoratedRun.run()
            }

            val disposable = DisposableDeferred(deferred)
            parent?.add(disposable)

            return disposable
        }

        override fun dispose() {
            if (!disposed) {
                disposed = true
                //shutdown job
            }
        }

        private class DisposableDeferred<T>(val deferred: Deferred<T>) : Disposable {

            override fun isDisposed(): Boolean {
                return deferred.isCancelled
            }

            override fun dispose() {
                deferred.cancel(Throwable("Disposed"))
            }

        }

    }

}