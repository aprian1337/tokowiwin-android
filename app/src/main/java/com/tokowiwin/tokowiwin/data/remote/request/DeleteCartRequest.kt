package com.tokowiwin.tokowiwin.data.remote.request

import com.google.gson.annotations.SerializedName

data class DeleteCartRequest(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
