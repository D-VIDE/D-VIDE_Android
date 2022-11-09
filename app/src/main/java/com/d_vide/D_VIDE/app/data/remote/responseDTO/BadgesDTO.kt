package com.d_vide.D_VIDE.app.data.remote.responseDTO

import com.google.gson.annotations.SerializedName

data class BadgesDTO(
    @SerializedName("badges")
    val badges: List<BadgeDTO>
)
