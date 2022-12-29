package com.tokowiwin.tokowiwin.data.remote.response

import com.google.gson.annotations.SerializedName

data class TransactionsResponse(

	@field:SerializedName("data")
	val data: TransactionsData? = null,

	@field:SerializedName("status")
	val status: Int? = null,

    @field:SerializedName("error_message")
    val errorMessage: String? = "",

    @field:SerializedName("developer_message")
    val errorDeveloper: String? = ""
)

data class TransactionsData(
	@field:SerializedName("data")
	val data: List<TransactionsDataItem?>? = null
)

data class TransactionDetails(

	@field:SerializedName("payment_type")
	val paymentType: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("receiver_name")
	val receiverName: String? = null,

	@field:SerializedName("transaction_products")
	val transactionProducts: List<TransactionProductsItem?>? = null,

	@field:SerializedName("receiver_phone")
	val receiverPhone: String? = null,

	@field:SerializedName("total_bill")
	val totalBill: String? = null
)

data class TransactionProductsItem(

	@field:SerializedName("product_pic")
	val productPic: String? = null,

	@field:SerializedName("product_qty")
	val productQty: Int? = null,

	@field:SerializedName("product_price")
	val productPrice: String? = null,

	@field:SerializedName("product_total_price")
	val productTotalPrice: String? = null,

	@field:SerializedName("product_name")
	val productName: String? = null
)

data class TransactionsDataItem(

	@field:SerializedName("transaction_id")
	val transactionId: Int? = null,

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("another_product")
	val anotherProduct: String? = null,

	@field:SerializedName("product_pic")
	val productPic: String? = null,

	@field:SerializedName("product_name")
	val productName: String? = null,

	@field:SerializedName("transaction_details")
	val transactionDetails: TransactionDetails? = null
)
