package com.d_vide.D_VIDE.app.presentation.Chattings

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.model.Chat
import com.d_vide.D_VIDE.app.domain.model.ConversationUiState
import com.d_vide.D_VIDE.app.domain.model.Message
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChattingsViewModel @Inject constructor(
) : ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference

    var chatList : MutableList<Pair<Int,Chat>> = mutableStateListOf()
    var userId: String = "ascdf"

    init {
        getChattingRoom()
    }

    //채팅방 불러오기
    private fun getChattingRoom() {
        databaseReference.child("chatrooms")
            .orderByChild("users/$userId/userId").equalTo(userId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    viewModelScope.launch {
                        chatList.clear()
                        for (c in snapshot.children) {
                            chatList.add(0, Pair(c.key?.toInt() ?: -1, c.getValue(Chat::class.java) ?: Chat()))
                        }

                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })


    }
}