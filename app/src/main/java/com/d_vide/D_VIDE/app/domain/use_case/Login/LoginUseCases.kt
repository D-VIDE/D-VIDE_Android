package com.d_vide.D_VIDE.app.domain.use_case.Login

import javax.inject.Inject

data class LoginUseCases @Inject constructor(
    val doLogin: DoLogin,
    val getToken: GetToken,
    val getUserID: GetUserID,
    val getFCMToken: GetFCMToken,
    val setToken: SetToken,
    val setUserID: SetUserID,
    val setFCMToken: SetFCMToken,
    val postFCMToken: PostFCMToken
)