package com.d_vide.D_VIDE.app.presentation.view.MyPage

import com.d_vide.D_VIDE.app.data.remote.requestDTO.BadgeRequestDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.BadgeDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserDTO


data class MyPageState(
    var isLoading: Boolean = false,
    var userDTO: UserDTO = UserDTO(),
    var badgesDTO: List<BadgeDTO> = emptyList(),
    var badgeRequestDTO: BadgeRequestDTO = BadgeRequestDTO(),
    var error: String = ""
)