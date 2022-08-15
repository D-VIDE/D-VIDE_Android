package com.d_vide.D_VIDE.app.data.remote

import com.d_vide.D_VIDE.app._enums.Category
import com.d_vide.D_VIDE.app.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RecruitingsApi {

    // 근처 500m 모집글
    @GET("/api/v1/posts")
    suspend fun getRecruitings(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("category") category: String,
        @Query("first") offset: Int,
    ): Response<RecruitingsDTO>

    // 모집글 작성
    @POST("/api/v1/post")
    suspend fun postRecruiting(
        @Query("userId") userId: Int,
        @Body recruitingBody: RecruitingBodyDTO
    ): Response<RecruitingIdDTO>

    @POST("/api/v1/order")
    suspend fun postRecruitingOrder(
       // @Query("userId") userId: Int,
        @Body recruitingOrder: RecruitingOrderDTO
    ): Response <RecruitingOrderIdDTO>
}