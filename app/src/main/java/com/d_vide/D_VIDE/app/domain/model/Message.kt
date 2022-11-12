package com.d_vide.D_VIDE.app.domain.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import com.d_vide.D_VIDE.R

data class ConversationUiState(
    val channelName: String = "채팅방 이름",
    val channelMembers: Int = 3,
    val chatId: String = "",
    val messages: MutableList<Message> = mutableListOf()
)

@Immutable
data class Message(
    val author: String = "",
    val content: String = "",
    val timestamp: String = "",
    val image: Int? = null,
    val authorImage: Int = R.drawable.character_circle,
    val msgType: Int = 1
)

