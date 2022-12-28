package com.tokowiwin.tokowiwin.data.local

import android.content.Context
import com.tokowiwin.tokowiwin.data.remote.response.LoginUser
import com.tokowiwin.tokowiwin.utils.AuthType

class UserPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val IS_LOGIN = "is_login"
        private const val ID = "id"
        private const val FULLNAME = "fullname"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(user: LoginUser?, type: AuthType) {
        val editor = preferences.edit()
        when (type) {
            AuthType.LOGIN -> {
                editor.putString(ID, user?.id.toString())
                editor.putString(FULLNAME, user?.fullname)
                editor.putBoolean(IS_LOGIN, true)
                editor.apply()
            }
            AuthType.LOGOUT -> {
                editor.putString(ID, null)
                editor.putString(FULLNAME, null)
                editor.putBoolean(IS_LOGIN, false)
                editor.apply()
            }
        }
    }

    fun getUser(): SessionLogin = SessionLogin(
        preferences.getString(ID, ""),
        preferences.getString(FULLNAME, ""),
        preferences.getBoolean(IS_LOGIN, false)
    )

}