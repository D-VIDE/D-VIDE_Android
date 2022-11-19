package com.d_vide.D_VIDE.app.presentation.view.PostRecruiting

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.requestDTO.RecruitingBodyDTO
import com.d_vide.D_VIDE.app.domain.model.ChatUserInfo
import com.d_vide.D_VIDE.app.domain.use_case.PostRecruiting
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.UriUtil.toFile
import com.d_vide.D_VIDE.app.domain.util.log
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.FirebaseDatabase
import com.google.maps.android.compose.CameraPositionState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * userId수정해야함
 */
@HiltViewModel
class PostRecruitingViewModel @Inject constructor(
    val postRecruitingUseCase: PostRecruiting,
    @ApplicationContext val context: Context
) : ViewModel() {
    private var _imageUris = mutableStateListOf<Uri>()
    val imageUris: SnapshotStateList<Uri> = _imageUris

    val pathfinder = CameraPosition(LatLng(35.232234, 129.085211), 17f, 1.0f, 0f)
    private val _cameraPositionState = mutableStateOf(CameraPositionState(pathfinder))
    var cameraPositionState: State<CameraPositionState> = _cameraPositionState

    private var _postId = mutableStateOf(0)
    val postId: State<Int> = _postId

    private var userId = "ascdf"

    private var _recruitingBody = mutableStateOf(RecruitingBodyDTO())
    val recruitingBodyDTO: State<RecruitingBodyDTO> = _recruitingBody

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: PostRecruitingsEvent) {
        when (event) {
            is PostRecruitingsEvent.EnteredTitle -> {
                _recruitingBody.value = recruitingBodyDTO.value.copy(
                    title = event.value
                )
            }
            is PostRecruitingsEvent.EnteredStoreName -> {
                _recruitingBody.value = recruitingBodyDTO.value.copy(
                    storeName = event.value
                )
            }
            is PostRecruitingsEvent.EnteredCategory -> {
                _recruitingBody.value = recruitingBodyDTO.value.copy(
                    category = event.value.tag
                )
            }
            is PostRecruitingsEvent.EnteredDeliveryPrice -> {
                _recruitingBody.value = recruitingBodyDTO.value.copy(
                    deliveryPrice = event.value
                )
            }
            is PostRecruitingsEvent.EnteredTargetPrice -> {
                _recruitingBody.value = recruitingBodyDTO.value.copy(
                    targetPrice = event.value
                )
            }
            is PostRecruitingsEvent.EnteredTargetTime -> {
                _recruitingBody.value = recruitingBodyDTO.value.copy(
                    targetTime = event.value
                )
            }
            is PostRecruitingsEvent.EnteredImage -> {
                if(event.index >= 0)
                    _imageUris[event.index] = event.value!!
                else if(_imageUris.size < 3)
                    _imageUris.add(event.value!!)
            }
            is PostRecruitingsEvent.DeleteImage -> {
                _imageUris.removeAt(event.index)
            }
            is PostRecruitingsEvent.EnteredContent -> {
                _recruitingBody.value = recruitingBodyDTO.value.copy(
                    content = event.value
                )

            }
            /**
             * 모집글 올린 후 바로 채팅방생성
             */
            is PostRecruitingsEvent.SaveRecruiting -> {
                viewModelScope.launch {
                    try {
                        // 모든 빈칸이 채워졌는지 check
                        if (recruitingBodyDTO.value.title.isNullOrBlank()
                            || recruitingBodyDTO.value.content.isNullOrBlank()
                            || recruitingBodyDTO.value.category.isNullOrBlank()
                            || recruitingBodyDTO.value.deliveryPrice == null
                            || recruitingBodyDTO.value.deliveryPrice!! < 0
                            || recruitingBodyDTO.value.targetPrice == null
                            || recruitingBodyDTO.value.targetPrice!! < 0
                        ) {
                            _eventFlow.emit(UiEvent.ShowSnackbar("모든 칸의 내용을 채워주세요"))
                            return@launch
                        }

                        val fileList = _imageUris.map { toFile(context, it) }

                        val location = _cameraPositionState.value.position.target
                        postRecruitingUseCase(
                            RecruitingBodyDTO(
                                title = recruitingBodyDTO.value.title,
                                category = recruitingBodyDTO.value.category,
                                storeName = recruitingBodyDTO.value.storeName,
                                deliveryPrice = recruitingBodyDTO.value.deliveryPrice,
                                content = recruitingBodyDTO.value.content,
                                targetTime = recruitingBodyDTO.value.targetTime,
                                longitude = location.longitude,
                                latitude = location.latitude
                            ), fileList
                        ).collect() { it ->
                            when (it) {
                                is Resource.Success -> {
                                    it.data!!.postId.also { _postId.value = it }
                                    Log.d("가희", "모집글 올리기 성공 postId: ${it.data!!.postId}")
                                    _eventFlow.emit(UiEvent.SaveRecruiting(it.data.postId.toLong()))

                                    //채팅방 생성
                                    databaseReference.child("chatrooms")
                                        .child("${it.data.postId}")
                                        .child("users/$userId").setValue(ChatUserInfo(userId,"nickname",false))

                                    databaseReference.child("chatrooms")
                                        .child("${it.data.postId}")
                                        .child("title").setValue(recruitingBodyDTO.value.title)

                                }
                                is Resource.Error -> "모집글 올리기 실패".log()
                                is Resource.Loading -> "모집글 올리는 중".log()
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "모집글을 작성하는 동안 오류가 발생했습니다"
                            )
                        )
                    }
                }
            }
        }
    }
    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data class SaveRecruiting(val postId: Long) : UiEvent()
    }
}

