package com.tafi.footballspin.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tafi.footballspin.data.db.model.Team
import java.util.*

/**
 * Created by taind-201 on 3/14/2020.
 */

class ListTeamConverter {

    var gson = Gson()

    @TypeConverter
    fun fromTimestamp(data: String?): List<Team>? {

        if (data == null){
            return Collections.emptyList()
        }
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(data, listType)


    }

    @TypeConverter
    fun listTeamToString(someObjects: List<Team>?): String? {
        return gson.toJson(someObjects)
    }

}