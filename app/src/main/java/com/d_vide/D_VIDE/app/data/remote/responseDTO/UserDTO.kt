package com.d_vide.D_VIDE.app.data.remote.responseDTO

data class UserDTO (
    val email: String = "",
    val nickName: String = "",
    val profileImgUrl: String = "",
    val badges: String? = "",
    val followerCount: Int = 0,
    val followingCount: Int = 0,
    val savedPrice: Int = 0
)