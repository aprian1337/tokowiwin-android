package com.tokowiwin.tokowiwin.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.tokowiwin.tokowiwin.R
import com.tokowiwin.tokowiwin.data.local.UserPreference
import com.tokowiwin.tokowiwin.databinding.ActivityLoginBinding
import com.tokowiwin.tokowiwin.ui.MainActivity
import com.tokowiwin.tokowiwin.ui.register.RegisterActivity
import com.tokowiwin.tokowiwin.utils.AuthType
import com.tokowiwin.tokowiwin.utils.ToastHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userPreference: UserPreference
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userPreference = UserPreference(this)
        val user = userPreference.getUser()
        Log.d("TAGUSER", user.toString())
        if (user.id != "") {
            Intent(this@LoginActivity, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }
        binding.let{
            it.btnMasuk.setOnClickListener(this)
            it.txtDaftar.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txt_daftar -> {
                Intent(this, RegisterActivity::class.java).apply {
                    startActivity(this)
                }
            }
            R.id.btn_masuk -> {
                signIn()
            }
        }
    }

    private fun signIn() {
        val username = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()
        viewModel.doLogin(username, password)
        viewModel.getUser().observe(this) {
            if (it.errorMessage != "") {
                it.errorMessage?.let { it1 -> ToastHelper.showToast(this, it1) }
            }else{
                it.data?.message?.let { it1 -> ToastHelper.showToast(this, it1) }
            }

            if (it.data?.user != null) {
                userPreference.setUser(it.data.user, AuthType.LOGIN, it.data.headerText!!)
                Intent(this@LoginActivity, MainActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }
        }
    }
}