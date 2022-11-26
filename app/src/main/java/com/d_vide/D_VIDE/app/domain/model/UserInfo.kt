package com.d_vide.D_VIDE.app.domain.model

import com.d_vide.D_VIDE.app.data.remote.responseDTO.BadgeDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserDTO
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    var userId: Long = 0L,
    var email: String = "",
    var nickname: String = "",
    var badge: BadgeDTO = BadgeDTO(),
    var profileImgUrl: String = "",
    var followerCount: Int = 0,
    var followingCount: Int = 0,
    val carbonAmount: Int = 0,
    var savedPrice: Int = 0,
) {
    fun setByUser(user: UserDTO) {
        email = user.email
        nickname = user.nickname
        profileImgUrl = user.profileImgUrl
        badge = user.badge
        followerCount = user.followerCount
        followingCount = user.followingCount
        savedPrice = user.savedPrice
    }
}