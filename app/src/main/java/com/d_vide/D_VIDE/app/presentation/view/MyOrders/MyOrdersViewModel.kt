package com.d_vide.D_VIDE.app.presentation.view.MyOrders

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.use_case.GetMyOrders
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyOrdersViewModel @Inject constructor(
    val getMyOrdersUseCase: GetMyOrders,
) : ViewModel() {

    private val _state = MutableStateFlow(MyOrdersState())
    val state = _state

    init {
        getMyOrders()
    }

    fun getMyOrders() {
        viewModelScope.launch {
            getMyOrdersUseCase().collect() { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                recruitings = it.recruitings + (result.data?.recruitingDTOS ?: emptyList()),
                                isLoading = false,
                                offset = it.offset + (result.data?.recruitingDTOS?.size ?: 0),
                                pagingLoading = false,
                                endReached = result.data?.recruitingDTOS?.size!! < 10
                            )
                        }

                        "내 주문 목록 가져오기 성공${result.data?.recruitingDTOS?.size ?: 0}".log()
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message ?: "An unexpected error occured in my order",
                                isLoading = false,
                                pagingLoading = false,
                                endReached = true
                            )
                        }
                        "내 주문 목록 가져오기 실패".log()
                    }

                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                pagingLoading = true
                            )
                        }
                        "내 주문 목록 가져오는 중".log()
                    }

                }
            }
        }
    }
}