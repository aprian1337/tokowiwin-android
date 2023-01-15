package com.tokowiwin.tokowiwin.data.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateCartResponse(

	@field:SerializedName("data")
	val data: UpdateCartData? = null,

	@field:SerializedName("status")
	val status: Int? = null,

	@field:SerializedName("error_message")
	val errorMessage: String? = "",

	@field:SerializedName("developer_message")
	val errorDeveloper: String? = ""
)

data class UpdateCartData(

	@field:SerializedName("success")
	val success: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)
