package com.d_vide.D_VIDE.app.presentation.view.ChattingDetail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.model.ChatUserInfo
import com.d_vide.D_VIDE.app.domain.model.ConversationUiState
import com.d_vide.D_VIDE.app.domain.model.Message
import com.d_vide.D_VIDE.app.presentation.navigation.DetailDestinationKey
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * chat Id를 넘겨서 수정해야함.
 */
@HiltViewModel
class ChattingDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference


    private var _state = mutableStateOf(ConversationUiState())
    val state = _state

    private var userId = "ascdf"
    private var chatId = savedStateHandle.get<Long>(DetailDestinationKey.CHATTING)!!


    init {
        getChattingInfo()
        getUsersInfo()
    }

    //채팅 불러오기 user.id
    private fun getChattingInfo() {
        databaseReference.child("chatrooms")
            .child("$chatId").child("message")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    viewModelScope.launch {
                        var mList = mutableListOf<Message>()
                        for (c in snapshot.children) {
                            mList.add(0, c.getValue(Message::class.java) ?: Message("", ""))
                        }
                        _state.value = ConversationUiState(messages = mList)
                        //메세지 읽음(false로 수정)
                        changeUnRead(userId, unRead = false)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })


    }

    //읽음, 안읽음 수정
    private fun changeUnRead(userId: String, unRead: Boolean) {
        val taskMap: MutableMap<String, Any> = HashMap()
        taskMap["/users/$userId/unRead"] = unRead
        databaseReference.child("chatrooms")
            .child("$chatId").updateChildren(taskMap)
    }

    //채팅방 참가자 얻어오기
    private fun getUsersInfo() {
        databaseReference.child("chatrooms")
            .child("$chatId").child("users")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    viewModelScope.launch {
                        _state.value.users = snapshot.getValue<MutableMap<String, ChatUserInfo>>()!!
                        Log.d("가희1", _state.value.users.toString())
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
        //메세지 안읽음(true)로 수정
        state.value.users.forEach {
            changeUnRead(it.key, unRead = true)
        }
        //메세지 보내기
        databaseReference.child("chatrooms")
            .child("$chatId")
            .child("message").push().setValue(msg)



        //이부분은 유저를 채팅방에 넣을 때 사용할 것
//        val taskMap: MutableMap<String, Any> = HashMap()
//        taskMap["/users/$userId/unRead"] = true
//        databaseReference.child("chatrooms")
//            .child("-NG_wHc2EcSU-dt0g4Zg")
//            .child("users/ascdf").setValue(ChatUserInfo("ascdf","nickname",false))

        //last Message 업데이트 하기
        databaseReference.child("chatrooms")
            .child("$chatId")
            .child("lastMessage").setValue(msg)
    }

}

