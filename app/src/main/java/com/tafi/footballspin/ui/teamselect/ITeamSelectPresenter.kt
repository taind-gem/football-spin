package com.tafi.footballspin.ui.teamselect

import com.tafi.footballspin.ui.base.IPresenter
import com.tafi.footballspin.ui.base.IView

/**
 * Created by taind-201 on 3/3/2020.
 */

interface ITeamSelectPresenter<V : IView> : IPresenter<V> {

    fun getTeams()

}