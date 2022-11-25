package com.d_vide.D_VIDE.app.domain.repository

import com.d_vide.D_VIDE.app.data.remote.requestDTO.BadgeRequestDTO
import com.d_vide.D_VIDE.app.data.remote.requestDTO.EmailPasswordDTO
import com.d_vide.D_VIDE.app.data.remote.requestDTO.FcmTokenDTO
import com.d_vide.D_VIDE.app.data.remote.requestDTO.UserIdDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.*
import com.d_vide.D_VIDE.app.domain.model.UserInfo
import retrofit2.Response

interface UserRepository {

    suspend fun doLogin(emailPw: EmailPasswordDTO): Response<IdentificationDTO>
    suspend fun doKakaoLogin(token: String): Response<IdentificationDTO>

    suspend fun getUser(): Response<UserDTO>

    fun getUserToken(): String
    fun setUserToken(token: String)

    fun getUserID(): Long
    fun setUserID(ID: Long)

    fun getUserInfo(): UserInfo
    fun setUserInfo(userInfo: UserInfo)

    fun getFCMToken(): String
    fun setFCMToken(token: String)

    suspend fun getMyOrders(): Response<RecruitingsDTO>
    suspend fun getOtherUserInfo(userId: Long): Response<OtherUserInfoDTO>

    suspend fun getFollowInformation(relation: String, offset: Int): Response<FollowInfoDTO>
    suspend fun getFollowOther(relation: String, offset: Int, userId: Long): Response<List<OtherFollowDataDTO>>

    suspend fun postFollow(userIdDTO: UserIdDTO): Response<FollowIdDTO>
    suspend fun deleteFollow(followIdDTO: FollowIdDTO): Response<FollowIdDTO>

    suspend fun postFCMToken(fcmTokenDTO: FcmTokenDTO)

    suspend fun getBadges(): Response<BadgesDTO>
    suspend fun postBadge(badgeRequestDTO: BadgeRequestDTO): Response<BadgeRequestDTO>

}