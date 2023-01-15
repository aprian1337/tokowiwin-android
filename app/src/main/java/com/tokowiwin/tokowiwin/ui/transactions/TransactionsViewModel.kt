package com.tokowiwin.tokowiwin.ui.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tokowiwin.tokowiwin.data.remote.response.RegisterResponse
import com.tokowiwin.tokowiwin.data.remote.response.TransactionsResponse
import com.tokowiwin.tokowiwin.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(private val repository: Repository)  : ViewModel() {
    private lateinit var result: LiveData<TransactionsResponse>

    fun setTransactions(userId: Int){
        result = repository.transactions(userId)
    }

    fun getTransactions() : LiveData<TransactionsResponse> = result
}