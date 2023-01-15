package com.tokowiwin.tokowiwin.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
	@field:SerializedName("data")
	val data: List<ProductsDataItem?>? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("error_message")
	val errorMessage: String? = "",

	@field:SerializedName("developer_message")
	val errorDeveloper: String? = ""
)

data class ProductsDataItem(
	@field:SerializedName("product_pic")
	val productPic: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("product_stock")
	val productStock: Int? = null,

	@field:SerializedName("product_price")
	val productPrice: String? = null,

	@field:SerializedName("product_name")
	val productName: String? = null
)
