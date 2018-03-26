package com.example.mobiusapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.revolut.data.Network
import com.revolut.domain.interactors.AllMarketsTickersSimultaneousInteractor
import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.repositories.ExchangeRepository
import com.revolut.presentation.exchange.ExchangePresenter
import com.revolut.presentation.exchange.ExchangeView
import kotlinx.android.synthetic.main.activity_echange.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI


class ExchangeActivity : AppCompatActivity(), ExchangeView {

    private val exchangeRepository = ExchangeRepository(Network().bittrexApi)
    private val allMarketTickersInteractor
            = AllMarketsTickersSimultaneousInteractor(exchangeRepository, CommonPool)

    private val adapter by lazy {
        ListDelegationAdapter(AdapterDelegatesManager<List<Any>>().addDelegate(TickerItemDelegate()))
    }

    private val presenter = ExchangePresenter(
            uiContext = UI,
            allMarketsTickersInteractor = allMarketTickersInteractor
    )

    override fun showMarket(tickers: Map<Market, Ticker>) {
        adapter.items = tickers.map { TickerItemDelegate.MarketTicker(it.key, it.value) }
        adapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        presenter.attach(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_echange)

        tickersRecyclerView.adapter = adapter
        tickersRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun showLoading(loading: Boolean) {
        loadingProgress.visibility = if (loading) VISIBLE else GONE
    }



}