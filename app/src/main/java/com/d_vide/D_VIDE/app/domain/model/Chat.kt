package com.d_vide.D_VIDE.app.domain.model


data class Chat(
    var title: String = "",
    var users: MutableMap<String, ChatUserInfo> = HashMap(),
    var lastMessage: Message = Message()
)

