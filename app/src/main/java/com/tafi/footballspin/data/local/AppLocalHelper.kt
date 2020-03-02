package com.tafi.footballspin.data.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tafi.footballspin.data.db.model.Team
import com.tafi.footballspin.di.scope.ApplicationContext
import com.tafi.footballspin.model.League
import com.tafi.footballspin.utils.AppConstants
import io.reactivex.Observable
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject


/**
 * Created by taind-201 on 2/16/2020.
 */
class AppLocalHelper @Inject constructor(
    @ApplicationContext val context: Context
) : LocalHelper {

    override fun readAllTeamFromAsset(): Observable<List<Team>> {
        val listAllTeam = mutableListOf<Team>()

        val jsonFileString = getJsonData(AppConstants.ALL_TEAM_FILE_NAME)
        val jsonObj = JSONObject(jsonFileString)
        val iterator = jsonObj.keys()
        while (iterator.hasNext()) {
            val key = iterator.next()
            try {
                val value = jsonObj.get(key) as JSONArray
                val listLeagueType = object : TypeToken<List<League>>() {}.type
                val allLeague: List<League> = Gson().fromJson(value.toString(), listLeagueType)
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

        return Observable.fromCallable { listAllTeam }
    }

    private fun getJsonData(fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}