package com.tafi.footballspin.ui.splash

import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.ui.base.IPresenter
import com.tafi.footballspin.ui.base.IView

interface ISplashPresenter<V : IView> : IPresenter<V> {

    fun addPlayer(player: Player)

}