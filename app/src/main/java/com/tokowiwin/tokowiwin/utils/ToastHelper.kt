package com.tokowiwin.tokowiwin.utils

import android.content.Context
import android.widget.Toast

object ToastHelper {
    fun showToast(ctx: Context, msg: String) = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
}