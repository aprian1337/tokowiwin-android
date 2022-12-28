package com.tokowiwin.tokowiwin.domain.repository

import androidx.lifecycle.LiveData
import com.tokowiwin.tokowiwin.data.remote.response.ChangePassResponse
import com.tokowiwin.tokowiwin.data.remote.response.LoginResponse
import com.tokowiwin.tokowiwin.data.remote.response.ProductsResponse
import com.tokowiwin.tokowiwin.data.remote.response.RegisterResponse

interface Repository {
    fun login(username: String, password: String) : LiveData<LoginResponse>
    fun register(name: String, username: String, password: String) : LiveData<RegisterResponse>
    fun changePass(email: String, oldPass: String, newPass: String) : LiveData<ChangePassResponse>
    fun getProducts() : LiveData<ProductsResponse>
}