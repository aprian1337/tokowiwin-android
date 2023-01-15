package com.tokowiwin.tokowiwin.data.remote.request

import com.google.gson.annotations.SerializedName

data class ChangePassRequest(
    @field:SerializedName("email")
    val username: String = "",

    @field:SerializedName("old_password")
    val oldPassword: String = "",

    @field:SerializedName("new_password")
    val newPassword: String = ""
)