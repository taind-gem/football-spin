package com.tafi.footballspin.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tafi.footballspin.data.db.converter.ListTeamConverter
import java.io.Serializable

/**
 * Created by taind-201 on 2/10/2020.
 */

@Entity
data class Player(

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    var username: String,

    var nickname: String,

    var avatarUrl: String,

    var isJoin: Boolean,

    @TypeConverters(ListTeamConverter::class)
    var listTeam: List<Team>? = null

) : Serializable {

    constructor(username: String, nickname: String, avatarUrl: String) : this(
        null,
        username,
        nickname,
        avatarUrl,
        false
    )

}
