package com.tafi.footballspin.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.tafi.footballspin.data.db.model.Statistic

/**
 * Created by taind-201 on 3/14/2020.
 */
class StatisticConverter {

    val gson = Gson()

    @TypeConverter
    fun fromString(str: String): Statistic {
        return gson.fromJson(str, Statistic::class.java)
    }

    @TypeConverter
    fun teamToString(statistic: Statistic): String {
        return gson.toJson(statistic)
    }

}