package com.tafi.footballspin.data.db

import com.tafi.footballspin.data.db.model.DaoMaster
import com.tafi.footballspin.data.db.model.DaoSession
import com.tafi.footballspin.data.db.model.Match
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by taind-201 on 2/12/2020.
 */

@Singleton
class AppDbHelper @Inject constructor(dbOpenHelper: DbOpenHelper) : DbHelper {

    private var mDaoSession: DaoSession = DaoMaster(dbOpenHelper.writableDb).newSession()

    override fun saveMatch(match: Match): Observable<Long> {
        return Observable.fromCallable { mDaoSession.matchDao.insert(match) }
    }

}