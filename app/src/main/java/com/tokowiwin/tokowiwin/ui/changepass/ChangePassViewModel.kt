package com.tokowiwin.tokowiwin.ui.changepass

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tokowiwin.tokowiwin.data.remote.response.ChangePassResponse
import com.tokowiwin.tokowiwin.data.remote.response.RegisterResponse
import com.tokowiwin.tokowiwin.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePassViewModel @Inject constructor(private val repository: Repository)  : ViewModel() {
    private lateinit var result: LiveData<ChangePassResponse>

    fun doChangePass(email: String, oldPass : String, newPass : String){
        result = repository.changePass(email, oldPass, newPass)
    }

    fun getChangePass() : LiveData<ChangePassResponse> = result
}