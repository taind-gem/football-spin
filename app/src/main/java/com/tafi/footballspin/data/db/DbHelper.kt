package com.tafi.footballspin.data.db

import com.tafi.footballspin.data.db.model.Match
import io.reactivex.Observable

/**
 * Created by taind-201 on 2/12/2020.
 */

interface DbHelper {

    fun saveMatch(match: Match) : Observable<Long>

}