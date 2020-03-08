package com.tafi.footballspin.data.db

import com.tafi.footballspin.data.db.model.*
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by taind-201 on 2/12/2020.
 */

@Singleton
class AppDbHelper @Inject constructor(dbOpenHelper: DbOpenHelper) : DbHelper {

    private var mDaoSession: DaoSession = DaoMaster(dbOpenHelper.writableDb).newSession()

    /*
     * Player database
     */
    override fun isPlayerEmpty(): Observable<Boolean> {
        return Observable.fromCallable { mDaoSession.playerDao.count() == 0L }
    }

    override fun insertPlayer(player: Player): Observable<Boolean> {
        return Observable.fromCallable {
            val id =  mDaoSession.playerDao.insert(player)
            return@fromCallable id > 0
        }
    }

    override fun updatePlayer(player: Player): Observable<Boolean> {
        return Observable.fromCallable {
            mDaoSession.playerDao.update(player)
            return@fromCallable true
        }
    }

    override fun getPlayerList(): Observable<List<Player>> {
        return Observable.fromCallable { mDaoSession.playerDao.loadAll() }
    }

    override fun savePlayerList(players: List<Player>): Observable<Boolean> {
        return Observable.fromCallable {
            mDaoSession.playerDao.insertInTx(players)
            return@fromCallable true
        }
    }

    /*
     * Match database
     */
    override fun getMatchList(): Observable<List<Match>> {
        return Observable.fromCallable { mDaoSession.matchDao.loadAll() }
    }

    override fun saveMatch(match: Match): Observable<Boolean> {
        return Observable.fromCallable {
            val statId = mDaoSession.statisticDao.insert(match.statistic)
            match.statisticId = statId
            val matchId = mDaoSession.matchDao.insert(match)
            return@fromCallable matchId > 0 && statId > 0
        }
    }


    /*
     * Team database
     */
    override fun isTeamEmpty(): Observable<Boolean> {
        return Observable.fromCallable { mDaoSession.teamDao.count() == 0L }
    }

    override fun getTeamList(): Observable<List<Team>> {
        return Observable.fromCallable { mDaoSession.teamDao.loadAll() }
    }

    override fun saveTeamList(teams: List<Team>): Observable<Boolean> {
        return Observable.fromCallable {
            mDaoSession.teamDao.insertInTx(teams)
            return@fromCallable true
        }
    }

}