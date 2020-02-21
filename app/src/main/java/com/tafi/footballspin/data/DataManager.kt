package com.tafi.footballspin.data

import com.tafi.footballspin.data.db.DbHelper
import com.tafi.footballspin.data.local.LocalHelper
import com.tafi.footballspin.data.prefs.PreferencesHelper
import com.tafi.footballspin.model.entity.LoggedInMode

/**
 * Created by taind-201 on 2/11/2020.
 */

interface DataManager : PreferencesHelper, DbHelper, LocalHelper {

    fun setUserAsLoggedOut()

    fun updateUserInfo(
        accessToken: String?,
        userId: Long?,
        loggedInMode: LoggedInMode?,
        userName: String?,
        email: String?,
        profilePicPath: String?
    )
}