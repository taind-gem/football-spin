package com.tafi.footballspin.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tafi.footballspin.data.db.DbHelper
import com.tafi.footballspin.data.db.model.Match
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.data.db.model.Team
import com.tafi.footballspin.data.network.NetworkManager
import com.tafi.footballspin.data.network.request.LoginRequest
import com.tafi.footballspin.data.network.response.LoginResponse
import com.tafi.footballspin.data.prefs.PreferencesHelper
import com.tafi.footballspin.di.scope.ApplicationContext
import com.tafi.footballspin.model.League
import com.tafi.footballspin.model.entity.LoggedInMode
import com.tafi.footballspin.utils.AppConstants
import com.tafi.footballspin.utils.CommonUtils
import io.reactivex.Observable
import io.reactivex.Single
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by taind-201 on 2/12/2020.
 */

class AppDataManager @Inject constructor(
    @ApplicationContext val context: Context,
    private val mDbHelper: DbHelper,
    private val mPreferencesHelper: PreferencesHelper,
    private val mNetworkManager: NetworkManager
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

    override fun seedDatabaseTeams(): Observable<Boolean> {
        return mDbHelper.isTeamEmpty()
            .concatMap { isEmpty ->
                if (isEmpty) {
                    saveTeamList(parseJSONStringToTeamsList())
                } else Observable.just(false)
            }

    }

    override fun seedDatabasePlayers(): Observable<Boolean> {
        return mDbHelper.isPlayerEmpty()
            .concatMap { isEmpty ->
                if (isEmpty) {
                    val players = arrayListOf(
                        Player("dungvv", "Việt Dũng", "dungvv"),
                        Player("tuanpb", "Tuan Phan", "tuanpb"),
                        Player("taind", "Taihandsome", "taind")
                    )
                    savePlayerList(players)
                } else Observable.just(false)
            }
    }

    private fun parseJSONStringToTeamsList(): List<Team> {
        val listAllTeam = mutableListOf<Team>()

        val jsonFileString =
            CommonUtils.readDataFromFile(context, AppConstants.ALL_TEAM_FILE_NAME)
        val jsonObj = JSONObject(jsonFileString)
        val iterator = jsonObj.keys()

        while (iterator.hasNext()) {
            val key = iterator.next()
            try {
                val value = jsonObj.get(key) as JSONArray
                val listLeagueType = object : TypeToken<List<League>>() {}.type
                val allLeague: List<League> =
                    Gson().fromJson(value.toString(), listLeagueType)
                for (league in allLeague) {
                    for (club in league.clubs!!) {
                        club.leagueCode = league.code
                        club.leagueName = league.name

                        if (allLeague.isNotEmpty()) listAllTeam.add(club)
                    }
                    league.type = key
                }
            } catch (e: JSONException) {
                continue
            }
        }
        return listAllTeam
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

    override fun doServerLoginApiCall(request: LoginRequest.ServerLoginRequest): Single<LoginResponse> {
        return mNetworkManager.doServerLoginApiCall(request)
    }

    override fun doGoogleLoginApiCall(request: LoginRequest.GoogleLoginRequest): Single<LoginResponse> {
        return mNetworkManager.doGoogleLoginApiCall(request)
    }

    override fun doFacebookLoginApiCall(request: LoginRequest.FacebookLoginRequest): Single<LoginResponse> {
        return mNetworkManager.doFacebookLoginApiCall(request)
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

    override fun isPlayerEmpty(): Observable<Boolean> {
        return mDbHelper.isPlayerEmpty()
    }

    override fun saveMatch(match: Match): Observable<Long> {
        return mDbHelper.saveMatch(match)
    }

    override fun isTeamEmpty(): Observable<Boolean> {
        return mDbHelper.isTeamEmpty()
    }

    override fun getTeamList(): Observable<List<Team>> {
        return mDbHelper.getTeamList()
    }

    override fun getPlayerList(): Observable<List<Player>> {
        return mDbHelper.getPlayerList()
    }

    override fun savePlayerList(players: List<Player>): Observable<Boolean> {
        return mDbHelper.savePlayerList(players)
    }

    override fun insertPlayer(player: Player): Observable<Boolean> {
        return mDbHelper.insertPlayer(player)
    }

    override fun updatePlayer(player: Player): Observable<Boolean> {
        return mDbHelper.updatePlayer(player)
    }

    override fun saveTeamList(teams: List<Team>): Observable<Boolean> {
        return mDbHelper.saveTeamList(teams)
    }


}
