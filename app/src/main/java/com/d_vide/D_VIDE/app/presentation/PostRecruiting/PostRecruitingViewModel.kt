package com.d_vide.D_VIDE.app.presentation.PostRecruiting

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingBodyDTO
import com.d_vide.D_VIDE.app.domain.use_case.PostRecruiting
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.presentation.Recruitings.RecruitingsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class PostRecruitingViewModel @Inject constructor(
    val postRecruitingUseCase: PostRecruiting
) : ViewModel() {
    var imageUri = mutableStateOf<Uri?>(null)

    val pathfinder = CameraPosition(LatLng(35.232234, 129.085211), 17f, 1.0f, 0f)
    private val _cameraPositionState = mutableStateOf(CameraPositionState(pathfinder))
    var cameraPositionState: State<CameraPositionState> = _cameraPositionState

    private var _postId = mutableStateOf(0)
    val postId: State<Int> = _postId

    private var _recruitingBody = mutableStateOf(RecruitingBodyDTO())
    val recruitingBodyDTO: State<RecruitingBodyDTO> = _recruitingBody

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
                Log.d("test" , "input timestamp : ${event.value}")
            }
//            is PostRecruitingsEvent.EnteredImage -> {
//                _recruitingBody.value = recruitingBodyDTO.value.copy(
//                    title = event.value
//                )
//            }
//            is PostRecruitingsEvent.EnteredLocation -> {
//                _recruitingBody.value = recruitingBodyDTO.value.copy(
//                    latitude = event.latitude,
//                    longitude = event.longitude
//                )
//            }
            is PostRecruitingsEvent.EnteredContent -> {
                _recruitingBody.value = recruitingBodyDTO.value.copy(
                    content = event.value
                )
            }
            is PostRecruitingsEvent.SaveRecruiting -> {
                viewModelScope.launch {
                    try {
                        Log.d("test", "save button pushed")
                        if (recruitingBodyDTO.value.title.isNullOrBlank()
                            || recruitingBodyDTO.value.content.isNullOrBlank()
                            || recruitingBodyDTO.value.category.isNullOrBlank()
                            || recruitingBodyDTO.value.deliveryPrice == null
                            || recruitingBodyDTO.value.deliveryPrice!! < 0
                            || recruitingBodyDTO.value.targetPrice == null
                            || recruitingBodyDTO.value.targetPrice!! < 0
                        ) {
                            Log.d("test" , ",ERROR 입력 되지 않은 칸이 존재")
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    message = "모든 칸의 내용을 채워주세요"
                                )
                            )
                            return@launch
                        }
                        postRecruitingUseCase(
                            1,
                            RecruitingBodyDTO(
                                title = recruitingBodyDTO.value.title,
                                category = recruitingBodyDTO.value.category,
                                storeName = recruitingBodyDTO.value.storeName,
                                deliveryPrice = recruitingBodyDTO.value.deliveryPrice,
                                content = recruitingBodyDTO.value.content,
                                targetTime = recruitingBodyDTO.value.targetTime
                            )
                        ).collect() { it ->
                            when (it) {
                                is Resource.Success -> {
                                    it.data!!.postId.also { _postId.value = it }
                                    Log.d("test", "success : ${it.data}")
                                }
                                is Resource.Error -> {
                                    Log.d("test", "error")
                                }
                                is Resource.Loading -> {
                                    Log.d("test", "loading")
                                }
                            }
                        }
//                        Log.d("test", "${RecruitingBodyDTO(
//                            title = recruitingBodyDTO.value.title,
//                            category = recruitingBodyDTO.value.category,
//                            storeName = recruitingBodyDTO.value.storeName,
//                            deliveryPrice = recruitingBodyDTO.value.deliveryPrice,
//                            content = recruitingBodyDTO.value.content,
//                            targetTime = recruitingBodyDTO.value.
//                        )}")
                        _eventFlow.emit(UiEvent.SaveRecruiting)
                    } catch (e: Exception) {
                        Log.d("test", "save recruiting error!!")
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
        object SaveRecruiting : UiEvent()
    }
}

