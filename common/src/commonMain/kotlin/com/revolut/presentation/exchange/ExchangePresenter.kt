package com.revolut.presentation.exchange

import com.revolut.domain.interactors.AllMarketsTickersInteractor
import com.revolut.presentation.base.BasePresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

@Suppress("MemberVisibilityCanBePrivate")
class ExchangePresenter(
        private val interactor: AllMarketsTickersInteractor,
        private val UI: CoroutineDispatcher
) : BasePresenter<ExchangeView>() {

    override fun onViewAttached() {
        super.onViewAttached()
        refresh()
    }

    fun refresh() {
        launch(UI) {
            view?.showLoading(true)
            try {
                val markets = interactor.getTickersForAllMarkets()
                view?.showMarkets(markets)
            } catch (e: Throwable) {
                println(e.message)
            }
            view?.showLoading(false)
        }
    }

}
