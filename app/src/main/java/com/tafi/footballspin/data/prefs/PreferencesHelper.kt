package com.tafi.footballspin.data.prefs

import com.tafi.footballspin.model.entity.LoggedInMode

/**
 * Created by taind-201 on 2/11/2020.
 */

interface PreferencesHelper {

    fun getCurrentUserLoggedInMode(): Int

    fun setCurrentUserLoggedInMode(mode: LoggedInMode)

    fun getCurrentUserId(): Long?

    fun setCurrentUserId(userId: Long?)

    fun getCurrentUserName(): String?

    fun setCurrentUserName(userName: String?)

    fun getCurrentUserEmail(): String?

    fun setCurrentUserEmail(email: String?)

    fun getCurrentUserProfilePicUrl(): String?

    fun setCurrentUserProfilePicUrl(profilePicUrl: String?)

    fun getAccessToken(): String?

    fun setAccessToken(accessToken: String?)

    fun getWinPoint(): Long

    fun setWinPoint(point: Long)

    fun getDrawPoint(): Long

    fun setDrawPoint(point: Long)
}