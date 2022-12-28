package com.tokowiwin.tokowiwin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tokowiwin.tokowiwin.R
import com.tokowiwin.tokowiwin.databinding.ActivityMainBinding
import com.tokowiwin.tokowiwin.ui.account.AccountFragment
import com.tokowiwin.tokowiwin.ui.home.HomeFragment
import com.tokowiwin.tokowiwin.ui.transactions.TransactionsFragment
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home->{
                    addFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_history->{
                    addFragment(TransactionsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_account->{
                    addFragment(AccountFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun addFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}