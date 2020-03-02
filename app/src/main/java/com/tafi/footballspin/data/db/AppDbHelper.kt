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

    override fun insertPlayer(player: Player): Observable<Boolean> {
        return Observable.fromCallable {
            mDaoSession.playerDao.insert(player)
            return@fromCallable true
        }
    }

    override fun getPlayers(): Observable<List<Player>> {
        return Observable.fromCallable { mDaoSession.playerDao.loadAll() }
    }

    override fun saveMatch(match: Match): Observable<Long> {
        return Observable.fromCallable { mDaoSession.matchDao.insert(match) }
    }

    override fun saveTeamList(listLeague: List<Team>): Observable<Boolean> {
        return Observable.fromCallable {
            mDaoSession.teamDao.insertInTx(listLeague)
            return@fromCallable true
        }
    }

}