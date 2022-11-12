package com.d_vide.D_VIDE.app.domain.use_case.User

import javax.inject.Inject

data class UserUseCases @Inject constructor(
    val getUserInfo: GetUserInfo,
    val getOtherUserInfo: GetOtherUserInfo,
)
