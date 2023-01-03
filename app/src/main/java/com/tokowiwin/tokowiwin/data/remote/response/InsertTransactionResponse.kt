package com.tokowiwin.tokowiwin.data.remote.response

import com.google.gson.annotations.SerializedName

data class InsertTransactionResponse(

    @field:SerializedName("data")
    val data: InsertTransactionData? = null,

    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("error_message")
    val errorMessage: String? = "",

    @field:SerializedName("developer_message")
    val errorDeveloper: String? = ""
)

data class InsertTransactionData(
    @field:SerializedName("success")
    val success: Int? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("payment_type")
    val paymentType: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,
)
