package com.tafi.footballspin.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.tafi.footballspin.data.db.model.Team

/**
 * Created by taind-201 on 3/14/2020.
 */
class TeamConverter {

    val gson = Gson()

    @TypeConverter
    fun fromString(str: String): Team {
        return gson.fromJson(str, Team::class.java)
    }

    @TypeConverter
    fun teamToString(team: Team): String {
        return gson.toJson(team)
    }

}