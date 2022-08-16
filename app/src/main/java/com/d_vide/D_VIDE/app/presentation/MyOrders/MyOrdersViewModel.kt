package com.d_vide.D_VIDE.app.presentation.MyOrders

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.use_case.GetMyOrders
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyOrdersViewModel @Inject constructor(
    val getMyOrdersUseCase: GetMyOrders,
) : ViewModel() {

    private val _state = mutableStateOf(MyOrdersState())
    val state: State<MyOrdersState> = _state

    init {
        getMyOrders()
    }

    fun getMyOrders() {
        viewModelScope.launch {
            getMyOrdersUseCase().collect() {
                when (it) {
                    is Resource.Success -> {
                        _state.value =
                            it.data?.let { MyOrdersState(recruitingDTOs = it.recruitingDTOS) }!!
                        "내 주문 목록 가져오기 성공".log()
                    }
                    is Resource.Error -> "내 주문 목록 가져오기 실패".log()
                    is Resource.Loading -> "내 주문 목록 가져오는 중".log()
                }
            }
        }
    }
}