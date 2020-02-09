package com.tafi.footballspin.ui.base

/**
 * Created by taind-201 on 2/7/2020.
 */

interface MvpPresenter<V : MvpView> {

    fun onAttach(mvpView: V)

    fun onDetach()

    fun handleApiError(error: String)

    fun setUserAsLoggedOut()
}
