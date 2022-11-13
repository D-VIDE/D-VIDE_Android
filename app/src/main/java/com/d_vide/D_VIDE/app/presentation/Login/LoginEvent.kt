package com.d_vide.D_VIDE.app.presentation.Login

import com.d_vide.D_VIDE.app._enums.Category

sealed class LoginEvent {
    data class EnteredEmail(val value: String): LoginEvent()
    data class EnteredPassword(val value: String): LoginEvent()
    object LoginByKakao: LoginEvent()
    object Login: LoginEvent()
}