package com.revolut.presentation.cards

import com.revolut.domain.models.RevolutCard
import com.revolut.presentation.base.BaseView

/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
interface CardsView : BaseView {

    fun showCard(list: List<RevolutCard>)

}