package com.revolut.presentation.exchange

import com.revolut.domain.interactors.AllMarketsTickersInteractor
import com.revolut.presentation.base.BasePresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@Suppress("MemberVisibilityCanBePrivate")
class ExchangePresenter(
    private val interactor: AllMarketsTickersInteractor,
    private val UI: CoroutineDispatcher
) : BasePresenter<ExchangeView>() {

    override fun onViewAttached() {
        super.onViewAttached()

        GlobalScope.launch(UI) {
            view?.showLoading(true)
            try {
                interactor.observeTickers(UI).consumeEach { markets ->
                    println("Markets $markets")
                    view?.showMarkets(markets)
                    view?.showLoading(false)
                }
            } catch (e: Throwable) {
                println(e)
            }
        }
    }

}
