package com.d_vide.D_VIDE.app.presentation.view.Login

import android.content.Context
import com.d_vide.D_VIDE.app._enums.Category

sealed class LoginEvent {
    data class EnteredEmail(val value: String): LoginEvent()
    data class EnteredPassword(val value: String): LoginEvent()
    data class LoginByKakao(val context: Context): LoginEvent()
    object Login: LoginEvent()
}