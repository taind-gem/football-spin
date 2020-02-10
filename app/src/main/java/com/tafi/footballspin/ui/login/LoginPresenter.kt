package com.tafi.footballspin.ui.login

import com.tafi.footballspin.ui.base.BasePresenter

/**
 * Created by taind-201 on 2/11/2020.
 */

class LoginPresenter<V : ILoginView>: BasePresenter<V>(),ILoginPresenter<V> {

    override fun onServerLoginClick(email: String?, password: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGoogleLoginClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFacebookLoginClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}