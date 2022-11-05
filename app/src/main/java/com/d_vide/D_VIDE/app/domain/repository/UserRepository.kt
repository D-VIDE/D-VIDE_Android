package com.d_vide.D_VIDE.app.domain.repository

import com.d_vide.D_VIDE.app.data.remote.requestDTO.EmailPasswordDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.*
import com.d_vide.D_VIDE.app.domain.model.Token
import retrofit2.Response

interface UserRepository {

    suspend fun doLogin(emailPw: EmailPasswordDTO): Response<Token>
    suspend fun getUserInfo(): Response<UserDTO>

    fun getUserToken(): Token
    fun setUserToken(token: Token)

    suspend fun getMyOrders(): Response<RecruitingsDTO>
    suspend fun getOtherUserInfo(userId: Long): Response<OtherUserInfoDTO>

    suspend fun getFollowInformation(
        relation: String, offset: Int): Response<FollowInfoDTO>

    suspend fun postFollow(userIdDTO: UserIdDTO): Response<FollowIdDTO>
    suspend fun deleteFollow(userIdDTO: UserIdDTO): Response<FollowIdDTO>
}