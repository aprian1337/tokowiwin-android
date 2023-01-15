package com.tokowiwin.tokowiwin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tokowiwin.tokowiwin.data.remote.response.AddCartResponse
import com.tokowiwin.tokowiwin.data.remote.response.ProductsResponse
import com.tokowiwin.tokowiwin.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository)  : ViewModel() {
    private lateinit var result: LiveData<ProductsResponse>
    private lateinit var resultAddCart: LiveData<AddCartResponse>

    fun setProducts(){
        result = repository.getProducts()
    }
    fun getProducts() : LiveData<ProductsResponse> = result

    fun setAddCart(userId: Int, productId: Int, qty: Int){
        resultAddCart = repository.addCart(userId, productId, qty)
    }
    fun getAddCart() : LiveData<AddCartResponse> = resultAddCart
}