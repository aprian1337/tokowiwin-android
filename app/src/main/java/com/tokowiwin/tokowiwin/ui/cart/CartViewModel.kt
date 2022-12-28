package com.tokowiwin.tokowiwin.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tokowiwin.tokowiwin.data.remote.response.AddCartResponse
import com.tokowiwin.tokowiwin.data.remote.response.CartsResponse
import com.tokowiwin.tokowiwin.data.remote.response.DeleteCartResponse
import com.tokowiwin.tokowiwin.data.remote.response.ProductsResponse
import com.tokowiwin.tokowiwin.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: Repository)  : ViewModel() {
    private lateinit var result: LiveData<CartsResponse>
    private lateinit var resultDelete: LiveData<DeleteCartResponse>

    fun setCarts(userId: Int){
        result = repository.carts(userId)
    }
    fun getCarts() : LiveData<CartsResponse> = result
    fun setDeleteCart(userId: Int, productId: Int){
        resultDelete = repository.deleteCart(userId, productId)
    }
    fun getDeleteCart() : LiveData<DeleteCartResponse> = resultDelete

}