package com.d_vide.D_VIDE.app.data.remote.responseDTO

import com.google.gson.annotations.SerializedName

data class FollowInfoDTO(
    @SerializedName("data")
    val follows: List<FollowInfoDataDTO>
)
