package com.d_vide.D_VIDE.app.presentation.RecruitingDetail

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.requestDTO.RecruitingOrderDTO
import com.d_vide.D_VIDE.app.domain.use_case.PostRecruitingOrder
import com.d_vide.D_VIDE.app.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostRecruitingOrderViewModel @Inject constructor(
     val postRecruitingOrderUseCase: PostRecruitingOrder
) : ViewModel() {
    var imageUri = mutableStateOf<Uri?>(null)

    private var _orderId = mutableStateOf(0L)
    val orderId: State<Long> = _orderId


    private var _recruitingOrder = mutableStateOf(RecruitingOrderDTO())
    val recruitingOrderDTO: State<RecruitingOrderDTO> = _recruitingOrder

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

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
                        postRecruitingOrderUseCase(
                            RecruitingOrderDTO(
                                postId = recruitingOrderDTO.value.postId,
                                orderPrice = recruitingOrderDTO.value.orderPrice,
                            )
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
            }
        }
        sealed class UiEvent {
            data class ShowSnackbar(val message: String) : UiEvent()
            object SaveRecruiting : UiEvent()
        }
}