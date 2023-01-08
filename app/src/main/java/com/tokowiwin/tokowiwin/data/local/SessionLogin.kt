package com.tokowiwin.tokowiwin.data.local

data class SessionLogin(
    val id: String?="",
    val fullname: String?="",
    val headerText: String?="",
    val email: String?="",
    val isLogin: Boolean?=false
)