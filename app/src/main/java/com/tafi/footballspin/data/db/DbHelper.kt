package com.tafi.footballspin.data.db

import com.tafi.footballspin.data.db.model.Match
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.data.db.model.Team
import io.reactivex.Observable

/**
 * Created by taind-201 on 2/12/2020.
 */

interface DbHelper {

    fun getPlayers(): Observable<List<Player>>

    fun insertPlayer(player: Player): Observable<Boolean>

    fun saveMatch(match: Match): Observable<Long>

    fun getTeams(): Observable<List<Team>>

    fun saveTeamList(listLeague: List<Team>): Observable<Boolean>

}