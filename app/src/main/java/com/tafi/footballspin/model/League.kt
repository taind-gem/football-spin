package com.tafi.footballspin.model

/**
 * Created by taind-201 on 2/16/2020.
 */
data class League(
    val name: String,
    val code: String,
    var type: String,
    val clubs: MutableList<Team>? = null
)

