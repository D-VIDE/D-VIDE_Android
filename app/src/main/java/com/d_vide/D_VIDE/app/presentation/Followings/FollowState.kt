package com.d_vide.D_VIDE.app.presentation.Followings

import com.d_vide.D_VIDE.app.data.remote.responseDTO.FollowInfoDataDTO

data class FollowState(
    val isLoading: Boolean = false,
    val follows: List<FollowInfoDataDTO> = emptyList(),
    val error: String = ""
)