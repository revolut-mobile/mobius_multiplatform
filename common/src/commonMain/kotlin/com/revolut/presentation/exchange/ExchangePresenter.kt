package com.revolut.presentation.exchange

import com.revolut.coroutines.launch
import com.revolut.coroutines.withContext
import com.revolut.domain.interactors.AllMarketsTickersInteractor
import com.revolut.presentation.base.BasePresenter
import kotlin.coroutines.CoroutineContext

@Suppress("MemberVisibilityCanBePrivate")
class ExchangePresenter(
        private val uiContext: CoroutineContext,
        private val workerContext: CoroutineContext,
        private val interactor: AllMarketsTickersInteractor
) : BasePresenter<ExchangeView>() {

    override fun onViewAttached() {
        super.onViewAttached()
        refresh()
    }

    fun refresh() {
        launch(uiContext) {
            withContext(workerContext) {
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

}
