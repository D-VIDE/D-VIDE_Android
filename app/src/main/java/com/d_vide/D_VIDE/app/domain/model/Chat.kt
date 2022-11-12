package com.d_vide.D_VIDE.app.domain.model

import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserDTO

data class Chat(
    var message: MutableList<Message> = mutableListOf(),
    var title: String = "",
    var users: ArrayList<UserDTO> = arrayListOf(),
    var lastMessage: Message = Message()
)

