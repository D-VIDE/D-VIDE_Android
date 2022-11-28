package com.d_vide.D_VIDE.app.domain.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import com.d_vide.D_VIDE.R

data class ConversationUiState(
    var channelName: String = "채팅방 이름",
    val chatId: String = "",
    val messages: MutableList<Message> = mutableListOf(),
    var users: MutableMap<String, ChatUserInfo>  = HashMap()
)

@Immutable
data class Message(
    val author: String = "",
    val content: String = "",
    val timestamp: Long = 0L,
    val image: Int? = null,
    val authorImage: Int = R.drawable.character_circle,
    val msgType: Int = 1
)

