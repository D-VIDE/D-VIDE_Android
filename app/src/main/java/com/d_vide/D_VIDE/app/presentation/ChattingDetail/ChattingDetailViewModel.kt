package com.d_vide.D_VIDE.app.presentation.ChattingDetail

import android.util.Log
import androidx.compose.runtime.getValue

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.model.ConversationUiState
import com.d_vide.D_VIDE.app.domain.model.Message
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChattingDetailViewModel @Inject constructor(
) : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference


    private var _state = mutableStateOf(ConversationUiState())
    val state = _state


    init {
        getChattingInfo()
    }

    //초기 채팅방 정보 불러오기
    private fun getChattingInfo() {
        databaseReference.child("chatrooms")
            .child("-NG_wHc2EcSU-dt0g4Zg").child("message")
            .addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                viewModelScope.launch {
                    var mList = mutableListOf<Message>()
                    for(c in snapshot.children) {
                        mList.add(0, c.getValue(Message::class.java)?: Message("","",""))
                    }
                    _state.value = ConversationUiState(messages = mList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



    }

    fun makeChat() {
        //var a = Chat(title ="채팅방1", users = ,)
        //databaseReference.child("chatrooms").push().setValue(a)
    }

    //메세지 보내기
    /**
     * 메세지를 보내는 함수
     * @param msg 보내려는 메세지
     */
    fun send(msg: Message) {

        databaseReference.child("chatrooms")
            .child("-NG_wHc2EcSU-dt0g4Zg")
            .child("message").push().setValue(msg)
    }

}

