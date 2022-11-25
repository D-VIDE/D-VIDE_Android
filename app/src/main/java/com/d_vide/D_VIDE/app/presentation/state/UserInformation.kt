package com.d_vide.D_VIDE.app.presentation.state

import com.d_vide.D_VIDE.app.domain.model.UserInfo
import com.d_vide.D_VIDE.app.domain.util.log

object UserInformation {
    var userInfo: UserInfo = UserInfo()
    var token: String = ""
    var fcm: String = ""

    fun logUser() {
        "[USER] : ID:${userInfo.userId}/EMAIL:$userInfo.email/NICK:${userInfo.nickname}/FOLLOW:${userInfo.followerCount},${userInfo.followingCount}/JWT:$token/FCM:$fcm".log()
    }
}