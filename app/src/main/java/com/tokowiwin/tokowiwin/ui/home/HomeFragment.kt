package com.tokowiwin.tokowiwin.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tokowiwin.tokowiwin.data.local.UserPreference
import com.tokowiwin.tokowiwin.data.remote.response.ProductsDataItem
import com.tokowiwin.tokowiwin.databinding.FragmentHomeBinding
import com.tokowiwin.tokowiwin.ui.cart.CartActivity
import com.tokowiwin.tokowiwin.utils.ToastHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { HomeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userPreference = activity?.let { UserPreference(it) }
        val user =  userPreference?.getUser()
        setupRv()
        showData()
        binding.searchProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(txt: String?): Boolean {
                showData(txt!!.lowercase())
                return false
            }

            override fun onQueryTextChange(txt: String?): Boolean {
                showData(txt!!.lowercase())
                return false
            }
        })
        if (user != null) {
            binding.txtHeader.text = user.headerText
        }
        adapter.setOnItemClickCallback(object : HomeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ProductsDataItem, qty: Int) {
                if (qty == 0) {
                    ToastHelper.showToast(requireContext(), "Qty tidak boleh kosong")
                }else{
                    if (qty > data.productStock!!) {
                        ToastHelper.showToast(requireContext(), "Stock produk tidak tersedia dengan yang diminta")
                    }else{
                        val userId = user?.id?.let { Integer.parseInt(it) }
                        val productId = data.id
                        if (userId != null && productId != null) {
                            viewModel.setAddCart(userId, productId, qty)
                            viewModel.getAddCart().observe(viewLifecycleOwner) {
                                if (it.errorMessage != "") {
                                    it.errorMessage?.let { it1 -> ToastHelper.showToast(requireContext(), it1) }
                                }else if(it.data?.success == 0) {
                                    ToastHelper.showToast(requireContext(), it.data.message.toString())
                                }else{
                                    ToastHelper.showToast(requireContext(), "Berhasil menambahkan keranjang")
                                }
                            }
                        }
                    }
                }
            }
        })
        binding.imgCart.setOnClickListener{
            startActivity(Intent(activity, CartActivity::class.java))
        }
    }

    private fun setupRv() {
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProducts.setHasFixedSize(true)
    }

    private fun showData(filter: String = "") {
        viewModel.setProducts()
        viewModel.getProducts().observe(viewLifecycleOwner){
            if (it.errorMessage != "") {
                it.errorMessage?.let { it1 -> ToastHelper.showToast(requireContext(), it1) }
            }else{
                var tempData : ArrayList<ProductsDataItem> = arrayListOf()
                if (it?.data != null){
                    if (filter != "") {
                        for (item in it.data) {
                            if (item?.productName?.lowercase()?.contains(filter) == true) {
                                tempData.add(item)
                            }
                        }
                    }else{
                        tempData = it.data as ArrayList<ProductsDataItem>
                    }
                    adapter.setData(tempData)
                }
            }
        }
    }
}