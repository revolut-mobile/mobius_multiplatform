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
            val markets = allMarketsTickersInteractor.getTickersForAllMarkets()
            view?.showMarketTickers(markets)
        }
    }

}
