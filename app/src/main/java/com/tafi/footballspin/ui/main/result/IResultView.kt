package com.tafi.footballspin.ui.main.result

import com.tafi.footballspin.data.db.model.Match
import com.tafi.footballspin.ui.base.IView

/**
 * Created by taind-201 on 3/7/2020.
 */
interface IResultView : IView {

    fun updateMatchList(matches: List<Match>)

}