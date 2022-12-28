package com.tokowiwin.tokowiwin.data

import com.tokowiwin.tokowiwin.data.remote.ApiService
import com.tokowiwin.tokowiwin.data.remote.request.*
import com.tokowiwin.tokowiwin.data.remote.response.*
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Named

class NetworkDataSource @Inject constructor(
    @Named("resultApi") private val resultApi: ApiService,
) {
    suspend fun login(username: String, password: String, callback: LoginCallback) {
        val response = resultApi.login(LoginRequest(
            username,
            password,
        ))
        try {
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: HttpException) {
            Resource.Error(e.message(), null)
        }
    }

    suspend fun register(name: String, username: String, password: String, callback: RegisterCallback) {
        val response = resultApi.register(RegisterRequest(
            name,
            username,
            password,
        ))
        try {
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: HttpException) {
            Resource.Error(e.message(), null)
        }
    }

    suspend fun changePass(email: String, oldPass : String, newPass : String, callback: ChangePassCallback) {
        val response = resultApi.changePass(ChangePassRequest(
            email,
            oldPass,
            newPass,
        ))
        try {
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: HttpException) {
            Resource.Error(e.message(), null)
        }
    }

    suspend fun getProducts(callback: ProductsCallback) {
        val response = resultApi.products()
        try {
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: HttpException) {
            Resource.Error(e.message(), null)
        }
    }

    suspend fun addCart(userId: Int, productId: Int, qty: Int, callback: AddCartCallback) {
        val response = resultApi.addCart(AddCartRequest(
            userId, productId, qty
        ))
        try {
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: HttpException) {
            Resource.Error(e.message(), null)
        }
    }

    suspend fun carts(userId: Int, callback: CartsCallback) {
        val response = resultApi.carts(userId)
        try {
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: HttpException) {
            Resource.Error(e.message(), null)
        }
    }

    suspend fun deleteCart(userId: Int, productId: Int, callback: DeleteCartCallback) {
        val response = resultApi.deleteCart(userId, productId)
        try {
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: HttpException) {
            Resource.Error(e.message(), null)
        }
    }

    interface LoginCallback {
        fun onResultReceived(results: Resource<LoginResponse>)
    }

    interface RegisterCallback {
        fun onResultReceived(results: Resource<RegisterResponse>)
    }

    interface ChangePassCallback {
        fun onResultReceived(results: Resource<ChangePassResponse>)
    }

    interface ProductsCallback {
        fun onResultReceived(results: Resource<ProductsResponse>)
    }

    interface AddCartCallback {
        fun onResultReceived(results: Resource<AddCartResponse>)
    }

    interface CartsCallback {
        fun onResultReceived(results: Resource<CartsResponse>)
    }

    interface DeleteCartCallback {
        fun onResultReceived(results: Resource<DeleteCartResponse>)
    }
}