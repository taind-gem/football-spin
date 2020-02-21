package com.tafi.footballspin.data.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tafi.footballspin.di.scope.ApplicationContext
import com.tafi.footballspin.model.League
import com.tafi.footballspin.utils.AppConstants
import io.reactivex.Observable
import org.json.JSONArray
import java.io.IOException
import javax.inject.Inject
import org.json.JSONException
import org.json.JSONObject


/**
 * Created by taind-201 on 2/16/2020.
 */
class AppLocalHelper @Inject constructor(
    @ApplicationContext val context: Context
) : LocalHelper {

    override fun getAllTeamFromAssets(): Observable<List<League>> {
        val listAllLeague = mutableListOf<League>()

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
                        club.league_code = league.code
                        club.league_name = league.name
                    }
                    league.type = key
                }
                if (allLeague.isNotEmpty()) listAllLeague.addAll(allLeague)
            } catch (e: JSONException) {
                continue
            }
        }

        return Observable.fromCallable { listAllLeague }
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