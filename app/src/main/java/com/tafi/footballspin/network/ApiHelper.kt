package com.tafi.footballspin.network

import com.tafi.footballspin.network.request.LoginRequest
import com.tafi.footballspin.network.response.LoginResponse
import com.tafi.footballspin.network.response.LogoutResponse
import io.reactivex.Single

/**
 * Created by taind-201 on 2/12/2020.
 */

interface ApiHelper {

    fun doServerLoginApiCall(request: LoginRequest.ServerLoginRequest): Single<LoginResponse>

    fun doGoogleLoginApiCall(request: LoginRequest.GoogleLoginRequest): Single<LoginResponse>

    fun doFacebookLoginApiCall(request: LoginRequest.FacebookLoginRequest): Single<LoginResponse>

    fun doLogoutApiCall(): Single<LogoutResponse>

}