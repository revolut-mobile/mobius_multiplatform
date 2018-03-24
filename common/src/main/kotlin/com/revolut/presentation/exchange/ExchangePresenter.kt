package com.revolut.presentation.exchange

import com.revolut.coroutines.launch
import com.revolut.domain.interactors.AllMarketsTickersInteractor
import com.revolut.presentation.base.BasePresenter
import kotlin.coroutines.experimental.CoroutineContext

class ExchangePresenter(
        private val uiContext: CoroutineContext,
        private val allMarketsTickersInteractor: AllMarketsTickersInteractor
) : BasePresenter<ExchangeView>() {

    override fun onViewAttached() {
        super.onViewAttached()
        launch(uiContext) {
            view?.showLoading(true)
            val markets = allMarketsTickersInteractor.getTickersForAllMarkets()
            view?.showLoading(false)
            view?.showMarketTickers(markets)
        }
    }

}
