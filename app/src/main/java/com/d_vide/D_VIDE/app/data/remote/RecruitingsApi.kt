package com.d_vide.D_VIDE.app.data.remote

import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingBodyDTO
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingIdDTO
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingsDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RecruitingsApi {

    // 근처 500m 모집글
    @GET("/api/v1/posts/nearby")
    suspend fun getRecruitings(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Response<RecruitingsDTO>

    // 모집글 작성
    @POST("/api/v1/post")
    suspend fun postRecruiting(
        @Query("userId") userId: Int,
        @Body recruitingBody: RecruitingBodyDTO
    ): Response<RecruitingIdDTO>

}