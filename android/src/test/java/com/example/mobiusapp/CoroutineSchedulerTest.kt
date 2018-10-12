package com.example.mobiusapp

import io.reactivex.Observable
import io.reactivex.internal.schedulers.NewThreadScheduler
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.TimeUnit

class CoroutineSchedulerTest {

    @Test
    fun test() {
        val test = Observable.just("1000")
                .delay(300, TimeUnit.MILLISECONDS, CoroutineScheduler())
                .test()

        test.await(400, TimeUnit.MILLISECONDS)
        test.assertValue { it == "1000" }
    }

    @Test
    fun test2() {
        val test = Observable.fromPublisher<Int> { publisher ->
            (0..10).forEach {
                publisher.onNext(it)
                Thread.sleep(500)
            }
            publisher.onComplete()
        }.subscribeOn(NewThreadScheduler())
                .debounce(600, TimeUnit.MILLISECONDS, NewThreadScheduler())
                .test()

        test.awaitTerminalEvent()
        test.assertValues(10)
    }

    @Test
    fun test3() {
        val test = Observable.fromPublisher<Int> { publisher ->
            (0..10).forEach {
                publisher.onNext(it)
                Thread.sleep(500)
            }
            publisher.onComplete()
        }.subscribeOn(NewThreadScheduler())
                .debounce(600, TimeUnit.MILLISECONDS, CoroutineScheduler())
                .test()

        test.awaitTerminalEvent()
        test.assertValues(10)
    }

    @Test
    fun test4() {
        val test = Observable.fromPublisher<Int> { publisher ->
            (0..10).forEach {
                publisher.onNext(it)
                Thread.sleep(500)
            }
            publisher.onComplete()
        }.subscribeOn(NewThreadScheduler())
                .throttleFirst(600, TimeUnit.MILLISECONDS, NewThreadScheduler())
                .test()

        test.awaitTerminalEvent()
        test.assertValues(0, 2, 4, 6, 8, 10)
    }

    @Test
    fun test5() {
        val test = Observable.fromPublisher<Int> { publisher ->
            (0..10).forEach {
                publisher.onNext(it)
                Thread.sleep(500)
            }
            publisher.onComplete()
        }.subscribeOn(NewThreadScheduler())
                .throttleFirst(600, TimeUnit.MILLISECONDS, CoroutineScheduler())
                .test()

        test.awaitTerminalEvent()
        test.assertValues(0, 2, 4, 6, 8, 10)
    }

    @Test
    //@Throws(OutOfMemoryError::class)
    fun test6() {
        val scheduler = Schedulers.single()
        val test = Observable.merge((1..20_000).map {
            Observable.just(it).delay(1000, TimeUnit.MILLISECONDS, scheduler)
        }).test()

        test.await(5000, TimeUnit.MILLISECONDS)

        assertEquals(20_000, test.values().size)
    }

    @Test
    fun test7() {
        val coroutine = CoroutineScheduler()
        val test = Observable.merge((1..20_000).map {
            Observable.just(it).delay(1000, TimeUnit.MILLISECONDS, coroutine)
        }).test()

        test.await(5000, TimeUnit.MILLISECONDS)

        assertEquals(20_000, test.values().size)
    }

}
