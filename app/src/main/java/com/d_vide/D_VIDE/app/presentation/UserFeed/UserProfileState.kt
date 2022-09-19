package com.d_vide.D_VIDE.app.presentation.UserFeed

import com.d_vide.D_VIDE.app.data.remote.responseDTO.OtherUserInfoDTO

data class UserProfileState (
    val isLoading: Boolean = false,
    val userProfile: OtherUserInfoDTO = OtherUserInfoDTO(),
    val error: String = ""
)