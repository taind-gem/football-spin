package com.tafi.footballspin.ui.login

import com.tafi.footballspin.di.PerActivity
import com.tafi.footballspin.ui.base.IPresenter

/**
 * Created by taind-201 on 2/11/2020.
 */

@PerActivity
interface ILoginPresenter<V : ILoginView> : IPresenter<V> {

    fun onServerLoginClick(email: String?, password: String?)

    fun onGoogleLoginClick()

    fun onFacebookLoginClick()

}