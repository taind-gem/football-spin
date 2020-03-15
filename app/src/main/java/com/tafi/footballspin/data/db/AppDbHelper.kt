package com.tafi.footballspin.data.db

import android.content.Context
import com.tafi.footballspin.data.db.model.Match
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.data.db.model.Team
import com.tafi.footballspin.di.scope.ApplicationContext
import com.tafi.footballspin.di.scope.RoomDatabase
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by taind-201 on 2/12/2020.
 */

@Singleton
class AppDbHelper @Inject constructor(
    @ApplicationContext val context: Context,
    @RoomDatabase val database: BaseDb): DbHelper {

    /*
     * Player database
     */
    override fun isPlayerEmpty(): Observable<Boolean> {
        return Observable.fromCallable { database.getPlayerDao().count() == 0L }
    }

    override fun insertPlayer(player: Player): Observable<Boolean> {
        return Observable.fromCallable {
            val id =  database.getPlayerDao().insert(player)
            return@fromCallable id > 0
        }
    }

    override fun updatePlayer(player: Player): Observable<Boolean> {
        return Observable.fromCallable {
            database.getPlayerDao().update(player)
            return@fromCallable true
        }
    }

    override fun getPlayerList(): Observable<List<Player>> {
        return Observable.fromCallable { database.getPlayerDao().loadAll() }
    }

    override fun savePlayerList(players: List<Player>): Observable<Boolean> {
        return Observable.fromCallable {
            database.getPlayerDao().insertListPlayer(players)
            return@fromCallable true
        }
    }

    /*
     * Match database
     */
    override fun getMatchList(): Observable<List<Match>> {
        return Observable.fromCallable { database.getMatchDao().loadAll() }
    }

    override fun saveMatch(match: Match): Observable<Boolean> {
        return Observable.fromCallable {
            val matchId = database.getMatchDao().insert(match)
            return@fromCallable matchId > 0
        }
    }


    /*
     * Team database
     */
    override fun isTeamEmpty(): Observable<Boolean> {
        return Observable.fromCallable { database.getTeamDao().count() == 0L }
    }

    override fun getTeamList(): Observable<List<Team>> {
        return Observable.fromCallable { database.getTeamDao().loadAll() }
    }

    override fun saveTeamList(teams: List<Team>): Observable<Boolean> {
        return Observable.fromCallable {
            database.getTeamDao().insertListTeam(teams)
            return@fromCallable true
        }
    }

}