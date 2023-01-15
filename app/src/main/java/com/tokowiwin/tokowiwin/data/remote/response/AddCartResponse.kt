package com.tokowiwin.tokowiwin.data.remote.response

import com.google.gson.annotations.SerializedName

data class AddCartResponse(

    @field:SerializedName("data")
    val data: AddCartData? = null,

    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("error_message")
    val errorMessage: String? = "",

    @field:SerializedName("developer_message")
    val errorDeveloper: String? = ""
)

data class AddCartData(
    @field:SerializedName("success")
    val success: Int? = null,

    @field:SerializedName("message")
    val message: String? = null,
)
