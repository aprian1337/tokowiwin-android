package com.tokowiwin.tokowiwin.data.remote.response

import com.google.gson.annotations.SerializedName

data class CartsResponse(

	@field:SerializedName("data")
	val data: CartsData? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class CartsDataItem(

	@field:SerializedName("product_pic")
	val productPic: String? = null,

	@field:SerializedName("qty")
	val qty: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("product_stock")
	val productStock: Int? = null,

	@field:SerializedName("product_price")
	val productPrice: String? = null,

	@field:SerializedName("product_total_price")
	val productTotalPrice: String? = null,

	@field:SerializedName("product_name")
	val productName: String? = null
)

data class CartsData(

	@field:SerializedName("data")
	val data: List<CartsDataItem?>? = null,

	@field:SerializedName("total_tagihan")
	val totalTagihan: String? = null
)
