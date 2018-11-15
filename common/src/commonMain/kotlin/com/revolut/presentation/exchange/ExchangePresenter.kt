package com.revolut.presentation.exchange

import com.revolut.domain.interactors.AllMarketsTickersInteractor
import com.revolut.presentation.base.BasePresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
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
        GlobalScope.launch(UI) {
            view?.showLoading(true)
            try {
                println("Get markets")

                val markets = interactor.getTickersForAllMarkets()

                println("Markets $markets")
                view?.showMarkets(markets)
            } catch (e: Throwable) {
                println("Error ocured")
                e.printStackTrace()
            }
            view?.showLoading(false)
        }
    }

}
