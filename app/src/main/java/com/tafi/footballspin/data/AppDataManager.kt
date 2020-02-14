package com.tafi.footballspin.data

import android.content.Context
import com.tafi.footballspin.data.db.DbHelper
import com.tafi.footballspin.data.db.model.Match
import com.tafi.footballspin.data.prefs.PreferencesHelper
import com.tafi.footballspin.di.ApplicationContext
import com.tafi.footballspin.model.entity.LoggedInMode
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by taind-201 on 2/12/2020.
 */

class AppDataManager @Inject constructor(
    @ApplicationContext val context: Context,
    private val mDbHelper: DbHelper,
    private val mPreferencesHelper: PreferencesHelper
) : DataManager {

    override fun setUserAsLoggedOut() {
        updateUserInfo(
            null,
            null,
            LoggedInMode.MODE_LOGGED_OUT,
            null,
            null,
            null
        )
    }

    override fun updateUserInfo(
        accessToken: String?,
        userId: Long?,
        loggedInMode: LoggedInMode?,
        userName: String?,
        email: String?,
        profilePicPath: String?
    ) {
        setAccessToken(accessToken)
        setCurrentUserId(userId)
        setCurrentUserLoggedInMode(loggedInMode!!)
        setCurrentUserName(userName)
        setCurrentUserEmail(email)
        setCurrentUserProfilePicUrl(profilePicPath)
    }

    override fun getCurrentUserLoggedInMode(): Int {
        return mPreferencesHelper.getCurrentUserLoggedInMode()
    }

    override fun setCurrentUserLoggedInMode(mode: LoggedInMode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode)
    }

    override fun getCurrentUserId(): Long? {
        return mPreferencesHelper.getCurrentUserId()
    }

    override fun setCurrentUserId(userId: Long?) {
        return mPreferencesHelper.setCurrentUserId(userId)
    }

    override fun getCurrentUserName(): String? {
        return mPreferencesHelper.getCurrentUserName()
    }

    override fun setCurrentUserName(userName: String?) {
        return mPreferencesHelper.setCurrentUserName(userName)
    }

    override fun getCurrentUserEmail(): String? {
        return mPreferencesHelper.getCurrentUserEmail()
    }

    override fun setCurrentUserEmail(email: String?) {
        return mPreferencesHelper.setCurrentUserEmail(email)
    }

    override fun getCurrentUserProfilePicUrl(): String? {
        return mPreferencesHelper.getCurrentUserProfilePicUrl()
    }

    override fun setCurrentUserProfilePicUrl(profilePicUrl: String?) {
        return mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl)
    }

    override fun getAccessToken(): String? {
        return mPreferencesHelper.getAccessToken()
    }

    override fun setAccessToken(accessToken: String?) {
        mPreferencesHelper.setAccessToken(accessToken)
    }

    override fun saveMatch(match: Match): Observable<Long> {
        return mDbHelper.saveMatch(match)
    }

}
