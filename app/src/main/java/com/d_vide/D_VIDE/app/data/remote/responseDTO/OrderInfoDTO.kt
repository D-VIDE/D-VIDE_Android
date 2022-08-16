package com.d_vide.D_VIDE.app.data.remote.responseDTO

import com.google.gson.annotations.SerializedName

data class OrderInfoDTO(
    @SerializedName("orderedPrice")
    val price: Int = 0,
    @SerializedName("orderedTime")
    val time: Long = 0L
)
