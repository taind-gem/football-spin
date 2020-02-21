package com.tafi.footballspin.network.response

import com.google.gson.annotations.SerializedName

/**
 * Created by taind-201 on 2/20/2020.
 */

data class LoginResponse(

    @SerializedName("status_code")
    var statusCode: Int? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("profile")
    val profile: ProfileResponse? = null

)

data class ProfileResponse(

    @SerializedName("user_id")
    val userId: Long? = null,

    @SerializedName("access_token")
    val accessToken: String? = null,

    @SerializedName("user_name")
    val userName: String? = null,

    @SerializedName("email")
    val userEmail: String? = null,

    @SerializedName("server_profile_pic_url")
    val serverProfilePicUrl: String? = null,

    @SerializedName("fb_profile_pic_url")
    val fbProfilePicUrl: String? = null,

    @SerializedName("google_profile_pic_url")
    val googleProfilePicUrl: String? = null

)