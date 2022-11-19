package com.d_vide.D_VIDE.app.presentation.state

import com.d_vide.D_VIDE.app.domain.util.log

object UserInformation {
    var userId: Long = 0
    var email : String = ""
    var profileImg_url: String = ""
    var token: String = ""
    var fcm: String = ""

    fun logUser() {
        "[USER] : ID:$userId/EMAIL:$email/JWT:$token/FCM:$fcm".log()
    }
}