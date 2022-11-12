package com.d_vide.D_VIDE.app.data.repository

import com.d_vide.D_VIDE.app.data.remote.UserApi
import com.d_vide.D_VIDE.app.data.remote.requestDTO.EmailPasswordDTO
import com.d_vide.D_VIDE.app.data.remote.requestDTO.UserIdDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.*
import com.d_vide.D_VIDE.app.data.storage.UserStore
import com.d_vide.D_VIDE.app.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val store: UserStore,
    private val api: UserApi
) : UserRepository {
    override suspend fun doLogin(emailPw: EmailPasswordDTO): Response<IdentificationDTO> {
        return api.login(emailPw)
    }

    override suspend fun getUserInfo(): Response<UserDTO> {
        return api.getUserInfo()
    }

    override fun getUserToken(): String {
        return runBlocking(Dispatchers.IO) {
            store.getToken().first()
        }
    }

    override fun setUserToken(token: String) {
        runBlocking(Dispatchers.IO) {
            store.setToken(token)
        }
    }

    override fun getUserID(): Long {
        return runBlocking(Dispatchers.IO) {
            store.getUserID().first()
        }
    }

    override fun setUserID(ID: Long) {
        runBlocking(Dispatchers.IO) {
            store.setUserID(ID)
        }
    }

    override fun getFCMToken(): String {
        return runBlocking(Dispatchers.IO) {
            store.getFCMToken().first()
        }
    }

    override fun setFCMToken(token: String) {
        runBlocking(Dispatchers.IO) {
            store.setFCMToken(token)
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

    override suspend fun postFollow(
        userIdDTO: UserIdDTO
    ): Response<FollowIdDTO> {
        return api.postFollow(userIdDTO)
    }

    override suspend fun deleteFollow(
        userIdDTO: UserIdDTO
    ): Response<FollowIdDTO> {
        return api.deleteFollow(userIdDTO)
    }
}