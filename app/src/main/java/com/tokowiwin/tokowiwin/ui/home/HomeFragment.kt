package com.tokowiwin.tokowiwin.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tokowiwin.tokowiwin.data.remote.response.ProductsDataItem
import com.tokowiwin.tokowiwin.databinding.FragmentHomeBinding
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
    }

    private fun setupRv() {
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProducts.setHasFixedSize(true)
    }

    private fun showData(filter: String = "") {
        Log.d("TAGFILTER", filter)
        viewModel.setProducts()
        viewModel.getProducts().observe(viewLifecycleOwner){
            if (it.errorMessage != "") {
                it.errorMessage?.let { it1 -> ToastHelper.showToast(requireContext(), it1) }
            }else{
                var tempData : ArrayList<ProductsDataItem> = arrayListOf()
                Log.d("TAGFILTER", filter)
                if (it?.data != null){
                    if (filter != "") {
                        for (item in it.data) {
                            Log.d("TAGITEM", item.toString())
                            Log.d("TAGIF", item?.productName!!.lowercase())
                            if (item.productName.lowercase().contains(filter)) {
                                Log.d("KETEMU", item.productName.lowercase())
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