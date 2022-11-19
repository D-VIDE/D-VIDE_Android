package com.d_vide.D_VIDE.app.presentation.view.RecruitingDetail

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.requestDTO.RecruitingOrderDTO
import com.d_vide.D_VIDE.app.domain.model.ChatUserInfo
import com.d_vide.D_VIDE.app.domain.use_case.PostRecruitingOrder
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.UriUtil
import com.d_vide.D_VIDE.app.presentation.view.PostRecruiting.PostRecruitingsEvent
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 나중에 userId 수정
 */
@HiltViewModel
class PostRecruitingOrderViewModel @Inject constructor(
     val postRecruitingOrderUseCase: PostRecruitingOrder,

     @ApplicationContext val context: Context
) : ViewModel() {
    private var _imageUris = mutableStateListOf<Uri>()
    val imageUris: SnapshotStateList<Uri> = _imageUris

    private var _orderId = mutableStateOf(0L)
    val orderId: State<Long> = _orderId

    private var _recruitingOrder = mutableStateOf(RecruitingOrderDTO())
    val recruitingOrderDTO: State<RecruitingOrderDTO> = _recruitingOrder

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference

    //유저의 아이디 넣는 곳
    //차후 수정 필요
    var userId = "userId"

    fun onEvent(event: PostRecruitingOrderEvent){
        when (event) {
            is PostRecruitingOrderEvent.EnteredPostId -> {
                _recruitingOrder.value = recruitingOrderDTO.value.copy(
                    postId = event.value
                )
            }
            is PostRecruitingOrderEvent.EnteredOrderPrice -> {
                _recruitingOrder.value = recruitingOrderDTO.value.copy(
                    orderPrice = event.value
                )
            }

            is PostRecruitingOrderEvent.EnteredImage -> {
                if(event.index >= 0)
                    _imageUris[event.index] = event.value!!
                else if(_imageUris.size < 3)
                    _imageUris.add(event.value!!)
            }
            is PostRecruitingOrderEvent.DeleteImage -> {
                _imageUris.removeAt(event.index)
            }

            is PostRecruitingOrderEvent.SaveRecruitingOrder -> {
                viewModelScope.launch {
                    try {
                        Log.d("testtest", "${recruitingOrderDTO.value.postId} and ${recruitingOrderDTO.value.orderPrice}")
                        if (recruitingOrderDTO.value.orderPrice == null) {
                            Log.d("test", "주문 가격을 입력해주세요.")
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    message = "모든 칸의 내용을 채워주세요"
                                )
                            )
                            return@launch
                        }

                        val fileList = _imageUris.map { UriUtil.toFile(context, it) }

                        postRecruitingOrderUseCase(
                            RecruitingOrderDTO(
                                postId = recruitingOrderDTO.value.postId,
                                orderPrice = recruitingOrderDTO.value.orderPrice,
                            ),
                            fileList
                        ).collect() { it ->
                            when (it) {
                                is Resource.Success -> {
                                    //  it.data!!.postId.also { _postId.value = it }
                                    it.data!!.orderId.also { _orderId.value = it }
                                    Log.d("testtest", "success : ${it.data}")
                                }
                                is Resource.Error -> {
                                    Log.d("test", "error")
                                }
                                is Resource.Loading -> {
                                    Log.d("test", "loading")
                                }
                            }
                        }
                        _eventFlow.emit(UiEvent.SaveRecruiting)
                    } catch (e: Exception) {
                        Log.d("test", "save recruiting order error!!")
                        e.printStackTrace()
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "주문서를 작성하는 동안 오류가 발생했습니다"
                            )
                        )
                    }
                    }
                }
            /**
             * 유저를 채팅방에 넣기 위한 작업
             */
            is PostRecruitingOrderEvent.EnterChatting -> {
                databaseReference.child("chatrooms")
                .child("${event.value}")
                .child("users/$userId").setValue(ChatUserInfo(userId,"nickname",false))

                viewModelScope.launch {
                    _eventFlow.emit(
                        UiEvent.SaveRecruiting
                    )
                }
            }
        }
    }
    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveRecruiting : UiEvent()
    }
}