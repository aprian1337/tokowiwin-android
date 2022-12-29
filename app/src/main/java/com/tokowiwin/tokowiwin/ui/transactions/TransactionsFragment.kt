package com.tokowiwin.tokowiwin.ui.transactions

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tokowiwin.tokowiwin.R
import com.tokowiwin.tokowiwin.data.local.UserPreference
import com.tokowiwin.tokowiwin.data.remote.response.TransactionsDataItem
import com.tokowiwin.tokowiwin.databinding.FragmentTransactionsBinding
import com.tokowiwin.tokowiwin.ui.cart.CartActivity
import com.tokowiwin.tokowiwin.utils.ToastHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionsFragment : Fragment(), View.OnClickListener {
    private var _binding : FragmentTransactionsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TransactionsViewModel by viewModels()
    private val adapter by lazy { TransactionsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userPreference = activity?.let { UserPreference(it) }
        val user =  userPreference?.getUser()
        val userId = Integer.parseInt(user?.id.toString())
        setupRv()
        viewModel.setTransactions(userId)
        viewModel.getTransactions().observe(viewLifecycleOwner){
            if (it.errorMessage != "") {
                it.errorMessage?.let { it1 -> ToastHelper.showToast(requireContext(), it1) }
            }else{
                adapter.setData(it?.data?.data as List<TransactionsDataItem>)
            }
        }
        binding.imgCart.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.img_cart -> {
                startActivity(Intent(activity, CartActivity::class.java))
            }
        }
    }

    private fun setupRv() {
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProducts.setHasFixedSize(true)
    }
}