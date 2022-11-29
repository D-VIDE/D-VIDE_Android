package com.d_vide.D_VIDE.app.presentation.view.Followings

import com.d_vide.D_VIDE.app.data.remote.responseDTO.FollowInfoDataDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.OtherFollowDataDTO

data class FollowState(
    val isLoading: Boolean = false,
    val follows: List<FollowInfoDataDTO> = emptyList(),
    val otherFollows: List<OtherFollowDataDTO> = emptyList(),
    val error: String = "",
    val offset: Int = 0,
    val endReached: Boolean = false,
    val pagingLoading : Boolean = false,
)