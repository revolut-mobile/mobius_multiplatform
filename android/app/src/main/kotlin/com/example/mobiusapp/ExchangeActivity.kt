package com.example.mobiusapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.revolut.domain.interactors.AllMarketsTickersSimultaneousInteractor
import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.repositories.ExchangeRepository
import com.revolut.presentation.exchange.ExchangePresenter
import com.revolut.presentation.exchange.ExchangeView
import kotlinx.android.synthetic.main.activity_echange.*
import kotlinx.coroutines.Dispatchers

class ExchangeActivity : AppCompatActivity(), ExchangeView {

    private val exchangeRepository = ExchangeRepository()

    private val interactor = AllMarketsTickersSimultaneousInteractor(exchangeRepository, Dispatchers.Default)

    private val adapter by lazy {
        ListDelegationAdapter(AdapterDelegatesManager<List<Any>>().addDelegate(TickerItemDelegate()))
    }

    private val presenter = ExchangePresenter(
            interactor = interactor,
            UI = Dispatchers.Main
    )

    override fun showMarkets(tickers: List<Pair<Market, Ticker>>) {
        adapter.items = tickers.map {
            TickerItemDelegate.MarketTicker(it.first, it.second)
        }
        adapter.notifyDataSetChanged()
    }

    override fun showLoading(loading: Boolean) {
        loadingProgress.visibility = if (loading) VISIBLE else GONE
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

}
