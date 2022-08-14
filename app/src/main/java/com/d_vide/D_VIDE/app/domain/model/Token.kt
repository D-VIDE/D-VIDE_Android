package com.d_vide.D_VIDE.app.domain.model

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("token")
    var value: String = ""
)
