package com.d_vide.D_VIDE.app.presentation.MyPage

import com.d_vide.D_VIDE.app.data.remote.requestDTO.BadgeRequestDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.BadgeDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserDTO


data class MyPageState(
    val isLoading: Boolean = false,
    val userDTO: UserDTO = UserDTO(),
    val badgesDTO: List<BadgeDTO> = emptyList(),
    val badgeRequestDTO: BadgeRequestDTO = BadgeRequestDTO(),
    val error: String = ""
)