package com.example.mobiusapp

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.revolut.domain.interactors.CardsInteractor
import com.revolut.domain.models.RevolutCard
import com.revolut.domain.models.RevolutCardImpl
import com.revolut.domain.repositories.CardsRepository
import com.revolut.presentation.cards.CardsPresenter
import com.revolut.presentation.cards.CardsView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.HandlerContext
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.startCoroutine

class MainActivity : AppCompatActivity(), CardsView {

    private val presenter = CardsPresenter(CardsInteractor(CardsRepository()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val card = RevolutCardImpl("id.android", AndroidSchedulers.mainThread())
            card.printIdAsync()
            card.runAsync {
                println("Card id = ${card.id}, thread = ${Thread.currentThread().name}")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attach(this)

        launch(UI) { // launch new coroutine in background and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
        }
        println("Hello,") // main thread continues while coroutine is delayed
        Thread.sleep(2000L) //
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

    override fun showCard(list: List<RevolutCard>) {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    open class EmptyContinuation(override val context: CoroutineContext) : Continuation<Any?> {
        companion object : EmptyContinuation(context)

        override fun resume(value: Any?) {}
        override fun resumeWithException(exception: Throwable) {
            throw exception
        }
    }

    private fun launchCustom(context: CoroutineContext, block: suspend () -> Unit) {
        block.startCoroutine(EmptyContinuation(context))
    }

}
