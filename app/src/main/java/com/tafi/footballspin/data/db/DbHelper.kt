package com.tafi.footballspin.data.db

import com.tafi.footballspin.data.db.model.Match
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.data.db.model.Team
import io.reactivex.Observable

/**
 * Created by taind-201 on 2/12/2020.
 */

interface DbHelper {

    //player db
    fun isPlayerEmpty(): Observable<Boolean>

    fun insertPlayer(player: Player): Observable<Boolean>

    fun getPlayerList(): Observable<List<Player>>

    fun savePlayerList(players: List<Player>): Observable<Boolean>

    //match db
    fun saveMatch(match: Match): Observable<Long>

    //team db
    fun isTeamEmpty(): Observable<Boolean>

    fun getTeams(): Observable<List<Team>>

    fun saveTeamList(teams: List<Team>): Observable<Boolean>

}