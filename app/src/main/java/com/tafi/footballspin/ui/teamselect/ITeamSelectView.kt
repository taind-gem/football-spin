package com.tafi.footballspin.ui.teamselect

import com.tafi.footballspin.data.db.model.Team
import com.tafi.footballspin.ui.base.IView

/**
 * Created by taind-201 on 3/3/2020.
 */

interface ITeamSelectView : IView {

    fun updatePlayerList(teams: List<Team>)

}