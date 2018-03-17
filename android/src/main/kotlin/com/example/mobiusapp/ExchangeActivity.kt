package com.example.mobiusapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.revolut.data.Network
import com.revolut.domain.interactors.AllMarketsTickersInteractor
import com.revolut.domain.repositories.ExchangeRepository
import com.revolut.presentation.exchange.ExchangePresenter
import com.revolut.presentation.exchange.ExchangeView
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
class ExchangeActivity : AppCompatActivity(), ExchangeView {

    private val presenter = ExchangePresenter(UI, AllMarketsTickersInteractor(ExchangeRepository(Network().bittrexApi), CommonPool))

    override fun showMarketTickers(string: String) {
        Log.d("Markets", string)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_echange)
    }

    override fun onStart() {
        super.onStart()
        presenter.attach(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

}