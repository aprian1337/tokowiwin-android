package com.tokowiwin.tokowiwin.ui.cart

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tokowiwin.tokowiwin.R
import com.tokowiwin.tokowiwin.data.local.UserPreference
import com.tokowiwin.tokowiwin.data.remote.response.CartsDataItem
import com.tokowiwin.tokowiwin.databinding.ActivityCartBinding
import com.tokowiwin.tokowiwin.ui.MainActivity
import com.tokowiwin.tokowiwin.utils.Method
import com.tokowiwin.tokowiwin.utils.ToastHelper
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private lateinit var binding: ActivityCartBinding
    private val viewModel: CartViewModel by viewModels()
    private val adapter by lazy { CartAdapter() }
    private lateinit var dialog : Dialog
    private lateinit var btnKembali :  Button
    private lateinit var btnBayar :  Button
    private lateinit var receiverName :  TextView
    private lateinit var receiverPhone :  TextView
    private lateinit var receiverAddress :  TextView
    private lateinit var radioPaymentType : RadioGroup
    private var paymentType = "COD"
    private var userId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialog = Dialog(this, R.style.CustomAlertDialog)
        binding.btnPembayaran.visibility = View.GONE
        setupRv()
        val userPreference = UserPreference(this)
        val user =  userPreference.getUser()
        userId = Integer.parseInt(user.id.toString())
        showData(userId)
        adapter.setOnItemClickCallback(object: CartAdapter.OnItemClickCallback {
            override fun onItemClicked(data: CartsDataItem, qty: Int, type: Method) {
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
                               this@CartActivity.recreate()
                           }
                       }
                   }
                    Method.UPDATE -> {
                        viewModel.setUpdateCart(userId,  data.id!!, qty)
                        viewModel.getUpdateCart().observe(this@CartActivity){
                            if (it.errorMessage != "") {
                                it.errorMessage?.let { it1 -> ToastHelper.showToast(this@CartActivity, it1) }
                            }else if(it.data?.success == 0) {
                                it.data.message?.let { it1 -> ToastHelper.showToast(this@CartActivity, it1) }
                            }else{
                                ToastHelper.showToast(this@CartActivity, "Update keranjang..")
                                this@CartActivity.recreate()
                            }
                        }
                    }
                    else -> {}
                }
            }
        })
        binding.btnPembayaran.setOnClickListener(this)
    }

    private fun showData(userId: Int) {
        viewModel.setCarts(userId)
        viewModel.getCarts().observe(this){
            adapter.setData(it?.data?.data!! as List<CartsDataItem>)
            if (it.data.data.isEmpty()) {
                binding.txtTotalTagihan.text = "Keranjang Kosong"
            }else{
                binding.txtTotalTagihan.text = it.data.totalTagihan
                binding.btnPembayaran.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRv() {
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.setHasFixedSize(true)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_pembayaran -> {
                showPopup()
            }
            R.id.btn_kembali -> {
                dialog.dismiss()
            }
            R.id.btn_bayar -> {
                viewModel.setInsertTransaction(userId, receiverName.text.toString(), receiverPhone.text.toString(), receiverAddress.text.toString(), paymentType)
                viewModel.getInsertTransaction().observe(this){
                    if (it.errorMessage != "") {
                        it.errorMessage?.let { it1 -> ToastHelper.showToast(this, it1) }
                    }else if(it.data?.success == 0) {
                        it.data.message?.let { it1 -> ToastHelper.showToast(this, it1) }
                    }else{
                        ToastHelper.showToast(this, "Transaksi Berhasil")
                        startActivity(Intent(this@CartActivity, MainActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }

    private fun showPopup() {
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.80).toInt()
        dialog.setContentView(R.layout.popup_payment)
        dialog.window?.setLayout(width, height)
        dialog.show()
        btnKembali = dialog.findViewById(R.id.btn_kembali) as Button
        btnBayar = dialog.findViewById(R.id.btn_bayar) as Button
        radioPaymentType = dialog.findViewById(R.id.radio_payment_type) as RadioGroup
        receiverName = dialog.findViewById(R.id.txt_receiver_name) as TextView
        receiverPhone = dialog.findViewById(R.id.txt_receiver_phone) as TextView
        receiverAddress = dialog.findViewById(R.id.txt_receiver_address) as TextView
        btnKembali.setOnClickListener(this)
        btnBayar.setOnClickListener(this)
        radioPaymentType.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(r: RadioGroup?, v: Int) {
        when(v) {
            R.id.radio_button_1 -> {
                paymentType = "COD"
                ToastHelper.showToast(this, "COD")
                //COD
            }
            R.id.radio_button_2 -> {
                paymentType = "Manual Transfer"
                ToastHelper.showToast(this, "Manual")
                //Manual Transfer
            }
        }
    }
}