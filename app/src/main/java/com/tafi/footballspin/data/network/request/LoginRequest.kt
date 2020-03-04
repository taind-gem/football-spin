package com.tafi.footballspin.data.network.request

import com.google.gson.annotations.SerializedName

/**
 * Created by taind-201 on 2/20/2020.
 */

class LoginRequest {

    class ServerLoginRequest(
        @field:SerializedName("email") var email: String?,
        @field:SerializedName("password") var password: String?
    )

    class GoogleLoginRequest(
        @field:SerializedName("google_user_id") var googleUserId: String?,
        @field:SerializedName("google_id_token") var idToken: String?
    )

    class FacebookLoginRequest(
        @field:SerializedName("fb_user_id") var fbUserId: String?,
        @field:SerializedName("fb_access_token") var fbAccessToken: String?
    )

}