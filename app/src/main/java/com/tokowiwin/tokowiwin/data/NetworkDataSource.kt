package com.tokowiwin.tokowiwin.data

import com.tokowiwin.tokowiwin.data.remote.ApiService
import com.tokowiwin.tokowiwin.data.remote.request.*
import com.tokowiwin.tokowiwin.data.remote.response.*
import javax.inject.Inject
import javax.inject.Named

class NetworkDataSource @Inject constructor(
    @Named("resultApi") private val resultApi: ApiService,
) {
    suspend fun login(username: String, password: String, callback: LoginCallback) {
        try {
            val response = resultApi.login(LoginRequest(
                username,
                password,
            ))
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage as String, null)
        }
    }

    suspend fun register(name: String, username: String, password: String, callback: RegisterCallback) {
        try {
            val response = resultApi.register(RegisterRequest(
                name,
                username,
                password,
            ))
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage as String, null)
        }
    }

    suspend fun changePass(email: String, oldPass : String, newPass : String, callback: ChangePassCallback) {
        try {
            val response = resultApi.changePass(ChangePassRequest(
                email,
                oldPass,
                newPass,
            ))
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage as String, null)
        }
    }

    suspend fun getProducts(callback: ProductsCallback) {
        try {
            val response = resultApi.products()
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage as String, null)
        }
    }

    suspend fun addCart(userId: Int, productId: Int, qty: Int, callback: AddCartCallback) {
        try {
            val response = resultApi.addCart(AddCartRequest(
                userId, productId, qty
            ))
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage as String, null)
        }
    }

    suspend fun carts(userId: Int, callback: CartsCallback) {
        try {
            val response = resultApi.carts(userId)
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage as String, null)
        }
    }

    suspend fun deleteCart(userId: Int, productId: Int, callback: DeleteCartCallback) {
        try {
            val response = resultApi.deleteCart(userId, productId)
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage as String, null)
        }
    }

    suspend fun updateCart(userId: Int, productId: Int, qty: Int, callback: UpdateCartCallback) {
        try {
            val response = resultApi.updateCart(userId, productId, qty)
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage as String, null)
        }
    }

    suspend fun insertTransaction(userId: Int, receiverName: String, receiverPhone: String, receiverAddress: String, paymentType: String, callback: InsertTransactionCallback) {
        try {
            val response = resultApi.insertTransaction(InsertTransactionRequest(
                userId, receiverName, receiverPhone, receiverAddress, paymentType)
            )
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage as String, null)
        }
    }

    suspend fun transactions(userId: Int, callback: TransactionsCallback) {
        try {
            val response = resultApi.transactions(userId)
            response.let { result->
                callback.onResultReceived(Resource.Success(result))
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage as String, null)
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
        fun onErrorReceived(results: Resource<Error>)
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

    interface UpdateCartCallback {
        fun onResultReceived(results: Resource<UpdateCartResponse>)
    }

    interface InsertTransactionCallback {
        fun onResultReceived(results: Resource<InsertTransactionResponse>)
    }

    interface TransactionsCallback {
        fun onResultReceived(results: Resource<TransactionsResponse>)
    }
}