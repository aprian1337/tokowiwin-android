package com.tokowiwin.tokowiwin.domain.model

import androidx.annotation.Keep

@Keep
data class User(
    var id: String? = "",
    var email: String? = "",
    var fullname: String? = "",
)