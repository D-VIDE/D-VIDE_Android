package com.d_vide.D_VIDE.app.data.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import com.d_vide.D_VIDE.app.data.remote.requestDTO.RecruitingOrderDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.*
import retrofit2.Response
import retrofit2.http.*

interface RecruitingsApi {

    // 근처 500m 모집글 v2
    @GET("/api/v2/posts")
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

    @Multipart
    @POST("/api/v1/order")
    suspend fun postRecruitingOrder(
        @Part("request") request: RequestBody,
        @Part images: List<MultipartBody.Part?>,
    ): Response <RecruitingOrderIdDTO>

    @Multipart
    @POST("/api/v1/review")
    suspend fun postReview(
        @Query("postId") postId: Int,
        @Part("request") request: RequestBody,
        @Part images: List<MultipartBody.Part?>,
    ): Response <ReviewIdDTO>

    //상세 게시글 조회
    @GET("/api/v1/posts/{postId}")
    suspend fun getRecruitingDetail(
        @Path("postId") postId : Long
    ): Response<RecruitingDetailDTO>
}