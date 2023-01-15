package com.tokowiwin.tokowiwin.ui.changepass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.tokowiwin.tokowiwin.R
import com.tokowiwin.tokowiwin.data.local.UserPreference
import com.tokowiwin.tokowiwin.databinding.ActivityChangePassBinding
import com.tokowiwin.tokowiwin.databinding.ActivityRegisterBinding
import com.tokowiwin.tokowiwin.ui.MainActivity
import com.tokowiwin.tokowiwin.ui.login.LoginActivity
import com.tokowiwin.tokowiwin.ui.register.RegisterViewModel
import com.tokowiwin.tokowiwin.utils.ToastHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePassActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityChangePassBinding
    private val viewModel: ChangePassViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChangePassBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnChangePass.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_change_pass -> {
                val newPass = binding.txtNewPassword.text.toString()
                val oldPass = binding.txtOldPassword.text.toString()
                val userPreference = UserPreference(this)
                val user =  userPreference.getUser()

                viewModel.doChangePass(user.email!!, oldPass, newPass)
                viewModel.getChangePass().observe(this){
                    if (it.errorMessage != "") {
                        it.errorMessage?.let { it1 -> ToastHelper.showToast(this, it1) }
                    }else if(it.data?.success == 0) {
                        it.data.message?.let { it1 -> ToastHelper.showToast(this, it1) }
                    }else{
                        it.data?.message?.let { it1 -> ToastHelper.showToast(this, it1) }
                        Intent(this@ChangePassActivity, MainActivity::class.java).apply {
                            startActivity(this)
                            finish()
                        }
                    }
                }
            }
        }
    }
}