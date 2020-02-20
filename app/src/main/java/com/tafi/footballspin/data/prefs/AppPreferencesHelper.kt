package com.tafi.footballspin.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.tafi.footballspin.di.scope.ApplicationContext
import com.tafi.footballspin.di.scope.PreferenceInfo
import com.tafi.footballspin.model.entity.LoggedInMode
import com.tafi.footballspin.utils.AppConstants
import javax.inject.Inject

/**
 * Created by taind-201 on 2/11/2020.
 */

class AppPreferencesHelper @Inject constructor(
    @ApplicationContext val context: Context,
    @PreferenceInfo val prefFileName: String
) : PreferencesHelper {

    private var mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun getCurrentUserLoggedInMode(): Int {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE, LoggedInMode.MODE_LOGGED_OUT.type)
    }

    override fun setCurrentUserLoggedInMode(mode: LoggedInMode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.type).apply()
    }

    override fun getCurrentUserId(): Long? {
        val userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX)
        return if (userId == AppConstants.NULL_INDEX) null else userId
    }

    override fun setCurrentUserId(userId: Long?) {
        val id = userId ?: AppConstants.NULL_INDEX
        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply()
    }

    override fun getCurrentUserName(): String? {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null)
    }

    override fun setCurrentUserName(userName: String?) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply()
    }

    override fun getCurrentUserEmail(): String? {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null)
    }

    override fun setCurrentUserEmail(email: String?) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply()
    }

    override fun getCurrentUserProfilePicUrl(): String? {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null)
    }

    override fun setCurrentUserProfilePicUrl(profilePicUrl: String?) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply()
    }

    override fun getAccessToken(): String? {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)
    }

    override fun setAccessToken(accessToken: String?) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()
    }


    companion object {
        const val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE"
        const val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"
        const val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"
        const val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"
        const val PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL"
        const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
    }

}