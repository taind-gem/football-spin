package com.tafi.footballspin.model

import com.tafi.footballspin.data.db.model.Team

/**
 * Created by taind-201 on 2/16/2020.
 */
data class League(
    val name: String,
    val code: String,
    var type: String,
    val clubs: MutableList<Team>? = null
)

