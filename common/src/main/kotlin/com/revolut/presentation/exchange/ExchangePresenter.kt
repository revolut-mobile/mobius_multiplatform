package com.revolut.presentation.exchange

import com.revolut.coroutines.Deferred
import com.revolut.coroutines.async
import com.revolut.domain.interactors.AllMarketsTickersInteractor
import com.revolut.presentation.base.BasePresenter
import kotlin.coroutines.experimental.CoroutineContext

class ExchangePresenter(
        private val uiContext: CoroutineContext,
        private val allMarketsTickersInteractor: AllMarketsTickersInteractor
) : BasePresenter<ExchangeView>() {

    private var running: Deferred<Unit?>? = null

    override fun onViewAttached() {
        super.onViewAttached()
        refresh()
    }

    fun refresh() {
        running?.cancel()
        running = async(uiContext) {
            view?.showLoading(true)
            val markets = allMarketsTickersInteractor.getTickersForAllMarkets()
            view?.showLoading(false)
            view?.showMarket(markets)
        }
    }

}
