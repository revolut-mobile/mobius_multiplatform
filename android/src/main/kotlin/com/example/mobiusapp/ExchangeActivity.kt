package com.example.mobiusapp

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobiusapp.databinding.ActivityEchangeBinding
import com.revolut.data.db.DbArgs
import com.revolut.domain.interactors.AllMarketsTickersSimultaneousInteractor
import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.domain.repositories.ExchangeRepository
import com.revolut.presentation.exchange.ExchangePresenter
import com.revolut.presentation.exchange.ExchangeView
import com.revolut.recyclerkit.animations.FadeInAnimator
import com.revolut.recyclerkit.delegates.DelegatesManager
import com.revolut.recyclerkit.delegates.DiffAdapter
import kotlinx.coroutines.Dispatchers

class ExchangeActivity : AppCompatActivity(), ExchangeView {

    private val exchangeRepository by lazy { ExchangeRepository(DbArgs(context = applicationContext)) }

    private val interactor by lazy { AllMarketsTickersSimultaneousInteractor(exchangeRepository) }

    private lateinit var binding: ActivityEchangeBinding

    private val adapter by lazy {
        DiffAdapter(
            DelegatesManager().addDelegate(TickerItemDelegate())
        )
    }

    private val presenter by lazy {
        ExchangePresenter(
            interactor = interactor,
            UI = Dispatchers.Main
        )
    }

    override fun showMarkets(tickers: List<Pair<Market, Ticker>>) {
        adapter.setItems(tickers.map {
            TickerItemDelegate.MarketTicker(it.first, it.second)
        })
    }

    override fun showLoading(loading: Boolean) {
        binding.loadingProgress.visibility = if (loading) VISIBLE else GONE
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
        binding = ActivityEchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tickersRecyclerView.adapter = adapter
        binding.tickersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.tickersRecyclerView.itemAnimator = FadeInAnimator()
    }
}
