package com.tokowiwin.tokowiwin.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tokowiwin.tokowiwin.data.remote.response.LoginResponse
import com.tokowiwin.tokowiwin.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository)  : ViewModel() {
    private lateinit var result: LiveData<LoginResponse>

    fun doLogin(username : String, password : String){
        result = repository.login(username, password)
    }

    fun getUser() : LiveData<LoginResponse> = result
}