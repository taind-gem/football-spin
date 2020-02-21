package com.tafi.footballspin.model

import java.io.Serializable

/**
 * Created by taind-201 on 2/16/2020.
 */
data class Team (
    val key: String,
    val name: String,
    val abbr: String,
    val code: String,
    var league_code: String,
    var league_name: String,
    val rate: Float
) : Serializable