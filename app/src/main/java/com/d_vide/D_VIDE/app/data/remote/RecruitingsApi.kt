package com.d_vide.D_VIDE.app.data.remote

import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingIdDTO
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingsDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import com.d_vide.D_VIDE.app.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

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
    @Multipart
    @POST("/api/v2/post")
    suspend fun postRecruiting(
        @Part("request") request: RequestBody,
        @Part images: List<MultipartBody.Part?>,
    ): Response<RecruitingIdDTO>

    @POST("/api/v1/order")
    suspend fun postRecruitingOrder(
       // @Query("userId") userId: Int,
        @Body recruitingOrder: RecruitingOrderDTO
    ): Response <RecruitingOrderIdDTO>
}