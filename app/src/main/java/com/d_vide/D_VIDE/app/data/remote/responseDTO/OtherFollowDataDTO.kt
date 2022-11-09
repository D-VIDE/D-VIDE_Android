package com.d_vide.D_VIDE.app.data.remote.responseDTO


data class OtherFollowDataDTO(
    val userId: Long = 0L,
    val profileImgUrl: String = "",
    val nickname: String = "",
    val followed: Boolean = false,
    val followId: Long = 0L
)
