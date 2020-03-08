package com.tafi.footballspin.ui.main.result

import com.tafi.footballspin.ui.base.IPresenter

/**
 * Created by taind-201 on 3/7/2020.
 */
interface IResultPresenter<V : IResultView> : IPresenter<V> {

    fun onViewInitialized()

}