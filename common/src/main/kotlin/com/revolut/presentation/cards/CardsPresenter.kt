package com.revolut.presentation.cards

import com.revolut.presentation.base.BasePresenter

expect class CardsPresenter : BasePresenter<CardsView> {

    override fun onViewAttached()

    override fun onViewDetached()

}
