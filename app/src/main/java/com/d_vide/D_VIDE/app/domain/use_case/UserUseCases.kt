package com.d_vide.D_VIDE.app.domain.use_case

import javax.inject.Inject

data class UserUseCases @Inject constructor(
    val doLogin: DoLogin,
    val getUserInfo: GetUserInfo,
    val getToken: GetToken,
    val setToken: SetToken,
    val getOtherUserInfo: GetOtherUserInfo
)
