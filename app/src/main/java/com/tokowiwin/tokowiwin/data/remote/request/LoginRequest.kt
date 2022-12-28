package com.tokowiwin.tokowiwin.data.remote.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @field:SerializedName("email")
    val username: String = "",

    @field:SerializedName("password")
    val password: String = ""
)