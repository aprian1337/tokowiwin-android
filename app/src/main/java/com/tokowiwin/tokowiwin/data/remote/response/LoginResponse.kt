package com.tokowiwin.tokowiwin.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("data")
    val data: LoginData? = null,

    @field:SerializedName("status")
    val status: Int? = null,

    @field:SerializedName("error_message")
    val errorMessage: String? = "",

    @field:SerializedName("developer_message")
    val errorDeveloper: String? = ""
)

data class LoginData(

    @field:SerializedName("success")
    val success: Int? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("user")
    val user: LoginUser? = null
)

data class LoginUser(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("fullname")
    val fullname: String? = null,

    @field:SerializedName("email")
    val email: String? = null
)
