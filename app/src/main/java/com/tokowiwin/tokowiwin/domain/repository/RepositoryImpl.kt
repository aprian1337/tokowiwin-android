package com.tokowiwin.tokowiwin.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tokowiwin.tokowiwin.data.NetworkDataSource
import com.tokowiwin.tokowiwin.data.Resource
import com.tokowiwin.tokowiwin.data.remote.response.ChangePassResponse
import com.tokowiwin.tokowiwin.data.remote.response.LoginResponse
import com.tokowiwin.tokowiwin.data.remote.response.ProductsResponse
import com.tokowiwin.tokowiwin.data.remote.response.RegisterResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkDataSource : NetworkDataSource,
) : Repository {
    override fun login(username: String, password: String): LiveData<LoginResponse> {
        val result = MutableLiveData<LoginResponse>()
        GlobalScope.launch {
            networkDataSource.login(username, password, object : NetworkDataSource.LoginCallback {
                override fun onResultReceived(results: Resource<LoginResponse>) {
                    result.postValue(results.data!!)
                }
            })
        }
        return result
    }

    override fun register(
        name: String,
        username: String,
        password: String
    ): LiveData<RegisterResponse> {
        val result = MutableLiveData<RegisterResponse>()
        GlobalScope.launch {
            networkDataSource.register(name, username, password, object : NetworkDataSource.RegisterCallback {
                override fun onResultReceived(results: Resource<RegisterResponse>) {
                    result.postValue(results.data!!)
                }
            })
        }
        return result
    }

    override fun changePass(
        email: String,
        oldPass: String,
        newPass: String
    ): LiveData<ChangePassResponse> {
        val result = MutableLiveData<ChangePassResponse>()
        GlobalScope.launch {
            networkDataSource.changePass(email, oldPass, newPass, object : NetworkDataSource.ChangePassCallback {
                override fun onResultReceived(results: Resource<ChangePassResponse>) {
                    result.postValue(results.data!!)
                }
            })
        }
        return result
    }

    override fun getProducts(): LiveData<ProductsResponse> {
        val result = MutableLiveData<ProductsResponse>()
        GlobalScope.launch {
            networkDataSource.getProducts(object : NetworkDataSource.ProductsCallback {
                override fun onResultReceived(results: Resource<ProductsResponse>) {
                    result.postValue(results.data!!)
                }
            })
        }
        return result    }
}