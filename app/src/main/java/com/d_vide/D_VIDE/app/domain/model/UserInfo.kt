package com.d_vide.D_VIDE.app.domain.model


data class UserInfo(
    val token: String = "",
//    val id: String,
//    val email: String,
    val badge: String = "",
    val nickName: String = "",
) {
    fun isGuest() : Boolean = (this == GUEST)

    companion object {
        val GUEST = UserInfo(
            "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlbWFpbEBnbWFpbC5jb20iLCJhdXRoIjoiVVNFUiIsImV4cCI6MjY1OTU5MzMyM30.H_KhH3tK7PRmw0wHU045G22UdRVy1BXbmeRLFGFD4oyYpAucWPPmFiHB6s4HV2sVFisNd46LnZioE_BR4K11vw",
            "디바이드 게스트",
            "GUEST"
        )
    }
}