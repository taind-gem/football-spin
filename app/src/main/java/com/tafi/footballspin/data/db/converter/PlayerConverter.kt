package com.tafi.footballspin.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.tafi.footballspin.data.db.model.Player

/**
 * Created by taind-201 on 3/14/2020.
 */
class PlayerConverter {

    val gson = Gson()

    @TypeConverter
    fun fromString(str: String): Player {
        return gson.fromJson(str, Player::class.java)
    }

    @TypeConverter
    fun teamToString(player: Player): String {
        return gson.toJson(player)
    }

}