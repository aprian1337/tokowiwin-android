package com.tokowiwin.tokowiwin.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.tokowiwin.tokowiwin.R
import com.tokowiwin.tokowiwin.databinding.ActivityRegisterBinding
import com.tokowiwin.tokowiwin.ui.MainActivity
import com.tokowiwin.tokowiwin.ui.login.LoginActivity
import com.tokowiwin.tokowiwin.ui.login.LoginViewModel
import com.tokowiwin.tokowiwin.utils.AuthType
import com.tokowiwin.tokowiwin.utils.ToastHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.let{
            it.btnDaftar.setOnClickListener(this)
            it.txtMasuk.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txt_masuk -> {
                Intent(this, LoginActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }
            R.id.btn_daftar -> {
                val name = binding.txtName.text.toString()
                val username = binding.txtEmail.text.toString()
                val password = binding.txtPassword.text.toString()
                val repassword = binding.txtRepassword.text.toString()
                if (name=="" || username=="" || password=="" || repassword=="") {
                    ToastHelper.showToast(this, "Data harus diisi semua!")
                }else{
                    if (password != repassword) {
                        ToastHelper.showToast(this, "Password yang dimasukkan tidak sama.")
                    }else if (password.length < 6){
                        ToastHelper.showToast(this, "Password minimal memiliki 6 karakter.")
                    } else{
                        viewModel.doRegister(name, username, password)
                        viewModel.getRegister().observe(this){
                            if (it.errorMessage != "") {
                                it.errorMessage?.let { it1 -> ToastHelper.showToast(this, it1) }
                            }else if(it.data?.success == 0) {
                                it.data.message?.let { it1 -> ToastHelper.showToast(this, it1) }
                            }else{
                                it.data?.message?.let { it1 -> ToastHelper.showToast(this, it1) }
                                Intent(this@RegisterActivity, LoginActivity::class.java).apply {
                                    startActivity(this)
                                    finish()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}