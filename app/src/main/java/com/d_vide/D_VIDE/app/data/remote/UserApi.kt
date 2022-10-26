package com.d_vide.D_VIDE.app.data.remote

import com.d_vide.D_VIDE.app.data.remote.requestDTO.EmailPasswordDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.*
import com.d_vide.D_VIDE.app.domain.model.Token
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body emailPw: EmailPasswordDTO
    ): Response<Token>

    @GET("/api/v1/user")
    suspend fun getUserInfo(): Response<UserDTO>

    @GET("/api/v1/orders")
    suspend fun getMyOrders(): Response<RecruitingsDTO>

    @GET("/api/v1/user/{userId}")
    suspend fun getOtherUserInfo(
        @Path("userId") userId: Long,
    ): Response<OtherUserInfoDTO>

    @GET("/api/v1/follow")
    suspend fun getFollowInformation(
        @Query("relation") relation: String,
        @Query("first") offset: Int,
    ): Response<FollowInfoDTO>

    @POST("/api/v1/follow")
    suspend fun postFollow(
        @Path("userId") userId: Long,
    ): Response<FollowIdDTO>

    @DELETE("/api/v1/follow")
    suspend fun deleteFollow(
        @Path("userId") userId: Long,
    ): Response<FollowResultDTO>

}