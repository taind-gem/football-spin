package com.tafi.footballspin.data.network

import com.tafi.footballspin.data.network.request.LoginRequest
import com.tafi.footballspin.data.network.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by taind-201 on 2/20/2020.
 */

interface ApiService {

    @POST("/users")
    fun getUser(@Body request: LoginRequest.ServerLoginRequest): Single<LoginResponse>

}