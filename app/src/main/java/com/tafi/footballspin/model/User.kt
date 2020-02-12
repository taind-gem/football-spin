package com.tafi.footballspin.model

/**
 * Created by taind-201 on 2/10/2020.
 */

data class User (
    val id: Long,
    var username: String? = null,
    var nickname: String? = null,
    var fbId: String? = null,
    var email: String? = null,
    var token: String? = null,
    var birthday: String? = null,
    var phone: String? = null,
    var sex: Int,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
