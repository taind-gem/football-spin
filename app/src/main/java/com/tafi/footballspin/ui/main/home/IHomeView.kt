package com.tafi.footballspin.ui.main.home

import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.data.db.model.Team
import com.tafi.footballspin.model.entity.SideType
import com.tafi.footballspin.ui.base.IView

/**
 * Created by taind-201 on 2/14/2020.
 */

interface IHomeView : IView {

    fun updatePlayer(player: Player, type: SideType)

    fun updateTeam(team: Team, type: SideType)

    fun showResultDialog()

}