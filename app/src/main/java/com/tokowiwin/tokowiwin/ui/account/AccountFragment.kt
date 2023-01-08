package com.tokowiwin.tokowiwin.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tokowiwin.tokowiwin.data.local.UserPreference
import com.tokowiwin.tokowiwin.data.remote.response.LoginUser
import com.tokowiwin.tokowiwin.databinding.FragmentAccountBinding
import com.tokowiwin.tokowiwin.ui.cart.CartActivity
import com.tokowiwin.tokowiwin.ui.changepass.ChangePassActivity
import com.tokowiwin.tokowiwin.ui.login.LoginActivity
import com.tokowiwin.tokowiwin.utils.AuthType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {
    private var _binding : FragmentAccountBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userPreference = activity?.let { UserPreference(it) }
        val user =  userPreference?.getUser()
        super.onViewCreated(view, savedInstanceState)
        binding.let { b ->
            "Hi, ${user?.fullname}!".also { b.textView.text = it }
            b.btnChangePass.setOnClickListener{
                startActivity(Intent(activity, ChangePassActivity::class.java))
            }
            b.btnKeluar.setOnClickListener{
                Intent(activity, LoginActivity::class.java).let {
                    userPreference?.setUser(LoginUser(), AuthType.LOGOUT)
                    startActivity(it)
                    activity?.finish()
                }
            }
            b.imgCart.setOnClickListener{
                startActivity(Intent(activity, CartActivity::class.java))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}