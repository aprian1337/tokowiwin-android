package com.tokowiwin.tokowiwin.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tokowiwin.tokowiwin.data.remote.response.*
import com.tokowiwin.tokowiwin.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: Repository)  : ViewModel() {
    private lateinit var result: LiveData<CartsResponse>
    private lateinit var resultDelete: LiveData<DeleteCartResponse>
    private lateinit var resultUpdate: LiveData<UpdateCartResponse>
    private lateinit var resultInsertTransaction: LiveData<InsertTransactionResponse>

    fun setCarts(userId: Int){
        result = repository.carts(userId)
    }
    fun getCarts() : LiveData<CartsResponse> = result

    fun setDeleteCart(userId: Int, productId: Int){
        resultDelete = repository.deleteCart(userId, productId)
    }
    fun getDeleteCart() : LiveData<DeleteCartResponse> = resultDelete

    fun setUpdateCart(userId: Int, productId: Int, qty: Int){
        resultUpdate = repository.updateCart(userId, productId, qty)
    }
    fun getUpdateCart() : LiveData<UpdateCartResponse> = resultUpdate

    fun setInsertTransaction(userId: Int, receiverName: String, receiverPhone: String, receiverAddress: String, paymentType: String){
        resultInsertTransaction = repository.insertTransaction(userId, receiverName, receiverPhone, receiverAddress, paymentType)
    }
    fun getInsertTransaction() : LiveData<InsertTransactionResponse> = resultInsertTransaction
}