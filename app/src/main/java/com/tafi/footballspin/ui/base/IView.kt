package com.tafi.footballspin.ui.base

import android.view.View
import androidx.annotation.StringRes

/**
 * Created by taind-201 on 2/7/2020.
 */

interface IView {

    val isNetworkConnected: Boolean

    fun showLoading()

    fun hideLoading()

    fun openActivityOnTokenExpire()

    fun onError(@StringRes resId: Int)

    fun onError(message: String?)

    fun showMessage(message: String?)

    fun showMessage(@StringRes resId: Int)

    fun hideKeyboard()

    fun hideKeyboardWhenClickOutsideEdittext(view: View)

}
