package com.d_vide.D_VIDE.app.domain.use_case

import com.d_vide.D_VIDE.app.domain.use_case.Follow.DeleteFollow
import com.d_vide.D_VIDE.app.domain.use_case.Follow.GetFollowInformation
import com.d_vide.D_VIDE.app.domain.use_case.Follow.GetOtherFollow
import com.d_vide.D_VIDE.app.domain.use_case.Follow.PostFollow
import javax.inject.Inject

data class UserUseCases @Inject constructor(
    val doLogin: DoLogin,
    val getUserInfo: GetUserInfo,
    val getToken: GetToken,
    val setToken: SetToken,
    val getOtherUserInfo: GetOtherUserInfo,
    val getFollowInformation: GetFollowInformation,
    val getOtherFollow: GetOtherFollow,
    val postFollow: PostFollow,
    val deleteFollow: DeleteFollow,
    val postFCMToken: PostFCMToken,
    val getFCMToken: GetFCMToken,
    val setFCMToken: SetFCMToken
)
