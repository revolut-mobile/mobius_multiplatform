package com.revolut.presentation.base

/**
 * Created by yatsinar on 19/02/2018.
 * Revolut
 * All rights reserved
 */
abstract class BasePresenter<V : BaseView> {

    protected var view: V? = null
        private set

    fun attach(view: V) {
        this.view = view
        onViewAttached()
    }

    fun detach() {
        this.view = null
        onViewDetached()
    }

    open fun onViewAttached() {

    }

    open fun onViewDetached() {

    }

}


