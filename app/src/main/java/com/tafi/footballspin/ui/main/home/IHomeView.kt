package com.tafi.footballspin.ui.main.home

import com.tafi.footballspin.model.League
import com.tafi.footballspin.ui.base.IView

/**
 * Created by taind-201 on 2/14/2020.
 */

interface IHomeView : IView {

    fun onLoadDataSuccess(listLeague: List<League>)

}