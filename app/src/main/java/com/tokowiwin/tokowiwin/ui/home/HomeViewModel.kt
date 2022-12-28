package com.tokowiwin.tokowiwin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tokowiwin.tokowiwin.data.remote.response.ProductsResponse
import com.tokowiwin.tokowiwin.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository)  : ViewModel() {
    private lateinit var result: LiveData<ProductsResponse>

    fun setProducts(){
        result = repository.getProducts()
    }

    fun getProducts() : LiveData<ProductsResponse> = result
}