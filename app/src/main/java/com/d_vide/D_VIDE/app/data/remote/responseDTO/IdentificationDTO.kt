package com.d_vide.D_VIDE.app.data.remote.responseDTO

import com.google.gson.annotations.SerializedName

data class IdentificationDTO(
    @SerializedName("token")
    var token: String = "",

    @SerializedName("userId")
    var userId: Long = 0
)
