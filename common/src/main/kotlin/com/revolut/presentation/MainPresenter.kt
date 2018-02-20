package com.revolut.presentation

import com.revolut.domain.cards.GetAllCardsInteractor
import com.revolut.domain.models.RevolutCard
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
expect class MainPresenter {

    fun attach(view: MainView)


    fun detach()

}


