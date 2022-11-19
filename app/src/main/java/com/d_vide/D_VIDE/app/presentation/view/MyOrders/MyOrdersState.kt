package com.d_vide.D_VIDE.app.presentation.view.MyOrders

import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingDTO

data class MyOrdersState(
    val isLoading: Boolean = false,
    val recruitingDTOs: List<RecruitingDTO> = emptyList(),
    val error: String = ""
)
