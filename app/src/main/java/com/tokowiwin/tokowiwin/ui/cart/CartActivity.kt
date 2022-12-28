package com.tokowiwin.tokowiwin.ui.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tokowiwin.tokowiwin.data.local.UserPreference
import com.tokowiwin.tokowiwin.data.remote.response.CartsDataItem
import com.tokowiwin.tokowiwin.databinding.ActivityCartBinding
import com.tokowiwin.tokowiwin.utils.Method
import com.tokowiwin.tokowiwin.utils.ToastHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private val viewModel: CartViewModel by viewModels()
    private val adapter by lazy { CartAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userPreference = UserPreference(this)
        setupRv()
        val user =  userPreference.getUser()
        val userId : Int = Integer.parseInt(user.id.toString())
        showData(userId)
        adapter.setOnItemClickCallback(object: CartAdapter.OnItemClickCallback {
            override fun onItemClicked(data: CartsDataItem, type: Method) {
                when(type) {
                   Method.DELETE -> {
                       viewModel.setDeleteCart(userId, data.id!!)
                       viewModel.getDeleteCart().observe(this@CartActivity){
                           if (it.errorMessage != "") {
                               it.errorMessage?.let { it1 -> ToastHelper.showToast(this@CartActivity, it1) }
                           }else if(it.data?.success == 0) {
                               it.data.message?.let { it1 -> ToastHelper.showToast(this@CartActivity, it1) }
                           }else{
                               it.data?.message?.let { it1 -> ToastHelper.showToast(this@CartActivity, it1) }
                               showData(userId)
                           }
                       }
                   }
                    else -> {}
                }
            }
        })
    }

    private fun showData(userId: Int) {
        viewModel.setCarts(userId)
        viewModel.getCarts().observe(this){
            adapter.setData(it?.data?.data!! as List<CartsDataItem>)
            binding.txtTotalTagihan.text = it.data.totalTagihan
        }
    }

    private fun setupRv() {
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.setHasFixedSize(true)
    }
}