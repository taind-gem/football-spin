package com.tafi.footballspin.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by taind-201 on 3/1/2020.
 */

@Entity
data class Statistic (

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    var hostScore: Long,

    var guestScore: Long,

    var red: Long,

    var yellow: Long

)