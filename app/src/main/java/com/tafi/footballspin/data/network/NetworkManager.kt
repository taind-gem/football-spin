package com.tafi.footballspin.data.network

import com.tafi.footballspin.data.network.request.LoginRequest
import com.tafi.footballspin.data.network.response.LoginResponse
import com.tafi.footballspin.data.network.response.LogoutResponse
import io.reactivex.Single

/**
 * Created by taind-201 on 2/12/2020.
 */

interface NetworkManager {

    fun doServerLoginApiCall(request: LoginRequest.ServerLoginRequest): Single<LoginResponse>

    fun doGoogleLoginApiCall(request: LoginRequest.GoogleLoginRequest): Single<LoginResponse>

    fun doFacebookLoginApiCall(request: LoginRequest.FacebookLoginRequest): Single<LoginResponse>

}