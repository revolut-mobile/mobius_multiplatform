package com.revolut.presentation

import com.revolut.domain.models.RevolutCard

/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
interface MainView {

    fun showCard(list: List<RevolutCard>)

}