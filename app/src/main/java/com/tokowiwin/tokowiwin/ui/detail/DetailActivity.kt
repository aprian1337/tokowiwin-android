package com.tokowiwin.tokowiwin.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tokowiwin.tokowiwin.data.remote.response.TransactionProductsItem
import com.tokowiwin.tokowiwin.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private val adapter by lazy { DetailAdapter() }

    companion object {
        const val EXTRA_DETAIL_PRODUCTS = "extra_detail_products"
        const val EXTRA_DETAIL_PAYMENT_TYPE = "extra_detail_payment_type"
        const val EXTRA_DETAIL_ADDRESS = "extra_detail_address"
        const val EXTRA_DETAIL_NAME = "extra_detail_name"
        const val EXTRA_DETAIL_PHONE = "extra_detail_phone"
        const val EXTRA_DETAIL_BILL = "extra_detail_bill"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val detailPaymentType = intent.getStringExtra(EXTRA_DETAIL_PAYMENT_TYPE)
        val detailAddress = intent.getStringExtra(EXTRA_DETAIL_ADDRESS)
        val detailName = intent.getStringExtra(EXTRA_DETAIL_NAME)
        val detailPhone = intent.getStringExtra(EXTRA_DETAIL_PHONE)
        val detailBill = intent.getStringExtra(EXTRA_DETAIL_BILL)
        val products = intent.getParcelableArrayListExtra<TransactionProductsItem>(
            EXTRA_DETAIL_PRODUCTS)
        binding.let{
            it.txtNamaPenerima.text = detailName
            it.txtNomorHp.text = detailPhone
            it.txtAlamatPengiriman.text = detailAddress
            it.txtJenisPembayaran.text = detailPaymentType
            it.txtTotalTagihan.text = detailBill
        }
        setupRv()
        products?.let { adapter.setData(it) }
    }

    private fun setupRv() {
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.setHasFixedSize(true)
    }
}