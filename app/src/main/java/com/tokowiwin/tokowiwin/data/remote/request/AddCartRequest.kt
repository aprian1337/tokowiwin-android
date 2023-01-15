package com.tokowiwin.tokowiwin.data.remote.request

import com.google.gson.annotations.SerializedName

data class AddCartRequest(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("qty")
	val qty: Int? = null
)
