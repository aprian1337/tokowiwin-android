package com.tokowiwin.tokowiwin.data.remote.request

import com.google.gson.annotations.SerializedName

data class InsertTransactionRequest(
	@field:SerializedName("id")
	val id: Long? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("receiver_name")
	val receiverName: String? = null,

	@field:SerializedName("receiver_address")
	val receiverAddress: String? = null,

	@field:SerializedName("receiver_phone")
	val receiverPhone: String? = null,

	@field:SerializedName("payment_type")
	val paymentType: String? = null
)
