package com.tafi.footballspin.ui.splash

import com.tafi.footballspin.ui.base.IView

interface ISplashView : IView {

    fun openLoginActivity()

    fun openMainActivity()

    fun startSyncService()

}