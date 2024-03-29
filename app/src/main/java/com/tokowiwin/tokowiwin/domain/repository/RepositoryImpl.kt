package com.tokowiwin.tokowiwin.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tokowiwin.tokowiwin.data.NetworkDataSource
import com.tokowiwin.tokowiwin.data.Resource
import com.tokowiwin.tokowiwin.data.remote.response.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkDataSource : NetworkDataSource,
) : Repository {
    @OptIn(DelicateCoroutinesApi::class)
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

    @OptIn(DelicateCoroutinesApi::class)
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

    @OptIn(DelicateCoroutinesApi::class)
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

    @OptIn(DelicateCoroutinesApi::class)
    override fun getProducts(): LiveData<ProductsResponse> {
        val result = MutableLiveData<ProductsResponse>()
        GlobalScope.launch {
            networkDataSource.getProducts(object : NetworkDataSource.ProductsCallback {
                override fun onResultReceived(results: Resource<ProductsResponse>) {
                    result.postValue(results.data!!)
                }

                override fun onErrorReceived(results: Resource<Error>) {
                }
            })
        }
        return result
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun addCart(userId: Int, productId: Int, qty: Int): LiveData<AddCartResponse> {
        val result = MutableLiveData<AddCartResponse>()
        GlobalScope.launch {
            networkDataSource.addCart(userId, productId, qty, object : NetworkDataSource.AddCartCallback {
                override fun onResultReceived(results: Resource<AddCartResponse>) {
                    result.postValue(results.data!!)
                }
            })
        }
        return result
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun carts(userId: Int): LiveData<CartsResponse> {
        val result = MutableLiveData<CartsResponse>()
        GlobalScope.launch {
            networkDataSource.carts(userId, object : NetworkDataSource.CartsCallback {
                override fun onResultReceived(results: Resource<CartsResponse>) {
                    result.postValue(results.data!!)
                }
            })
        }
        return result
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun deleteCart(userId: Int, productId: Int): LiveData<DeleteCartResponse> {
        val result = MutableLiveData<DeleteCartResponse>()
        GlobalScope.launch {
            networkDataSource.deleteCart(userId, productId, object : NetworkDataSource.DeleteCartCallback {
                override fun onResultReceived(results: Resource<DeleteCartResponse>) {
                    result.postValue(results.data!!)
                }
            })
        }
        return result
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun updateCart(userId: Int, productId: Int, qty: Int): LiveData<UpdateCartResponse> {
        val result = MutableLiveData<UpdateCartResponse>()
        GlobalScope.launch {
            networkDataSource.updateCart(userId, productId, qty, object : NetworkDataSource.UpdateCartCallback {
                override fun onResultReceived(results: Resource<UpdateCartResponse>) {
                    result.postValue(results.data!!)
                }
            })
        }
        return result    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun insertTransaction(
        id: Long,
        userId: Int,
        receiverName: String,
        receiverPhone: String,
        receiverAddress: String,
        paymentType: String
    ): LiveData<InsertTransactionResponse> {
        val result = MutableLiveData<InsertTransactionResponse>()
        GlobalScope.launch {
            networkDataSource.insertTransaction(id, userId, receiverName, receiverPhone, receiverAddress, paymentType, object : NetworkDataSource.InsertTransactionCallback {
                override fun onResultReceived(results: Resource<InsertTransactionResponse>) {
                    result.postValue(results.data!!)
                }
            })
        }
        return result
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun transactions(userId: Int): LiveData<TransactionsResponse> {
        val result = MutableLiveData<TransactionsResponse>()
        GlobalScope.launch {
            networkDataSource.transactions(userId, object : NetworkDataSource.TransactionsCallback {
                override fun onResultReceived(results: Resource<TransactionsResponse>) {
                    result.postValue(results.data!!)
                }
            })
        }
        return result
    }
}