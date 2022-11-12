package com.d_vide.D_VIDE.app.domain.model

import com.d_vide.D_VIDE.app.data.remote.responseDTO.BadgeDTO

data class ChatUserInfo(
    val userId: String = "",
    val nickname: String = "",
    val unRead: Boolean = true
)