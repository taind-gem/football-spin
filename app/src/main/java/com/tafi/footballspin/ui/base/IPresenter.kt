package com.tafi.footballspin.ui.base

import com.tafi.footballspin.model.ApiError

/**
 * Created by taind-201 on 2/7/2020.
 */

interface IPresenter<V : IView> {

    fun onAttach(view: V)

    fun onDetach()

    fun handleApiError(error: ApiError?)

    fun setUserAsLoggedOut()
}
