package com.tokowiwin.tokowiwin.ui.changepass

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tokowiwin.tokowiwin.R
import com.tokowiwin.tokowiwin.data.local.UserPreference
import com.tokowiwin.tokowiwin.data.remote.response.LoginUser
import com.tokowiwin.tokowiwin.databinding.FragmentAccountBinding
import com.tokowiwin.tokowiwin.databinding.FragmentChangePassBinding
import com.tokowiwin.tokowiwin.ui.login.LoginActivity
import com.tokowiwin.tokowiwin.utils.AuthType

class ChangePassFragment : Fragment() {
    private var _binding : FragmentChangePassBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangePassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userPreference = activity?.let { UserPreference(it) }
        val user =  userPreference?.getUser()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}