package com.tafi.footballspin.network

import com.tafi.footballspin.network.request.LoginRequest
import com.tafi.footballspin.network.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET

/**
 * Created by taind-201 on 2/20/2020.
 */

interface ApiService {

    @GET("/users/{username}")
    fun getUser(@Body request: LoginRequest.ServerLoginRequest): Single<LoginResponse>

}