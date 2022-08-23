package com.d_vide.D_VIDE.app.presentation.MyPage

import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserDTO


data class MyPageState(
    val isLoading: Boolean = false,
    val userDTO: UserDTO = UserDTO(),
    val error: String = ""
)