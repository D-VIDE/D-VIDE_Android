package com.d_vide.D_VIDE.app.domain.model


data class UserInfo(
    val token: String,
    val id: String,
    val email: String,
    val password: String,
    val profileImgUrl: String,
    val nickName: String,
    val badges: String?,
    val followerCount: Int = 0,
    val followingCount: Int = 0,
    val savedPrice: Int = 0
) {
    fun isGuest() : Boolean = (this == GUEST)

    companion object {
        val GUEST = UserInfo(
            token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlbWFpbEBnbWFpbC5jb20iLCJhdXRoIjoiVVNFUiIsImV4cCI6MjY1OTU5MzMyM30.H_KhH3tK7PRmw0wHU045G22UdRVy1BXbmeRLFGFD4oyYpAucWPPmFiHB6s4HV2sVFisNd46LnZioE_BR4K11vw",
            id = "디바이드 게스트",
            email = "email@gmail.com",
            password = "password1",
            profileImgUrl = "",
            nickName = "룡룡",
            badges = "디바이드 게스트",
            followerCount = 0,
            followingCount = 0,
            savedPrice = 0
        )
    }
}