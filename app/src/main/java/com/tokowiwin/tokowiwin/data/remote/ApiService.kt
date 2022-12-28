package com.tokowiwin.tokowiwin.data.remote

import com.tokowiwin.tokowiwin.data.remote.request.LoginRequest
import com.tokowiwin.tokowiwin.data.remote.request.RegisterRequest
import com.tokowiwin.tokowiwin.data.remote.response.LoginResponse
import com.tokowiwin.tokowiwin.data.remote.response.RegisterResponse
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("/login")
    suspend fun login(
        @Body req: LoginRequest
    ) : LoginResponse

    @POST("/register")
    suspend fun register(
        @Body req: RegisterRequest
    ) : RegisterResponse
}