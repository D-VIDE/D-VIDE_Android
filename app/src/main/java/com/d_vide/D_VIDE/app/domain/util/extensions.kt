package com.d_vide.D_VIDE.app.domain.util

import android.util.Log

fun String.log(header: String = "D/VIDE") {
    Log.d(header, this)
}