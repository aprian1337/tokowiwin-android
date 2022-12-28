package com.tokowiwin.tokowiwin.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tokowiwin.tokowiwin.data.remote.response.RegisterResponse
import com.tokowiwin.tokowiwin.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: Repository)  : ViewModel() {
    private lateinit var result: LiveData<RegisterResponse>

    fun doRegister(name: String, username : String, password : String){
        result = repository.register(name, username, password)
    }

    fun getRegister() : LiveData<RegisterResponse> = result
}