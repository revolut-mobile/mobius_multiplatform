package com.example.mobiusapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.revolut.data.Network
import com.revolut.domain.interactors.AllMarketsTickersSimultaneousInteractor
import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.repositories.ExchangeRepository
import com.revolut.presentation.exchange.ExchangePresenter
import com.revolut.presentation.exchange.ExchangeView
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI


class ExchangeActivity : AppCompatActivity(), ExchangeView {

    private val presenter = ExchangePresenter(
            UI,
            AllMarketsTickersSimultaneousInteractor(
                    ExchangeRepository(Network().bittrexApi), CommonPool)
    )

    override fun showMarketTickers(tickers: Map<Market, Ticker>) {
        TODO()
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