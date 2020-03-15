package com.tafi.footballspin.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by taind-201 on 2/12/2020.
 */

@Entity
data class Match (

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    var createdTime: Long,

    var winPoint: Long,

    var drawPoint: Long,

    var hostPlayer: Player,

    var guestPlayer: Player,

    var hostTeam: Team,

    var guestTeam: Team,

    var statistic: Statistic

)