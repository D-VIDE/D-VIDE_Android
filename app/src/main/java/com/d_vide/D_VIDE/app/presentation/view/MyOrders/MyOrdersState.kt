package com.d_vide.D_VIDE.app.presentation.view.MyOrders

import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingDTO

data class MyOrdersState(
    val isLoading: Boolean = false,
    val recruitings: List<RecruitingDTO> = emptyList(),
    val error: String = "",
    val offset: Int = 0,
    val endReached: Boolean = false,
    val pagingLoading : Boolean = false,
)
