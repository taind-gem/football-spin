package com.tafi.footballspin.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by taind-201 on 2/16/2020.
 */

@Entity
data class Team (

    @PrimaryKey(autoGenerate = true)
    var id: Long,

    var key: String,

    var name: String,

    var abbr: String,

    var code: String,

    var leagueName: String,

    var leagueCode: String,

    var rate: Float,

    var isPlayed: Boolean

) : Serializable
