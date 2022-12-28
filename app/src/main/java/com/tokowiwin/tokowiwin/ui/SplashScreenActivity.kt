package com.tokowiwin.tokowiwin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import com.tokowiwin.tokowiwin.R
import com.tokowiwin.tokowiwin.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

class SplashScreenActivity : AppCompatActivity() {
    private val splashTime: Long = 4000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                Intent(this@SplashScreenActivity, LoginActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }, splashTime
        )
    }
}