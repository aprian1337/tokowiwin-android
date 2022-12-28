package com.tokowiwin.tokowiwin.data

import com.tokowiwin.tokowiwin.data.remote.ApiService
import com.tokowiwin.tokowiwin.data.remote.request.LoginRequest
import com.tokowiwin.tokowiwin.data.remote.request.RegisterRequest
import com.tokowiwin.tokowiwin.data.remote.response.LoginResponse
import com.tokowiwin.tokowiwin.data.remote.response.RegisterResponse
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

    interface LoginCallback {
        fun onResultReceived(results: Resource<LoginResponse>)
    }

    interface RegisterCallback {
        fun onResultReceived(results: Resource<RegisterResponse>)
    }
}