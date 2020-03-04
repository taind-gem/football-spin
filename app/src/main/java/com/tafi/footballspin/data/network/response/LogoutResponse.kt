package com.tafi.footballspin.data.network.response

import com.google.gson.annotations.SerializedName

/**
 * Created by taind-201 on 2/20/2020.
 */

data class LogoutResponse(

    @SerializedName("status_code")
    private var statusCode: String? = null,

    @SerializedName("message")
    private var message: String? = null

)