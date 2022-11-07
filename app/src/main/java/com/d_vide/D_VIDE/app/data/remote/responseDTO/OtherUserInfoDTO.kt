package com.d_vide.D_VIDE.app.data.remote.responseDTO

data class OtherUserInfoDTO(
    val nickname: String = "",
    val profileImgUrl: String = "",
    val badge: BadgeDTO = BadgeDTO(),
    val followerCount: Int = 0,
    val followingCount: Int = 0,
    val followed: Boolean = false
)
