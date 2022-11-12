package com.d_vide.D_VIDE.app.presentation.ChattingDetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.model.ConversationUiState
import com.d_vide.D_VIDE.app.domain.model.Message
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * chat Id를 넘겨서 수정해야함.
 */
@HiltViewModel
class ChattingDetailViewModel @Inject constructor(
) : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference


    private var _state = mutableStateOf(ConversationUiState())
    val state = _state

    private var userId = ""

    init {
        getChattingInfo()
    }

    //채팅 불러오기 user.id
    private fun getChattingInfo() {
        databaseReference.child("chatrooms")
            .child("-NG_wHc2EcSU-dt0g4Zg").child("message")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    viewModelScope.launch {
                        var mList = mutableListOf<Message>()
                        for (c in snapshot.children) {
                            mList.add(0, c.getValue(Message::class.java) ?: Message("", "", ""))
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

    /**
     * 메세지를 보내는 함수
     * @param msg 보내려는 메세지
     */
    fun send(msg: Message) {
        //메세지 보내기
        databaseReference.child("chatrooms")
            .child("-NG_wHc2EcSU-dt0g4Zg")
            .child("message").push().setValue(msg)

        //메세지 안읽음 개수 올리기
        val taskMap: MutableMap<String, Any> = HashMap()
        taskMap["/users/$userId/unRead"] = true
        databaseReference.child("chatrooms")
            .child("-NG_wHc2EcSU-dt0g4Zg").updateChildren(taskMap)

        //last Message 업데이트 하기
        databaseReference.child("chatrooms")
            .child("-NG_wHc2EcSU-dt0g4Zg")
            .child("lastMessage").setValue(msg)
    }

}

