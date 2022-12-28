package com.tokowiwin.tokowiwin.data.remote

import com.tokowiwin.tokowiwin.data.NetworkDataSource
import com.tokowiwin.tokowiwin.data.remote.request.*
import com.tokowiwin.tokowiwin.data.remote.response.*
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

    @POST("/carts")
    suspend fun addCart(
        @Body req: AddCartRequest
    ) : AddCartResponse

    @GET("/carts")
    suspend fun carts(
        @Query("id") id : Int
    ) : CartsResponse

    @DELETE("/carts")
    suspend fun deleteCart(
        @Query("user_id") userId: Int, @Query("product_id") productId: Int
    ) : DeleteCartResponse
}