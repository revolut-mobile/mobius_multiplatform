package com.revolut.presentation.exchange

import com.revolut.domain.models.Market
import com.revolut.domain.models.Ticker
import com.revolut.presentation.base.BaseView

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
interface ExchangeView: BaseView {

    fun showMarketTickers(tickers: Map<Market, Ticker>)

}