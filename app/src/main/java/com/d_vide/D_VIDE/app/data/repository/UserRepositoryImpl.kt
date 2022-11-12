package com.d_vide.D_VIDE.app.data.repository

import com.d_vide.D_VIDE.app.data.remote.UserApi
import com.d_vide.D_VIDE.app.data.remote.requestDTO.EmailPasswordDTO
import com.d_vide.D_VIDE.app.data.remote.requestDTO.FcmTokenDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.*
import com.d_vide.D_VIDE.app.data.storage.FCMTokenStore
import com.d_vide.D_VIDE.app.data.storage.TokenStore
import com.d_vide.D_VIDE.app.domain.model.Token
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val store: TokenStore,
    private val fcmStore: FCMTokenStore,
    private val api: UserApi
) : UserRepository {
    override suspend fun doLogin(emailPw: EmailPasswordDTO): Response<Token> {
        return api.login(emailPw)
    }

    override suspend fun getUserInfo(): Response<UserDTO> {
        return api.getUserInfo()
    }

    override fun getUserToken(): Token {
        return runBlocking(Dispatchers.IO) {
            store.getToken().first()
        }
    }

    override fun setUserToken(token: Token) {
        runBlocking(Dispatchers.IO) {
            store.setToken(token)
        }
    }

    override suspend fun getMyOrders(): Response<RecruitingsDTO> {
        return api.getMyOrders()
    }

    override suspend fun getOtherUserInfo(userId: Long): Response<OtherUserInfoDTO> {
        return api.getOtherUserInfo(userId)
    }

    override suspend fun getFollowInformation(
        relation: String,
        offset: Int
    ): Response<FollowInfoDTO> {
        return api.getFollowInformation(relation, offset)
    }

    override suspend fun getFollowOther(
        relation: String,
        offset: Int,
        userId: Long
    ): Response<List<OtherFollowDataDTO>>{
        return api.getFollowOther(relation, offset, userId)
    }
    override suspend fun postFollow(
        userIdDTO: UserIdDTO
    ): Response<FollowIdDTO> {
        return api.postFollow(userIdDTO)
    }

    override suspend fun deleteFollow(
        followIdDTO: FollowIdDTO
    ): Response<FollowIdDTO> {
        return api.deleteFollow(followIdDTO)
    }

    override fun getFCMToken(): FcmTokenDTO {
        return runBlocking(Dispatchers.IO) {
            fcmStore.getFCMToken().first()
        }
    }

    override fun setFCMToken(fcmTokenDTO: FcmTokenDTO) {
        runBlocking(Dispatchers.IO) {
            fcmStore.setFCMToken(fcmTokenDTO)
        }
    }
    override suspend fun postFCMToken(
        fcmTokenDTO: FcmTokenDTO
    ) {
        return api.postFCMToken(fcmTokenDTO)
    }
}