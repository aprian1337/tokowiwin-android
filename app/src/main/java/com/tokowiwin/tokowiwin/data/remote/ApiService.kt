package com.tokowiwin.tokowiwin.data.remote

import com.tokowiwin.tokowiwin.data.remote.request.ChangePassRequest
import com.tokowiwin.tokowiwin.data.remote.request.LoginRequest
import com.tokowiwin.tokowiwin.data.remote.request.RegisterRequest
import com.tokowiwin.tokowiwin.data.remote.response.ChangePassResponse
import com.tokowiwin.tokowiwin.data.remote.response.LoginResponse
import com.tokowiwin.tokowiwin.data.remote.response.ProductsResponse
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

    @POST("/changepass")
    suspend fun changePass(
        @Body req: ChangePassRequest
    ) : ChangePassResponse

    @GET("/products")
    suspend fun products() : ProductsResponse
}