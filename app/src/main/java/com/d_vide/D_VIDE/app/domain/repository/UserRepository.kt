package com.d_vide.D_VIDE.app.domain.repository

import com.d_vide.D_VIDE.app.data.remote.requestDTO.EmailPasswordDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.OtherUserInfoDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.RecruitingsDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserDTO
import com.d_vide.D_VIDE.app.domain.model.Token
import retrofit2.Response

interface UserRepository {

    suspend fun doLogin(emailPw: EmailPasswordDTO): Response<Token>
    suspend fun getUserInfo(): Response<UserDTO>

    fun getUserToken(): Token
    fun setUserToken(token: Token)

    suspend fun getMyOrders(): Response<RecruitingsDTO>
    suspend fun getOtherUserInfo(userId: Long): Response<OtherUserInfoDTO>
}