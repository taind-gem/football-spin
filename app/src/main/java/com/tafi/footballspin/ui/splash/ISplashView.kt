package com.tafi.footballspin.ui.splash

import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.ui.base.IView

interface ISplashView : IView {

    fun updateAppVersion()

    fun openMainActivity()

    fun updatePlayerList(listPlayer: List<Player>)

}