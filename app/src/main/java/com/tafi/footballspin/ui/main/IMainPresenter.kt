package com.tafi.footballspin.ui.main

import com.tafi.footballspin.ui.base.IPresenter
import com.tafi.footballspin.ui.base.IView

/**
 * Created by taind-201 on 2/14/2020.
 */

interface IMainPresenter<V : IView> : IPresenter<V> {

    fun onViewInitialized()

}