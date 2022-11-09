package com.d_vide.D_VIDE.app.data.remote.responseDTO

data class UserDTO (
    val email: String = "",
    val nickname: String = "",
    val profileImgUrl: String = "",
    val badge: BadgeDTO = BadgeDTO(),
    val followerCount: Int = 0,
    val followingCount: Int = 0,
    val savedPrice: Int = 0,
)