package com.tafi.footballspin.ui.main.home

import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.ui.base.IView

/**
 * Created by taind-201 on 2/14/2020.
 */

interface IHomeView : IView {

    fun updateHomeView(players: List<Player>)

}