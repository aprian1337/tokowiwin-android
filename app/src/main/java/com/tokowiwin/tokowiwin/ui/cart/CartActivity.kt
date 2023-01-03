package com.tokowiwin.tokowiwin.ui.cart

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.midtrans.sdk.corekit.core.MidtransSDK
import com.midtrans.sdk.corekit.core.TransactionRequest
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme
import com.midtrans.sdk.corekit.models.BillingAddress
import com.midtrans.sdk.corekit.models.CustomerDetails
import com.midtrans.sdk.corekit.models.ShippingAddress
import com.midtrans.sdk.corekit.models.snap.Authentication
import com.midtrans.sdk.corekit.models.snap.CreditCard
import com.midtrans.sdk.corekit.models.snap.TransactionResult
import com.midtrans.sdk.uikit.SdkUIFlowBuilder
import com.tokowiwin.tokowiwin.R
import com.tokowiwin.tokowiwin.data.local.UserPreference
import com.tokowiwin.tokowiwin.data.remote.response.CartsDataItem
import com.tokowiwin.tokowiwin.data.remote.response.CartsResponse
import com.tokowiwin.tokowiwin.databinding.ActivityCartBinding
import com.tokowiwin.tokowiwin.ui.MainActivity
import com.tokowiwin.tokowiwin.utils.Constants
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
    private var email = ""
    private var fullname = ""
    private var orderId : Long = 0
    private lateinit var cartResp : CartsResponse

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
        email = user.email!!
        fullname = user.fullname!!
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
        SdkUIFlowBuilder.init()
            .setClientKey(Constants.CLIENT_KEY) // client_key is mandatory
            .setContext(this) // context is mandatory
            .setTransactionFinishedCallback { result -> transactionFinishedCallback(result) } // set transaction finish callback (sdk callback)
            .setMerchantBaseUrl(Constants.BASE_URL) //set merchant url (required)
            .enableLog(true) // enable sdk log (optional)
            .setLanguage("id") //`en` for English and `id` for Bahasa
            .buildSDK()
    }

    private fun showData(userId: Int) {
        viewModel.setCarts(userId)
        viewModel.getCarts().observe(this@CartActivity){
            adapter.setData(it?.data?.data!! as List<CartsDataItem>)
            cartResp = it
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
                val orderIdStr : String = userId.toString() + System.currentTimeMillis().toString()
                orderId = orderIdStr.toLong()
                if (paymentType == "COD") {
                    viewModel.setInsertTransaction(orderId, userId, receiverName.text.toString(), receiverPhone.text.toString(), receiverAddress.text.toString(), paymentType)
                    viewModel.getInsertTransaction().observe(this@CartActivity){
                        if (it.errorMessage != "") {
                            it.errorMessage?.let { it1 -> ToastHelper.showToast(this, it1) }
                        }else if(it.data?.success == 0) {
                            it.data.message?.let { it1 -> ToastHelper.showToast(this, it1) }
                        }else{
//                            ToastHelper.showToast(this, "Transaksi Berhasil")
//                            startActivity(Intent(this@CartActivity, MainActivity::class.java))
//                            finish()
                        }
                    }
                    ToastHelper.showToast(this, "Transaksi Berhasil")
                    startActivity(Intent(this@CartActivity, MainActivity::class.java))
                    finish()
                }else{
                    val request: TransactionRequest = transactionRequest(orderIdStr)
                    MidtransSDK.getInstance().transactionRequest = request
                    MidtransSDK.getInstance().startPaymentUiFlow(this@CartActivity)
                }
            }
        }
    }

    private fun transactionRequest(externalId: String): TransactionRequest {
        val request = TransactionRequest(externalId,
            cartResp.data?.totalTagihanNumeric!!.toDouble()
        )
        val customerDetails = CustomerDetails()
        customerDetails.customerIdentifier = userId.toString()
        customerDetails.email = email
        customerDetails.firstName = receiverName.text.toString()
        customerDetails.phone = receiverPhone.text.toString()
        val shippingAddress = ShippingAddress()
        shippingAddress.address = receiverAddress.text.toString()
        customerDetails.shippingAddress = shippingAddress
        val billingAddress = BillingAddress()
        billingAddress.address = receiverAddress.text.toString()
        customerDetails.billingAddress = billingAddress
        val creditCard = CreditCard()
        creditCard.isSaveCard = false
        creditCard.authentication = Authentication.AUTH_3DS
        request.creditCard = creditCard
        request.setCustomerDetails(customerDetails)
        request.orderId
        return request
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
                //COD
            }
            R.id.radio_button_2 -> {
                paymentType = "Manual Transfer"
                //Manual Transfer
            }
        }
    }

    fun transactionFinishedCallback(result: TransactionResult) {
        if (result.response != null) {
            when (result.status) {
                TransactionResult.STATUS_SUCCESS -> {
                    ToastHelper.showToast(this, "Transaksi Berhasil")
                }
                TransactionResult.STATUS_PENDING -> {
                    viewModel.setInsertTransaction(orderId, userId, receiverName.text.toString(), receiverPhone.text.toString(), receiverAddress.text.toString(), paymentType)
                    viewModel.getInsertTransaction().observe(this@CartActivity){
                        if (it.errorMessage != "") {
                            it.errorMessage?.let { it1 -> ToastHelper.showToast(this, it1) }
                        }else if(it.data?.success == 0) {
                            it.data.message?.let { it1 -> ToastHelper.showToast(this, it1) }
                        }else{
                            ToastHelper.showToast(this@CartActivity, "Transaksi Berhasil")
                            startActivity(Intent(this@CartActivity, MainActivity::class.java))
                            finish()
                        }
                    }
                    ToastHelper.showToast(
                        this,
                        "Transaksi Berhasil - Menunggu Pembayaran "
                    )
                    startActivity(Intent(this@CartActivity, MainActivity::class.java))
                    finish()
                }
                TransactionResult.STATUS_FAILED -> Toast.makeText(
                    this,
                    "Transaksi Gagal",
                    Toast.LENGTH_LONG
                ).show()
            }
            result.response.validationMessages
        } else if (result.isTransactionCanceled) {
            ToastHelper.showToast(
                this, "Transaksi Dibatalkan"
            )
        } else {
            if (result.status.equals(TransactionResult.STATUS_INVALID, ignoreCase = true)) {
                ToastHelper.showToast(
                    this,
                    "Transaksi Tidak Valid",
                )
            } else {
                ToastHelper.showToast(
                    this, "Ada yang tidak beres",
                )
            }
        }
    }
}
