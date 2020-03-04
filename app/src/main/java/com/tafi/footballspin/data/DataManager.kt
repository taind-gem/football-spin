package com.tafi.footballspin.data

import com.tafi.footballspin.data.db.DbHelper
import com.tafi.footballspin.data.network.NetworkManager
import com.tafi.footballspin.data.prefs.PreferencesHelper
import com.tafi.footballspin.model.entity.LoggedInMode
import io.reactivex.Observable

/**
 * Created by taind-201 on 2/11/2020.
 */

interface DataManager : NetworkManager, PreferencesHelper, DbHelper {

    fun setUserAsLoggedOut()

    fun seedDatabaseTeams(): Observable<Boolean>

    fun seedDatabasePlayers(): Observable<Boolean>

    fun updateUserInfo(
        accessToken: String?,
        userId: Long?,
        loggedInMode: LoggedInMode?,
        userName: String?,
        email: String?,
        profilePicPath: String?
    )
}