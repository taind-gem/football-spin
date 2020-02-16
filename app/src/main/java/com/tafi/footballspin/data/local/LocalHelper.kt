package com.tafi.footballspin.data.local

import com.tafi.footballspin.model.League
import io.reactivex.Observable

/**
 * Created by taind-201 on 2/16/2020.
 */
interface LocalHelper {

    fun getAllTeamFromAssets(): Observable<List<League>>

}