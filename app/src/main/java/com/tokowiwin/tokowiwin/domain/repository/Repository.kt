package com.tokowiwin.tokowiwin.domain.repository

import androidx.lifecycle.LiveData
import com.tokowiwin.tokowiwin.data.remote.response.*

interface Repository {
    fun login(username: String, password: String) : LiveData<LoginResponse>
    fun register(name: String, username: String, password: String) : LiveData<RegisterResponse>
    fun changePass(email: String, oldPass: String, newPass: String) : LiveData<ChangePassResponse>
    fun getProducts() : LiveData<ProductsResponse>
    fun addCart(userId: Int, productId: Int, qty: Int) : LiveData<AddCartResponse>
    fun carts(userId: Int) : LiveData<CartsResponse>
    fun deleteCart(userId: Int, productId: Int) : LiveData<DeleteCartResponse>
}